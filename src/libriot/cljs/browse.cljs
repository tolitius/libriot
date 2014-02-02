(ns libriot.browsing
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [jayq.core :as jq]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ html attr add-class remove-attr]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def $t-books ($ :.t-books))
(def init? (atom true))
(def contact-id-col 1)
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

(defpartial contact-link []
 [:i.fa.fa-envelope])

(defpartial lend-link []
 [:i.fa.fa-users])

(defn make-contact-link [row]
  (let [[$contact id] (cell-data contact-id-col row)]
    (html $contact "")
    (if-not (= id "home-base") 
      (do
        (add-class $contact "contact-link center")
        (attr $contact "id" id)
        (html $contact (contact-link)))
      (do
        (add-class $contact "lend-link center")
        (html $contact (lend-link))))))

(defn add-meta [row & _]
  (when @init?
    (make-contact-link row)
    (star-rating row)))

(defn table-it []
  (.dataTable $t-books 
    (clj->js {:sAjaxSource "/browse"
              :aoColumns [{:mData "title"}
                          {:mData "contact-id" :sDefaultContent "" :sWidth "22px" :bSortable false}
                          {:mData "whereabouts"}
                          {:mData "format" :sClass "center"}
                          {:mData "rating" :sWidth "128px"}]
              :aaSorting [[rating-col "desc"]]
              :oLanguage {:sLengthMenu ""}
              :fnRowCallback add-meta
              :fnInitComplete (fn [] (remove-attr $t-books "style") 
                                     (reset! init? false))})))
              ;; :sDom "<'row'<'col-xs-6'T><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>"}))))

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
