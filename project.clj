(defproject clog "0.1.0-SNAPSHOT"
  :description "Clog is blogging web app built in Clojure"
  :url "http://github.com/victorneo/clog"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [enlive "1.1.5"]
                 [selmer "0.6.6"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-core "1.3.0"]
                 [org.clojure/java.jdbc "0.3.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [compojure "1.1.8"]]
  :plugins [[lein-ring "0.8.8"]]
  :ring {:init clog.db/create-db
         :handler clog.routes/app
         :auto-refresh? true}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [enlive "1.1.5"]]}})
