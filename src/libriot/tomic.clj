(ns libriot.tomic
  (:use [datomic.api :only (db) :as d])
  (:require [libriot.dbnator :refer :all]
            [clojure.set :refer [rename-keys]]
            [clojure.tools.logging :refer [info]]))

(defn find-by [by value] ;; ... )

