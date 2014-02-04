(ns libriot.pieces.books-table
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [jayq.core :as jq]
            [crate.core :as crate]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ html attr on add-class remove-attr append]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def $t-books ($ :.t-books))

;; datatable "magic number" legacy (in order to fetch in :fnRowCallback)
(def book-title-col 0)
(def action-id-col 1)
(def borrower-name-col 2)
(def rating-col 4)

(defn cell-data [col row]
  (let [$cell ($ (str "td:eq(" col ")") row)]
    [$cell (.text $cell)]))

(defpartial search-box [$input]
 [:div.input-group $input
  [:span.input-group-addon
   [:i.fa.fa-search]]])

(defn bootstrap-search-box []
  (let [$search ($ ".t-search label")
        $input ($ ".t-search label input")]
    (attr $input "autofocus" "true")
    (html $search (search-box $input))))

(defn table-it [$t-books on-complete row-callback]
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
              :fnRowCallback row-callback
              :fnInitComplete on-complete
              :sDom "<'row t-books-head'<'col-xs-2'l><'col-xs-offset-2 col-xs-4 t-search'f><'col-xs-offset-2 col-xs-2 add-book-link'>r>t<'row t-books-foot'<'col-xs-6'i><'col-xs-6'p>>"})))
