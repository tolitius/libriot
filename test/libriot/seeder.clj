;;(data-from "datomic:free://localhost:4334/libriot" "resources/db/schema.edn")
;;(def data-tx (read-string (slurp "test/resources/db/libriot.edn")))
;;@(d/transact *conn* data-tx)
(ns libriot.seeder
  (:require [clojure.edn :as edn])
  (:use libriot.dbnator
        clojure.tools.logging
        [datomic.api :only (db) :as d]))

(defn read-datomic-edn [path]
  (edn/read-string {:readers *data-readers*} 
                   (slurp path)))

(defn create-schema [part schema] 
  (d/transact *conn* 
    (read-datomic-edn part))     ;; creates a custom parition
  (d/transact *conn* 
    (read-datomic-edn schema)))

(defn create-libriot-schema [uri]
  (connect-to uri)
  (create-schema "resources/db/partition.edn" 
                 "resources/db/schema.edn"))

(defn data-from [uri path]
  (connect-to uri)
  (create-schema "resources/db/partition.edn" 
                 "resources/db/schema.edn")
  (d/transact *conn*
    ;; ;; needs an 'eval' to process all the BigInts and friends
    ;; (eval (do (use '[datomic.api :only (db) :as d]) (read-datomic-edn path))))
    (read-datomic-edn path))
  (info "data from \"" path "\" loaded to [" uri "]"))

(defn load-test-data [uri path mockdata-path]
  (let [data-tx (read-string (slurp mockdata-path))]
    (do (data-from uri path) 
        @(d/transact *conn* data-tx))))
        

(defn delete-test-db [uri]
  (d/delete-database uri)
  (info "database [" uri "] is deleted"))
