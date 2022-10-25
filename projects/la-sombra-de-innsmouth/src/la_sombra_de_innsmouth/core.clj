(ns la-sombra-de-innsmouth.core)
(def personajes "resources/personajes.csv")

;; sólo agregamos las keywords
(def personaje-caracteristicas [:nombre :raza :origen :tiempo-de-vida])

(defn str->int
  [str]
  (Integer. str))

;; para definir el tipo de dato según su keyword, lo usamos en conjunto con (convertir)
(def personaje {:nombre identity
                 :raza identity
                 :origen identity
                 :tiempo-de-vida str->int})

(defn caracteristica-personaje
  [personaje-key valor]
  ((get personaje personaje-key) valor))

;; (personaje-caracteristica :tiempo-de-vida 5)

(defn lista-de-filas
  "Devuelve una lista de filas de un CSV"
  [csv-texto]
  (map #(clojure.string/split % #",")
       (clojure.string/split csv-texto #"\n")))

;; (lista-de-filas (slurp personajes))

(defn personajes-map
  [filas-pendientes]
  (map (fn [fila-pendiente]
         (reduce (fn [nueva-fila-transformada [caracteristica valor]]
                   (assoc nueva-fila-transformada caracteristica (caracteristica-personaje caracteristica valor)))
                 {}
                 (map vector personaje-caracteristicas fila-pendiente)))
       filas-pendientes))

;; (first (personajes-map (lista-de-filas (slurp personajes))))

(defn personajes-humanos
  [limite-edad lista-personajes]
  (filter #(<= (:tiempo-de-vida %) limite-edad) lista-personajes))

;; (personajes-humanos 60 (personajes-map (lista-de-filas (slurp personajes))))
