(ns libriot.layout.create
  (:require [libriot.layout.base :refer :all]))

(defn header []
  (in-container {:container-class "add-book" :jumbo-class "c-header"}
    [:div.row.center]))

(defn add-book []
  (with-bootstrap "libriot: adding a new book"
    (header)))
