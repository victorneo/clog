(defproject clog "0.1.0-SNAPSHOT"
  :description "Clog is blogging web app built in Clojure"
  :url "http://github.com/victorneo/clog"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.2.1"]
                 [selmer "0.5.5"]
                 [org.clojure/java.jdbc "0.3.2"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.8"]]
  :ring {:init clog.db/create-db
         :handler clog.routes/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [enlive "1.1.5"]]}})
