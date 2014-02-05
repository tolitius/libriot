(ns libriot.layout.browsing
  (:require [libriot.layout.base :refer :all]))

(def header
  (in-container {:container-class "browse-head" :jumbo-class "c-header"}
    [:div.row.center [:i.fa.fa-dot-circle-o] " Wonbooking Life " [:i.fa.fa-dot-circle-o]]))

(def add-book-modal
  (in-modal {:title "Adding New Book" 
             :clazz` "add-book-modal" 
             :body [:div.row.search-book
                    [:div.input-group.col-xs-offset-2.col-xs-6
                     [:span.input-group-addon [:i.fa.fa-barcode]]
                     [:input.form-control {:type "text" :autofocus "autofocus" :placeholder "add by ISBN"}]]
                    [:button.btn.btn-default [:i.fa.fa-search]]]
             :do-it "Add" 
             :cancel "Close"}))

(def contact-borrower-modal
  (in-modal {:title "Contact " 
             :clazz "contact-borrower-modal"
             :body [:row [:div "..."]] 
             :do-it "Send" 
             :cancel "Close"}))

(def lend-book-modal
  (in-modal {:title "Lending a Book" 
             :clazz "lend-book-modal"
             :body [:row [:div "..."]] 
             :do-it "Lend" 
             :cancel "Close"}))

(def t-books
  (in-container {:container-class "c-books"}
    [:div.row
      [:table.table.table-striped.table-bordered.t-books
       [:thead
        [:tr
         [:th [:i.fa.fa-book] " Title"]
         [:th " " [:i.fa.fa-gear]]
         [:th [:i.fa.fa-location-arrow] " Whereabouts"]
         [:th [:i.fa.fa-file-text-o] " Format"]
         [:th [:i.fa.fa-star-half-o] " Rating"]]]]]))

(defn browse []
  (with-bootstrap "libriot: where books live a full life"
    header
    add-book-modal
    contact-borrower-modal
    lend-book-modal
    t-books))
