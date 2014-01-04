(ns clog.test.handler
  (:use clojure.test
        ring.mock.request
        clog.routes)
  (:require [clojure.java.io :as io]
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
      (let [response (app (request :get "/new/"))]
        (is (= (:status response) 200))
        (is (= (:body response) "Creating new post"))))

  (testing "View Post Route"
      (let [response (app (request :get "/view/4/"))]
        (is (= (:status response) 200))
        (is (not= (.indexOf (:body response) "Viewing post 4") -1))))

    (testing "Not-found Route"
      (let [response (app (request :get "/invalid"))]
        (is (= (:status response) 404)))))

