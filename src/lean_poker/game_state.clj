(ns lean-poker.game-state)

(def check-fold 0)
(def high-card #{"J" "D" "K" "A"})

(defn player [{:keys [players in_action] :as state}]
  (get players in_action))

(defn preflop? [{:keys [community_cards] :as state}]
  (empty? community_cards))

(defn bet [state]
  (->> state
       player
       :bet))

(defn hand [state]
  (->> state
       player
       :hole_cards))

(defn pair? [state]
  (->> (hand state)
       (map :rank)
       (apply =)))

(defn potential-flush? [state]
  (->> (hand state)
       (map :suit)
       (apply =)))

(defn high-card? [state]
  (->> (hand state)
       (map :rank)
       (some #(get high-card %))))

; TODO: Extract constant value
(defn small-bet? [{:keys [current_buy_in] :as state}]
  (-> (bet state)
      (- current_buy_in)
      (< 200)))

(defn all_in [state]
  (->> state
       player
       :stack))

(defn safe-call [{:keys [current_buy_in] :as state}]
  (if (small-bet? state)
    current_buy_in
    check-fold))

(defn raise [{:keys [current_buy_in minimum_raise] :as state}]
  (->> (bet state)
       (- current_buy_in)
       (+ minimum_raise)))
