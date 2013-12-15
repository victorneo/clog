(defproject clog "0.1.0-SNAPSHOT"
  :description "Clog is blogging web app built in Clojure"
  :url "http://github.com/victorneo/clog"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.2.1"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.8"]]
  :ring {:handler clog.routes/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
