(ns tracks-on-tracks-on-tracks)

(defn new-list
  "Creates an empty list of languages to practice."
  []
  (list)
  )

;; https://clojuredocs.org/clojure.core/conj
;; https://medium.com/@greg_63957/conj-cons-concat-oh-my-1398a2981eab
(defn add-language
  "Adds a language to the list."
  [lang-list lang]
  (conj lang-list lang)
  )

(defn first-language
  "Returns the first language on the list."
  [lang-list]
  (first lang-list)
  )

(defn remove-language
  "Removes the first language added to the list."
  [lang-list]
  (drop 1 lang-list)
  )

(defn count-languages
  "Returns the total number of languages on the list."
  [lang-list]
  (count lang-list)
  )

;; https://clojuredocs.org/clojure.core/-%3E
(defn learning-list
  "Creates an empty list, adds Clojure and Lisp, removes Lisp, adds
  Java and JavaScript, then finally returns a count of the total number
  of languages."
  []
  (-> (new-list)
      (add-language "Clojure")
      (add-language "Lisp")
      (remove-language)
      (add-language "Java")
      (add-language "Javascript")
      (count-languages)
      )
  )
