(ns libriot
  (:require [clojure.string :refer [split capitalize lower-case] :as cstr]
            [clojure.tools.logging :refer [info]]
            [clojure.edn :as edn]
            [clj-time.core :refer [now]]
            [clj-time.format :refer :all]))

(defn human-date [d]
  (let [dt (parse (formatters :year-month-day) d)]
    (unparse (formatter "dd MMMM yyyy") dt)))

(defn book-search [] "da book")
