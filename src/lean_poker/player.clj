(ns lean-poker.player
  (:require [taoensso.timbre :as log]))

(def version "0.0.4-snapshot")

(defn bet-request
  [game-state]
  (log/info game-state)
  100)

(defn showdown
  [game-state]
  (log/info game-state)
  nil)
