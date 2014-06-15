(ns clog.handlers
  (:use compojure.core)
  (:use selmer.parser)
  (:use [ring.util.response :only [response]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :as resp]
            [clog.db :as db]))


(defn index-page
  []
  (response {:test "Testing"}))

(defn new-post
  []
  {:body (render-file "templates/new-post.html" {})})

(defn view-post
  [id]
  (let [post (db/get-post id)]
    (if (= nil post)
      {:body (render-file "templates/404.html" nil)}
      {:body (render-file "templates/post.html" post)})))

(defn create-post
  [title body]
  (let [post_id (db/insert-post {:title title :body body})]
       (resp/redirect (str "/view/" post_id "/"))))

