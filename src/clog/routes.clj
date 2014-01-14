(ns clog.routes
  (:use compojure.core
        clog.handlers)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/new/" [] (new-post))
  (POST "/new/" [title body] (new-post))
  (GET "/view/:id/" [id] (view-post id))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)))
