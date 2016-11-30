(ns lean-poker.positions)
;; set of helpers for working with player's positions @ the table

(declare state)

(defn total-players
  "Returns total number of players in this round"
  [state]
  (count (:players state)))

(defn dealer-index
  "Returns index of dealer in current round"
  [state]
  (:dealer state))

(defn small-blind-index
  "Index of Small blind at given state"
  [state]
  (let [players (total-players state)
        dealer (dealer-index state)]
    (if (= players 2)
      dealer
      (if (>= (inc dealer) (dec players))
        (- players (inc dealer))
        (inc dealer)))))

(defn big-blind-index
  "Index of Big blind at given state"
  [state]
  (let [players (total-players state)
        dealer (dealer-index state)
        small-blind (small-blind-index state)]
    (if (= players 2)
      (if (= dealer 0) 1 0)
      (if (>= (inc small-blind) (dec players))
        (- players (inc small-blind))
        (inc small-blind)))))
