(ns clog.test.handler
  (:use clojure.test
        ring.mock.request
        clog.routes))

(deftest test-app
  (testing "index page route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "new post route"
    (let [response (app (request :get "/new/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Creating new post"))))

  (testing "view post route"
    (let [response (app (request :get "/view/4/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Viewing post 4"))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
