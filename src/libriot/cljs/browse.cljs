(ns libriot.browsing
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [jayq.core :as jq]
            [crate.core :as crate]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ html attr on add-class remove-attr append]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def $t-books ($ :.t-books))
(def $contact-modal ($ :.contact-borrower-modal))
(def $contact-modal-title ($ ".contact-borrower-modal .modal-title"))
(def $lend-modal ($ :.lend-book-modal))
(def init? (atom true))
(def action-id-col 1)
(def rating-col 4)

(defn cell-data [col row]
  (let [$cell ($ (str "td:eq(" col ")") row)]
    [$cell (.text $cell)]))

(defn star-rating [row]
  (let [[$rating score] (cell-data rating-col row)]
    (html $rating "")
    (add-class $rating "rating")
    (.raty $rating (clj->js {:score score
                             :readOnly true}))
    (html $rating (str (html $rating) " &nbsp&nbsp(" score ")"))))

(defpartial contact-icon []
 [:i.fa.fa-envelope])

(defpartial lend-icon []
 [:i.fa.fa-users])

(defn contact-borrower [$contact id row]
  (add-class $contact "contact-link center")
  (html $contact (contact-icon))
  (on $contact :click 
      (fn [e] 
        (html $contact-modal-title (str "Contact " id))
        (.modal $contact-modal "show"))))

(defn lend-a-book [$lend row]
  (add-class $lend "lend-link center")
  (html $lend (lend-icon))
  (on $lend :click
      (fn [e] (.modal $lend-modal "show"))))

(defn make-action-link [row]
  (let [[$cell id] (cell-data action-id-col row)]
    (html $cell "")
    (if-not (= id "home-base") 
      (contact-borrower $cell id row)
      (lend-a-book $cell row))))

(defn add-meta [row & _]
  (when @init?
    (make-action-link row)
    (star-rating row)))

(defpartial search-box [$input]
 [:div.input-group $input
  [:span.input-group-addon
   [:i.fa.fa-search]]])

(defn bootstrap-search-box []
  (let [$search ($ ".t-search label")
        $input ($ ".t-search label input")]
    (attr $input "autofocus" "true")
    (html $search (search-box $input))))

(defn add-book-button []
  (append ($ :.add-book-link)
          (crate/html [:button.btn.btn-warning {:data-toggle "modal" :data-target ".add-book-modal"} 
                       [:i.fa.fa-plus-circle " New Book"]])))

(defn table-it []
  (.dataTable $t-books 
    (clj->js {:sAjaxSource "/browse"
              :aoColumns [{:mData "title"}
                          {:mData "contact-id" :sDefaultContent "" :sWidth "22px" :bSortable false}
                          {:mData "whereabouts"}
                          {:mData "format" :sClass "center"}
                          {:mData "rating" :sWidth "128px"}]
              :aaSorting [[rating-col "desc"]]
              :oLanguage {:sLengthMenu ""
                          :sInfo "Showing _START_ to _END_ of _TOTAL_ books"
                          :sSearch ""}
              :fnRowCallback add-meta
              :fnInitComplete (fn [] (remove-attr $t-books "style") 
                                     (bootstrap-search-box)
                                     (add-book-button)
                                     (reset! init? false))
              ;; :sDom "<'row'<'col-xs-6'T><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>"}))))
              :sDom "<'row t-books-head'<'col-xs-2'l><'col-xs-offset-2 col-xs-4 t-search'f><'col-xs-offset-2 col-xs-2 add-book-link'>r>t<'row t-books-foot'<'col-xs-6'i><'col-xs-6'p>>"})))

(defn show-library [data]
  (let [details (reader/read-string data)]
    (info->js details)
    ;; ...
    ))

(defn add-book [isbn]
  (jq/ajax {:url "/add-book"
            :data isbn
            :type "POST"
            :success show-library}))

(defn init [] (table-it))

;;TODO: externalize to "base layout: (init)"
(init)
