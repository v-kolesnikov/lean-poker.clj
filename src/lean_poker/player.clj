(ns lean-poker.player
  (:require [taoensso.timbre :as log]
            [lean-poker.hand :as hand]))

(def version "0.0.6-snapshot")

(def small_bet 0)
(def big_bet 230)

(defn check-hand [a b]
  (cond
    (and (> a 9) (> b 9) (= a b) true)
    (= a b) true
    :else false))

(defn bet-request
  [game-state]
  (log/info game-state)
  (let [card1 (first  (hand/my-cards game-state))
        card2 (second (hand/my-cards game-state))]
    (if (check-hand (rank-weight card1) (rank-weight card2))
      big_bet
      small_bet)))

(defn rank-weight [{rank :rank}]
  (get rank {1 "2"
             2 "3"
             3 "4"
             4 "5"
             5 "6"
             6 "7"
             8 "9"
             9 "10"
             10 "J"
             11 "Q"
             12 "K"
             13 "A"}))

(defn showdown
  [game-state]
  (log/info game-state)
  nil)
