(ns fun.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;; https://clojuredocs.org/clojure.core/-%3E
;; la función arrow ->
;; permite aplicarle varias funciones a un elemento y evitar usar muchos paréntesis (((..)))

(-> (list "a")
    (conj "b")
    (conj "c")
    )

(-> 1
    (+ 1)
    (+ 4))

(-> "a b c"
    .toUpperCase
    (.replace "A" "Z")
    (.split " ")
    first)
