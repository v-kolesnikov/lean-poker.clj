(ns lean-poker.player
  (:require [taoensso.timbre :as log]))

(def version "0.0.6-snapshot")

(defn bet-request
  [game-state]
  (log/info game-state)
  230)

(defn showdown
  [game-state]
  (log/info game-state)
  nil)
