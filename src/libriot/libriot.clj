(ns libriot
  (:require [clojure.string :refer [split capitalize lower-case] :as cstr]
            [clojure.tools.logging :refer [info]]
            [clojure.edn :as edn]
            [clojure.data.json :as json]
            [clj-time.core :refer [now]]
            [clj-time.format :refer :all]))

(defn book-search [] "where books live a full life")

(defn find-contact-template []
  "Dear n####,</br></br>
   By the power vested in me, I urge you to return b#### back to our library.<br/><br/>
   Thank you,<br/>
   Your Book Master")

(defn find-all []
  (json/write-str {:id -1 
                   :fieldErrors []
                   :sError ""
                   :aaData [
                            {:DT_RowId "row_1"
                             :title "Joy of Clojure"
                             :whereabouts "Anatoly"
                             :contact-id 42
                             :format "PDF"
                             :rating 4.2}
                            {:DT_RowId "row_2"
                             :title "Functional Programming in Scala"
                             :whereabouts "Some Dude"
                             :contact-id 48379
                             :format "ePub"
                             :rating 1.8}
                            {:DT_RowId "row_3"
                             :title "Java Puzzlers"
                             :whereabouts "Dan"
                             :contact-id 34
                             :format "PDF"
                             :rating 4.7}
                            {:DT_RowId "row_4"
                             :title "The Future of Java Applets"
                             :whereabouts "Aaron"
                             :contact-id 17
                             :format "Mobi"
                             :rating 5.0}
                            {:DT_RowId "row_5"
                             :title "Struts: The Good Parts"
                             :whereabouts "Chariot"
                             :contact-id "home-base"
                             :format "EBCDIC"
                             :rating 0.6}
                            ]}))
