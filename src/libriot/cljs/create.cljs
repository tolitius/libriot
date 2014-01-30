(ns libriot.create
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [jayq.core :as jq]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$]])
  (:use-macros [crate.def-macros :only [defpartial]]))


;; (def $add-book ($ :.add-book-btn))

(defn show-library [data]
  (let [details (reader/read-string data)]
    (info->js details)
    ;; ...
    ))

(defn add-book [isbn]
  (jq/ajax {:url "/add-book"
            :data isbn
            :type "POST"
            :success show-library}))

(jq/on $add-book :click
  (fn [e]
    (.preventDefault e)
    (add-book)))

(jq/on $add-book :keydown
  (fn [e]
    (if (= (.-keyCode e) 13)
      (do
        (.preventDefault e)
        (.click $add-book)))))

