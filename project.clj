(defproject lean-poker "2.0.0-SNAPSHOT"
  :description "Team Clojurians poker bot"
  :url "https://github.com/v-kolesnikov/lean-poker.clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.taoensso/timbre "3.4.0"]]
  :min-lein-version "2.4.3"
  :main lean-poker.core
  :target-path "target/%s"
  :uberjar-name "lean-poker-standalone.jar"
  :profiles {:uberjar {:aot :all}
             :production {:env {:production true}}})
