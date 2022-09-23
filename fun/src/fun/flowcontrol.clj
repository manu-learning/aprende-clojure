(ns fun.flowcontrol)

;; https://clojure.org/guides/learn/flow
(if (even? 2) "even" "odd")

(if (even? 3) "even" "odd")

;; declaramos la variable x
(def x 9)
(cond (= x 5) "x es 5"
      (> x 7) "x es mayor a 7")

;; declaramos la variable x "pero vive sólo dentro del ámbito de let"
(let [x 5]
  (if (even? x) "even" "odd"))

(let [x 6]
  (cond (= x 10) "x es igual a 10"
        (> x 5) "x es mayor a 5"))

(let [x 1]
  (cond (= x 10) "x es igual a 10"
        (> x 5) "x es mayor a 5"
        :else "pucha no le acerté"))

;; es el típico switch case
;; si no agregamos al último no le agregamos valor, lo tomará como el default
;; si no agregamos el default, lanzará una excepción si no coincide/matchea con alguna opción
(defn menu-opcion [opcion]
  (case opcion
    1 "pizza"
    2 "arróz"
    3 "lentejas"
    "no has elegido una opción"))
