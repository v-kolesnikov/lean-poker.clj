(ns lean-poker.player
  (:require [taoensso.timbre :as log]
            [lean-poker.hand :as hand]))

(def version "0.0.6-snapshot")

(defn bet-request
  [game-state]
  (let [my-cards (hand/my-cards game-state)]

      230))

(defn showdown
  [game-state]
  (log/info game-state)
  nil)
