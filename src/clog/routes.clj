(ns clog.routes
  (:use compojure.core
        clog.handlers
        [ring.middleware.json :only [wrap-json-response]]
        [ring.middleware.params :only [wrap-params]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/new/" [] (new-post))
  (POST "/create/" [title body] (create-post title body))
  (GET "/view/:id/" [id] (view-post id))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (wrap-json-response
  (wrap-params (handler/site main-routes))))
