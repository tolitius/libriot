(ns libriot.browse
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [libriot.pieces.books-table :refer [cell-data bootstrap-search-box] :as bt]
            [libriot.pieces.add-book :refer [add-book add-book-button]]
            [libriot.pieces.contact-borrower :refer [contact-borrower]]
            [libriot.pieces.lend-book :refer [lend-a-book]]
            [jayq.core :refer [$ html attr on add-class remove-attr append]]
            [crate.core :as crate]
            [cljs.reader :as reader])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def $t-books ($ :.t-books))
(def init? (atom true))

(defn star-rating [row]
  (let [[$rating score] (bt/cell-data bt/rating-col row)]
    (html $rating "")
    (add-class $rating "rating")
    (.raty $rating (clj->js {:score score
                             :readOnly true}))
    (html $rating (str (html $rating) " &nbsp&nbsp(" score ")"))))

(defn make-action-link [row]
  (let [[$cell id] (bt/cell-data bt/action-id-col row)]
    (html $cell "")
    (if-not (= id "home-base") 
      (contact-borrower $cell id row)
      (lend-a-book $cell row))))

(defn add-meta [row & _]
  (when @init?
    (make-action-link row)
    (star-rating row)))

(defn init-books-table []
  (remove-attr $t-books "style") 
  (bootstrap-search-box)
  (add-book-button)
  (reset! init? false))

(defn show-library [data]
  (let [details (reader/read-string data)]
    (info->js details)
    ;; ...
    ))

(defn init [] 
  (bt/table-it $t-books init-books-table add-meta))

;;TODO: externalize to "base layout: (init)"
(init)
