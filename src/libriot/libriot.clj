(ns libriot
  (:require [clojure.string :refer [split capitalize lower-case] :as cstr]
            [clojure.tools.logging :refer [info]]
            [clojure.edn :as edn]
            [clojure.data.json :as json]
            [clj-time.core :refer [now]]
            [clj-time.format :refer :all]))

(defn human-date [d]
  (let [dt (parse (formatters :year-month-day) d)]
    (unparse (formatter "dd MMMM yyyy") dt)))

(defn book-search [] "da book")

(defn find-all []
  (json/write-str {:id -1 
                   :fieldErrors []
                   :sError ""
                   :aaData [
                            {:DT_RowId "row_1"
                             :title "Joy of Clojure"
                             :whereabouts "Anatoly"
                             :rating 4.2}
                            {:DT_RowId "row_2"
                             :title "Functional Programming in Scala"
                             :whereabouts "Some Dude"
                             :rating 1.8}
                            {:DT_RowId "row_3"
                             :title "Java Puzzlers"
                             :whereabouts "Dan"
                             :rating 4.7}
                            {:DT_RowId "row_4"
                             :title "The Future of Java Applets"
                             :whereabouts "Aaron"
                             :rating 5.0}
                            ]}))
