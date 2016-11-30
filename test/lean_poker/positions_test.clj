(ns lean-poker.positions-test
  (:require [clojure.test :refer :all]
            [lean-poker.positions :as positions]))

;; HELPERS

(def state
  {:game_id "550da1cb2d909006e90004b1"
   :dealer 0
   :community_cards []
   :round 0
   :current_buy_in 0
   :tournament_id "550d1d68cd7bd10003000003"
   :orbits 0
   :players []
   :in_action 0
   :small_blind 10
   :bet_index 0
   :pot 0})

(defn get-player [n]
  {:name (str "Player " n)
   :stack 1000
   :status "active"
   :bet 0
   :hole_cards []
   :version (str "Version name " n)
   :id n})

(defn set-players-number [state, number]
  (assoc state :players (map-indexed
                         (fn [n _] (get-player n))
                         (repeat number nil))))

(defn switch-dealer [state, n]
  (assoc state :dealer n))

;; TESTS

(deftest total-players-test
  (is (= (count (:players state)) (positions/total-players state))))

(deftest dealer-index-test
  (is (= (:dealer state) (positions/dealer-index state))))

(deftest small-blind-index-test
  (let [state1 (set-players-number state 2)
        state2 (switch-dealer state1 1)
        state3 (set-players-number state 4)
        state4 (switch-dealer state3 3)]
    (is (= 0 (positions/small-blind-index state1)))
    (is (= 1 (positions/small-blind-index state2)))
    (is (= 1 (positions/small-blind-index state3)))
    (is (= 0 (positions/small-blind-index state4)))))

(deftest big-blind-index-test
  (let [state1 (set-players-number state 2)
        state2 (switch-dealer state1 1)
        state3 (set-players-number state 4)
        state4 (switch-dealer state3 3)]
    (is (= 1 (positions/big-blind-index state1)))
    (is (= 0 (positions/big-blind-index state2)))
    (is (= 2 (positions/big-blind-index state3)))
    (is (= 1 (positions/big-blind-index state4)))))
