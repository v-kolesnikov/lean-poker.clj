(defproject lean-poker "0.1.0-SNAPSHOT"
  :description "LeanPoker player in Clojure"
  :url "http://github.com/lean_poker/poker-player-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [org.clojure/data.json "0.2.6"]
                 [com.taoensso/timbre "3.4.0"]]
  :min-lein-version "2.4.3"
  :main lean-poker.core
  :target-path "target/%s"
  :uberjar-name "lean-poker-standalone.jar"
  :profiles {:uberjar {:aot :all}
             :production {:env {:production true}}})
