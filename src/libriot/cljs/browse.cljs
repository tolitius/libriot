(ns libriot.browsing
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [jayq.core :as jq]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$]])
  (:use-macros [crate.def-macros :only [defpartial]]))


(def $t-books ($ :.t-books))

(.dataTable $t-books 
  (clj->js {:sAjaxSource "/browse"
            :aoColumns [{:mData "title"}
                        {:mData "whereabouts"}
                        {:mData "rating"}]}))
            ;; :sDom "<'row'<'col-xs-6'T><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>"}))

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
