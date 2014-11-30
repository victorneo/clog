(ns clog.test.handler
  (:use clojure.test
        ring.mock.request
        [cheshire.core :only [parse-string]]
        clog.routes)
  (:require [net.cgrand.enlive-html :as html]
            [clog.db :as db]))


(use-fixtures :once
  (fn [f]
        (db/create-db)
        (f)))


(deftest test-db
  (testing "Insert and Get Post"
        (let [post {:date "Some Time" :title "Some Title" :body "Some body"}
              post-id (db/insert-post post)
              created-post (db/get-post post-id)]
          (is (= (:title post) (:title created-post)))
          (is (= (:date post) (:date created-post)))
          (is (= (:body post) (:body created-post))))))


(deftest test-handlers
  (testing "Index Route"
      (let [response (app (request :get "/"))
            body (html/html-snippet (:body response))
            title (str (first (:content (first (html/select body [:h1])))))]
        (is (= (:status response) 200))
        (is (= title "Blog Posts"))))

  (testing "New Post Route"
      (let [response (app (request :get "/new/"))
            body (html/html-snippet (:body response))
            title (str (first (:content (first (html/select body [:h1])))))
            form (str (first (:content (first (html/select body [:form])))))]
        (is (= (:status response) 200))
        (is (= title "Create a New Blog Post"))))

  (testing "Create Post Route"
      (let [title "Test title"
            body "Test body"
            response (app (request :post "/create/" {:title title :body body}))
            headers (:headers response)]
        (is (= (:status response) 302))
        (is (not (= (re-seq #"\/view\/[\d]+\/" (headers "Location")) nil)))))

  (testing "View 404 Post Route"
      (let [response (app (request :get "/view/99999/"))
            title (str (first (:content (first (html/select (html/html-snippet (:body response)) [:h1])))))]
        (is (= (:status response) 200))
        (is (= title "Post not found"))))

  (testing "View Post Route"
      (let [title "Some title"
            body "Some body"
            postId (db/insert-post {:date "Some Time" :title title :body body})
            response (app (request :get (str "/view/" postId "/")))
            snippet (html/html-snippet (:body response))
            renderedTitle (str (first (:content (first (html/select snippet [:h1])))))
            renderedBody (str (first (:content (first (html/select snippet [:p])))))]
        (is (= (:status response) 200))
        (is (= title renderedTitle))
        (is (= body renderedBody))))

    (testing "Not-found Route"
      (let [response (app (request :get "/invalid"))]
        (is (= (:status response) 404)))))

