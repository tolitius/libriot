(ns libriot.layout.browsing
  (:require [libriot.layout.base :refer :all]))

(defn header []
  (in-container {:container-class "browse" :jumbo-class "c-header"}
    [:div.row.center "it's a wonbooking life"]))

(def t-books
  (in-container {:container-class "c-books"}
    [:div.row
      [:table.table.table-striped.table-bordered.t-books
       [:thead
        [:tr
         [:th "Title"]
         [:th "Whereabouts"]
         [:th "Rating"]]]]]))

(defn browse []
  (with-bootstrap "libriot: where books live a full life"
    (header)
    t-books))
