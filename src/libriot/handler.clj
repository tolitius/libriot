(ns libriot.handler
  (:require ;; [libriot :refer []]
            [libriot.layout 
             ;;[create :refer [create-libriot]]
             ]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (search-libriot))
  ;; ...
  (route/resources "/")
  (route/not-found "not found"))

(def app
  (handler/site app-routes))
