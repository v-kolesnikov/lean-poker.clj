(ns lean-poker.core
  (:gen-class)
  (:require [clojure.data.json :as json]
            [lean-poker.player :as player]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :refer [run-jetty]]
            [taoensso.timbre :as log]))

(defn game-state [req]
  (try
    (-> (get-in req [:params "game_state"])
        (json/read-str :key-fn keyword))
    (catch Exception ex
      (log/error "Bad JSON" ex)
      {})))

(defn handler [req]
  (case (-> req :params (get "action"))
    "bet_request"
    (let [bet (player/bet-request (game-state req))]
      (log/info "Bet" bet)
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    (str bet)})

    "showdown"
    (let [showdown (player/showdown (game-state req))]
      (log/info "Showdown" showdown)
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    (str showdown)})

    "check"
    (let [showdown (player/showdown (game-state req))]
      (log/info "Showdown" showdown)
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    "Im clojurians!"})

    "version"
    (do
      (log/info "Version" player/version)
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    (str player/version)})

    (do
      (log/warn "Bad request" req)
      {:status 400})))

(defn -main [& [port]]
  (let [port (Integer. (or port 8087))]
    (run-jetty (wrap-params handler) {:port port})))
