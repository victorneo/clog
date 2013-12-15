(ns clog.handlers
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))


(defn index-page
  []
  {:body "Hello World"})

(defn new-post
  []
  {:body "Creating new post"})

(defn view-post
  [id]
  {:body (str "Viewing post " id)})

