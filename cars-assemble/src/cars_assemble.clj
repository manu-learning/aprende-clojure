(ns cars-assemble)

(def cars-per-hour 221)

(defn success-rate
  [speed]
  (cond (zero? speed) 0.0
        (<= speed 4) 1.0
        (<= speed 8) 0.9
        (= 9 speed) 0.8
        (= 10 speed) 0.77))

  ;; alternativa #1, evitamos evaluar el valor cero
  ;; (cond (= speed 10) 0.77
  ;;       (= speed 9) 0.8
  ;;       (>= speed 5) 0.9
  ;;       (>= speed 1) 1.0
  ;;       :else 0.0)

  ;; alternativa #2, es más específico (operador desde valor hasta) resultado
  ;; (cond (zero? speed) 0.0
  ;;       (<= 1 speed 4) 1.0
  ;;       (<= 5 speed 8) 0.9
  ;;       (= 9 speed) 0.8
  ;;       (= 10 speed) 0.77)

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (* speed cars-per-hour (success-rate speed)))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (int
   (quot (production-rate speed) 60)))
