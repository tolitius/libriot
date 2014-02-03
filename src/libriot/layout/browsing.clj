(ns libriot.layout.browsing
  (:require [libriot.layout.base :refer :all]))

(defn header []
  (in-container {:container-class "browse-head" :jumbo-class "c-header"}
    [:div.row.center [:i.fa.fa-dot-circle-o] " Wonbooking Life " [:i.fa.fa-dot-circle-o]]))

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
    (header)
    t-books))
