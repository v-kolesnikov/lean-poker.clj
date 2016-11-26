(ns lean-poker.player
  (:require [taoensso.timbre :as log]
            [lean-poker.hand :as hand]))

(def version "0.0.10-snapshot")

(def small_bet 0)
(def big_bet 230)

(defn check-hand [a b]
  (cond
    (and (> a 8) (> b 8) (= a b)) true
    (and (> a 10) (> b 10)) true
    :else false))

(defn rank-weight [{rank :rank}]
  (get {"2" 1
        "3" 2
        "4" 3
        "5" 4
        "6" 5
        "7" 6
        "9" 8
        "10" 9
        "J" 10
        "Q" 11
        "K" 12
        "A" 13} rank))

(def small_bet 0)
(def big_bet 230)

(defn bet-request
  [game-state]
  (log/info game-state)
  (let [card1 (first  (hand/my-cards game-state))
        card2 (second (hand/my-cards game-state))]
    (if (check-hand (rank-weight card1) (rank-weight card2))
      (log/info [big_bet small_bet])))
  230)

(defn showdown
  [game-state]
  (log/info game-state)
  nil)
