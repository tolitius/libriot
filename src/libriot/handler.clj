(ns libriot.handler
  (:require [libriot :refer [find-all]]
            [libriot.layout 
             [browsing :refer [browse]]
             ]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (browse))
  (GET "/browse" [] (find-all))
  ;; ...
  (route/resources "/")
  (route/not-found "not found"))

(def app
  (handler/site app-routes))
