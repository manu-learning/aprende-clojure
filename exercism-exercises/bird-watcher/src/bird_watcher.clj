(ns bird-watcher)

(def last-week 
  [0 2 5 3 7 8 4])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (conj (pop birds)
        (+ 1
           (last birds))))

;; https://stackoverflow.com/questions/3249334/test-whether-a-list-contains-a-specific-value-in-clojure
;; https://clojuredocs.org/clojure.core/contains_q
(defn contains-zero? [numbers]
  (if (some zero? numbers) true false))

(defn day-without-birds? [birds]
  (contains-zero? birds))

;; https://clojuredocs.org/clojure.core/subvec
(defn n-days-count [birds n]
  (apply +
         (subvec birds 0 n)))

;; podría no estar.. ya que no se utiliza en varias partes
;; pero me parecío interesante practicar funciones booleanas
(defn busy-day? [day]
  (>= day 5))

;; https://clojuredocs.org/clojure.core/filterv
(defn busy-days [birds]
  (count
   (filterv
    (fn [day] (busy-day? day)) birds)))

(defn odd-week? [birds]
  (= birds [1 0 1 0 1 0 1]))
