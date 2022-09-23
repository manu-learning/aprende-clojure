(ns fun.lists)

;; https://clojuredocs.org/clojure.core/list

;; con quote podemos definir una lista, igual que con (list)
'(1 2 3 "a" "b")

(list 1 2 3 "a" "b")

(def numeros (list 1 2 3))

(def letras (list "a" "b" "c"))

(let [x 1 y 2]
  '(x y))

(let [x 1 y 2]
  `(x y))

;; falla
;;(let [x 1 y 2] (list x y))

;; https://clojuredocs.org/clojure.core/conj
;; https://medium.com/@greg_63957/conj-cons-concat-oh-my-1398a2981eab
;;
;; la función conj podemos agregar elementos a listas
(conj (list 1 2 3) 4)
