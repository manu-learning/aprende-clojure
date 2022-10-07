(ns all-your-base)

(require '[clojure.string :as str])

;; https://clojuredocs.org/clojure.string/split
;; https://stackoverflow.com/questions/4714923/convert-a-sequence-of-strings-to-integers-clojure
(defn number-to-list [num]
  (map read-string
       (str/split
        (str num) #"")))

;; https://stackoverflow.com/questions/5057047/how-to-do-exponentiation-in-clojure
(defn exp [x n]
  (reduce *
          (repeat n x)))

;; https://stackoverflow.com/questions/2111891/stackoverflow-while-counting-digits
(defn count-digits [num]
  (count (str num)))

;; queda comentado la idea era obtener el índice de la lista
;; pero falta knowledge aún para hacerlo de forma correcta, asi que salen libros..
;;
;; 1*2^2 + 0*2^1 + 1*2^0
;; (defn exp-every-number [numbers base]
;;   (map (fn [x] (*
;;                 x
;;                 (exp base )))
;;        numbers))

;; (defn exp-every-number [numbers base]
;;   (map (fn [x] (exp x base))
;;        numbers))

(defn convert-binary-to-decimal [number]
  (reduce +
          (exp-every-number
           (number-to-list number) 10)))

(defn convert [baseFrom number baseTo]
)
