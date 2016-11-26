(ns lean-poker.player
  (:require [taoensso.timbre :as log]
            [lean-poker.hand :as hand]))

(def version "0.0.15-lucky")

(def small-bet 0)
(def mid-bet 100)
(def big-bet 230)

(defn check-state [a b x y z]
  (let []
    (cond
          (and (> a 8) (> b 8) (= a b)) big-bet
          (and (> a 10) (> b 10)) big-bet
          (or (= a x) (= a y)
              (= a z) (= b x)
              (= b y) (= b z)) mid-bet
          :else small-bet)
    ))

(defn check-hand [a b]
  (log/info "In check-hand: a:" a)
  (log/info "In check-hand: b:" b)
  (if (nil? (or a b))
    false
    (cond
      (and (> a 8) (> b 8) (= a b)) true
      (and (> a 10) (> b 10)) true
      :else false)))

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

(defn bet-request
  [game-state]
  (log/info (str "[GAME STATE line 37] " game-state))
  (try
    (let [card1 (first  (hand/my-cards game-state))
          card2 (second (hand/my-cards game-state))]
      (->> hand/visible-cards
         (map rank-weight)
         (check-state)))
    (catch Exception e
      small-bet)))



(defn showdown
  [game-state]
  (log/info game-state)
  nil)
