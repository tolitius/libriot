(ns libriot.browsing
  (:require [libriot.thingies :refer [fade-out-in info info->js]]
            [jayq.core :as jq]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ html add-class]])
  (:use-macros [crate.def-macros :only [defpartial]]))


(def $t-books ($ :.t-books))

(defn star-rating [row _ rownum _]
  (let [$rating ($ "td:eq(2)" row)
        score (.text $rating)]
    (html $rating "")
    (add-class $rating "rating")
    (.raty $rating (clj->js {:score score
                             :readOnly true}))
    (html $rating (str (html $rating) " &nbsp&nbsp(" score ")"))))

(.dataTable $t-books 
  (clj->js {:sAjaxSource "/browse"
            :aoColumns [{:mData "title"}
                        {:mData "whereabouts"}
                        {:mData "rating" :sWidth "128px"}]
            :oLanguage {:sLengthMenu ""}
            :fnRowCallback star-rating}))
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
