(ns annalyns-infiltration)


(defn can-fast-attack?
  "Returns true if a fast-attack can be made, false otherwise."
  [knight-awake?]
  (not knight-awake?))

(defn can-spy?
  "Returns true if the kidnappers can be spied upon, false otherwise."
  [knight-awake? archer-awake? prisoner-awake?]
  (or knight-awake?
      archer-awake?
      prisoner-awake?))

(defn can-signal-prisoner?
  "Returns true if the prisoner can be signalled, false otherwise."
  [archer-awake? prisoner-awake?]
  (and prisoner-awake?
       (not archer-awake?)))

(defn can-free-prisoner?
  "Returns true if prisoner can be freed, false otherwise."
  [knight-awake? archer-awake? prisoner-awake? dog-present?]

  ;; Solution: (or (true true) (true false false))
  (or (and dog-present? ;; Option 1
           (not archer-awake?))
      (and prisoner-awake? ;; Option 2
           (not dog-present?)
           (not knight-awake?)
           (not archer-awake?)))
  )
