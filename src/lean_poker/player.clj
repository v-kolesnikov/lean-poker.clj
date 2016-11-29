(ns lean-poker.player
  (:require [lean-poker.bet-request :as bet]))

(def version "2.0")

(defn bet-request
  [game-state]
  (bet/call game-state))

(defn showdown
  [game-state]
  nil)
