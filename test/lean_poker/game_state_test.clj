(ns lean-poker.game-state-test
  (:require [clojure.test :refer :all]
            [lean-poker.game-state :as game]))

(def game-state
  {:game_id "550da1cb2d909006e90004b1"
   :dealer 0
   :community_cards []
   :round 0
   :current_buy_in 0
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
   :bet_index 0
   :pot 0})

(deftest player-test
  (let [state1 (assoc-in game-state [:in_action] 0)
        state2 (assoc-in game-state [:in_action] 1)]
    (is (= (get-in game-state [:players 0]) (game/player state1)))
    (is (= (get-in game-state [:players 1]) (game/player state2)))))

(deftest preflop?-test
  (is (game/preflop? game-state)))

(deftest bet-test
  (let [state (assoc-in game-state [:players 0 :bet] 314)]
    (is (= 314 (game/bet state)))))

(deftest hand-test
  (let [player-cards [{:rank 3 :suit "spades"}
                      {:rank 5 :suit "spades"}]
        state (assoc-in game-state [:players 0 :hole_cards] player-cards)]
    (is (= player-cards (game/hand state)))))

(deftest pair?-test
  (let [player-cards [{:rank 4 :suit "spades"}
                      {:rank 4 :suit "hearts"}]
        state (assoc-in game-state [:players 0 :hole_cards] player-cards)]
    (is (game/pair? state))))

(deftest potential-flush?-test
  (let [player-cards [{:rank 2 :suit "hearts"}
                      {:rank 6 :suit "hearts"}]
        state (assoc-in game-state [:players 0 :hole_cards] player-cards)]
    (is (game/potential-flush? state))))

(deftest high-card?-test
  (let [player-cards [{:rank "A" :suit "spades"}
                      {:rank 10  :suit "hearts"}]
        state (assoc-in game-state [:players 0 :hole_cards] player-cards)]
    (is (game/high-card? state))))
