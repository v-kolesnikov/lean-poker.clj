(ns lean-poker.bet-request
  (:require [lean-poker.game-state :as game]))

(defn preflop [state]
  (cond
    (game/pair? state) (game/raise state)
    (game/high-card? state) (game/safe-call state)
    (game/potential-flush? state) (game/safe-call state)
    :else game/check-fold))

; TODO: Implement flop round
(defn flop [state]
  (game/check-fold state))

(defn call [state]
  (if (game/preflop? state)
    (preflop state)
    (flop state)))
