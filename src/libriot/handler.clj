(ns libriot.handler
  (:require [libriot :refer [book-search]]
            [libriot.layout 
             [create :refer [add-book]]
             ]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (book-search))
  ;; ...
  (route/resources "/")
  (route/not-found "not found"))

(def app
  (handler/site app-routes))
