(ns libriot.pieces.contact-borrower
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [libriot.pieces.books-table :refer [cell-data borrower-name-col]]
            [jayq.core :as jq]
            [crate.core :as crate]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ html attr on add-class remove-attr append]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def $contact-modal ($ :.contact-borrower-modal))
(def $contact-modal-title ($ ".contact-borrower-modal .modal-title"))

(defpartial contact-icon []
 [:i.fa.fa-envelope])

(defn contact-borrower [$contact id row]
  (let [borrower-name (second (cell-data borrower-name-col row))]
    (add-class $contact "contact-link center")
    (html $contact (contact-icon))
    (on $contact :click 
        (fn [e] 
          (html $contact-modal-title (str "Contact " borrower-name))
          (.modal $contact-modal "show")))))
