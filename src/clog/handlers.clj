(ns clog.handlers
  (:use compojure.core)
  (:use selmer.parser)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [clog.db :as db]))


(defn index-page
  []
  {:body "Hello World"})

(defn new-post
  []
  {:body (render-file "templates/new-post.html" {})})

(defn view-post
  [id]
  {:body (render-file "templates/post.html" {:id id})})

