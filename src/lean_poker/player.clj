(ns lean-poker.player
  (:require [taoensso.timbre :as log]))

(def version "Clojure-y folding player")

(defn bet-request
  [game-state]
  (log game-state)
  0)

(defn showdown
  [game-state]
  (log game-state)
  nil)
