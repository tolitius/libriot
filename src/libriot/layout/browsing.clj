(ns libriot.layout.browsing
  (:require [libriot.layout.base :refer :all]))

(def header
  (in-container {:container-class "browse-head" :jumbo-class "c-header"}
    [:div.row.center [:i.fa.fa-dot-circle-o] " Wonbooking Life " [:i.fa.fa-dot-circle-o]]))

(def add-book-modal
  [:div.modal.fade.add-book-modal {:tabindex "-1" :role "dialog" :aria-labelledby "add-book-label" :aria-hidden "true"}
   [:div.modal-dialog
    [:div.modal-content
     [:div.modal-header
      [:button.close {:type "button" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
      [:h4.modal-title {:id "add-book-label"} "Adding New Book"]]
     [:div.modal-body "..."]
     [:div.modal-footer
      [:button.btn.btn-default {:type "button" :data-dismiss "modal"} "Close"]
      [:button.btn.btn-warning {:type "button"} "Add"]]]]])

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
    t-books))
