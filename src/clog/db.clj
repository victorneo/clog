(ns clog.db
  (:require [clojure.java.jdbc :as sql]))


(def sqlite-db {:subprotocol "sqlite"
                :subname "posts.sqlite"})


(defn posts-table-ddl []
  (sql/create-table-ddl :posts
                       [:date :text]
                       [:title :text]
                       [:body :text]))

(defn create-db []
  (try  (sql/db-do-commands sqlite-db
        (posts-table-ddl))
  (catch Exception e (println e))))


; Inserts a blog post and return its id
(defn insert-post [post]
  (first (vals (first (sql/insert! sqlite-db :posts post)))))


(defn get-post [id]
  (first (sql/query sqlite-db ["SELECT * FROM posts WHERE ROWID = ?" id])))
