(ns libriot.pieces.add-book
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [jayq.core :as jq]
            [crate.core :as crate]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ html attr on add-class remove-attr append]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(defn add-book-button []
  (append ($ :.add-book-link)
          (crate/html [:button.btn.btn-warning {:data-toggle "modal" :data-target ".add-book-modal"} 
                       [:i.fa.fa-plus-circle " New Book"]]))
  (add-class ($ ".add-book-modal .do-it-btn") "disabled"))   ;; enable it later when a book is found to be added

(defn add-book [isbn on-complete]
  (jq/ajax {:url "/add-book"
            :data isbn
            :type "POST"
            :success on-complete}))

