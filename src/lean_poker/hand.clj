(ns lean-poker.hand
  (:require
      [clojure.data.json :as json]
      [taoensso.timbre :as log]))

;;------------------------------------------
;; MOQ STATE :: FOR DEVELOPMENT IN REPL ONLY
;;------------------------------------------

(def raw-state
  { "tournament_id"  "550d1d68cd7bd10003000003"
      "game_id" "550da1cb2d909006e90004b1"
      "round"  0
      "bet_index"  0
      "small_blind"  10
      "current_buy_in"  320
      "minimum_raise"  240
      "dealer"  1
      "orbits"  7
      "in_action"  1
      "players"
        [
          {
              "id"  0
              "name"  "Albert"
              "status"  "active"
              "version"  "Default random player"
              "stack"  1010
              "bet"  320}
          {
              ;; Your own player looks similar with one extension.
              "id"  1
              "name"  "Bob"
              "status"  "active"
              "version"  "Default random player"
              "stack"  1590
              "bet"  80
              ;; our cards
              "hole_cards"
                [
                  {
                    "rank"  "6"
                    "suit"  "hearts"}
                  {
                    "rank"  "K"
                    "suit"  "spades"}]}
          {
            "id"  2
            "name"  "Chuck"
            "status"  "out"
            "version"  "Default random player"
            "stack"  0
            "bet"  0}]
      "community_cards"
        [
          {
            "rank"  "4"
            "suit"  "spades"}
          {
            "rank"  "A"
            "suit"  "hearts"}
          {
            "rank"  "6"
            "suit"  "clubs"}]})


;;------------------------------------------
;; MOQ STATE :: ENDS HERE
;;------------------------------------------

(defn parse
  "Parse & keywordize raw input"
  [raw-state]
  (-> (json/write-str raw-state)
      (json/read-str :key-fn keyword)))

(defn me
  "Accepts raw state, returns "
  [raw-state]
  (let [parsed-state (parse raw-state)
        players (:players parsed-state)]
    (->> players
        (filter
          #(contains? % :hole_cards))
        first)))

(defn my-cards
  [raw-state]
  (let [cards (me raw-state)]
    (:hole_cards cards)))

(defn community-cards
  [raw-state]
  (let [parsed-state (parse raw-state)]
    (:community_cards parsed-state)))
