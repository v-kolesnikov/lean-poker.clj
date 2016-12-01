(ns lean-poker.bet-request-test
  (:require [clojure.test :refer :all]
            [lean-poker.game-state :as game]
            [lean-poker.bet-request :as bet]))

(def game-state
  {:game_id "550da1cb2d909006e90004b1"
   :dealer 0
   :community_cards []
   :round 0
   :current_buy_in 20
   :tournament_id "550d1d68cd7bd10003000003"
   :orbits 0
   :in_action 0
   :players
   [{:name "Player 1"
     :stack 1000
     :status "active"
     :bet 0
     :hole_cards []
     :version "Version name 1"
     :id 0}
    {:name "Player 2"
     :stack 1000
     :status "active"
     :bet 0
     :hole_cards []
     :version "Version name 2"
     :id 1}]
   :small_blind 10
   :minimum_raise 20
   :bet_index 0
   :pot 0})

(deftest call-when-pair
  (let [player-cards [{:rank "A", :suit "hearts"}
                      {:rank "A", :suit "spades"}]
        state (assoc-in game-state [:players 0 :hole_cards] player-cards)]
    (is (= 40 (bet/call state)))))

(deftest call-when-potential-flush
  (let [player-cards [{:rank "3" :suit "spades"}
                      {:rank "5" :suit "spades"}]
        state (assoc-in game-state [:players 0 :hole_cards] player-cards)]
    (is (= 20 (bet/call state)))))

(deftest call-when-bad-hand
  (let [player-cards [{:rank "2" :suit "hearts"}
                      {:rank "3" :suit "spades"}]
        state (assoc-in game-state [:players 0 :hole_cards] player-cards)]
    (is (= 0 (bet/call state)))))
