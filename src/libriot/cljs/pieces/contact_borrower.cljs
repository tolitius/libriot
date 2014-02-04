(ns libriot.pieces.contact-borrower
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [libriot.pieces.books-table :refer [cell-data borrower-name-col book-title-col]]
            [jayq.core :refer [$ html attr on add-class remove-attr append ajax]]
            [crate.core :as crate]
            [clojure.string :as s]
            [cljs.reader :as reader])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def $contact-modal ($ :.contact-borrower-modal))
(def $contact-modal-title ($ ".contact-borrower-modal .modal-title"))
(def $contact-modal-body ($ ".contact-borrower-modal .modal-body"))
(def contact-template (atom "[contact template]"))

(defn prepare-contact-template []
  (ajax {:url "/contact-template"
         :type "GET"
         :success #(reset! contact-template %)}))

(defpartial contact-icon []
 [:i.fa.fa-envelope])

(defn contact-email [borrower-name book-title template]
  (-> template
      (s/replace #"n####" borrower-name)
      (s/replace #"b####" (str "\"" book-title "\"" ))))

(defn contact-borrower [$contact id row]
  (let [borrower-name (second (cell-data borrower-name-col row))
        book-title (second (cell-data book-title-col row))]
    (add-class $contact "contact-link center")
    (html $contact (contact-icon))
    (on $contact :click 
        (fn [e] 
          (html $contact-modal-title (str "Contact " borrower-name))
          (html $contact-modal-body (contact-email borrower-name book-title @contact-template))
          (.modal $contact-modal "show")))))
