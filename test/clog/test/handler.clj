(ns clog.test.handler
  (:use clojure.test
        ring.mock.request
        clog.routes)
  (:require [clojure.java.io :as io]
            [net.cgrand.enlive-html :as html]
            [clog.db :as db]))


(use-fixtures :once
  (fn [f]
        (db/create-db)
        (f)
        (io/delete-file "posts.sqlite")))


(deftest test-db
  (testing "Insert and Get Post"
        (let [post {:date "Some Time" :title "Some Title" :body "Some body"}
              post-id (db/insert-post post)
              created-post (db/get-post post-id)]
          (is (= post created-post)))))


(deftest test-handlers
  (testing "Index Route"
      (let [response (app (request :get "/"))]
        (is (= (:status response) 200))
        (is (= (:body response) "Hello World"))))

  (testing "New Post Route"
      (let [response (app (request :get "/new/"))
            body (html/html-snippet (:body response))
            title (str (first (:content (first (html/select body [:h1])))))
            form (str (first (:content (first (html/select body [:form])))))]
        (is (= (:status response) 200))
        (is (= title "Create a new blog post"))))

  (testing "View 404 Post Route"
      (let [response (app (request :get "/view/99999/"))
            title (str (first (:content (first (html/select (html/html-snippet (:body response)) [:h1])))))]
        (is (= (:status response) 200))
        (is (= title "Post not found"))))


  (testing "View Post Route"
      (let [postId (db/insert-post {:date "Some Time" :title "Some Title" :body "Some Body"})
            response (app (request :get (str "/view/" postId "/")))
            title (str (first (:content (first (html/select (html/html-snippet (:body response)) [:h1])))))]
        (is (= (:status response) 200))
        (is (= title (str "Viewing post " postId )))))

    (testing "Not-found Route"
      (let [response (app (request :get "/invalid"))]
        (is (= (:status response) 404)))))

