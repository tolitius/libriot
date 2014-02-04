(ns libriot.pieces.lend-book
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [libriot.pieces.books-table :refer [cell-data]]
            [jayq.core :as jq]
            [crate.core :as crate]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ html attr on add-class remove-attr append]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def $lend-modal ($ :.lend-book-modal))

(defpartial lend-icon []
 [:i.fa.fa-users])

(defn lend-a-book [$lend row]
  (add-class $lend "lend-link center")
  (html $lend (lend-icon))
  (on $lend :click
      (fn [e] (.modal $lend-modal "show"))))
