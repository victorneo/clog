(ns clog.handlers
  (:use compojure.core)
  (:use selmer.parser)
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
  {:body (render-file "templates/post.html" {:id id})})

