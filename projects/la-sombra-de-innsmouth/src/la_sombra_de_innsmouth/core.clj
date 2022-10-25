(ns la-sombra-de-innsmouth.core)
(def personajes "personajes.csv")

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

;; Hay que pensar de forma topdown, usamos abstracciones que aún no definimos, más adelante se definirán
;; Ej. Recorro y opero cada elemento de una secuencia en el cuerpo de una función,
;; defino como serían (aunque aún siquiera le pasamos la colección a la función por parámetro)
;; y luego de todo eso, entonces le paso la secuencia con los elementos (claro que deben respetar el tipo de dato,
;; que se definió en el cuerpo de la función de como deberían ser)
;;
;; 1. pensamos en Recibimos filas del csv y Recorremos cada fila
;; => usemos la función map
;;
;; 2. definimos que haremos con cada fila
;; => delegemos en una función anónima pasada a map, ésta se aplicará a cada elemento recorrido por map
;;
;; 3. Recorremos cada columna (caracteristica) de la fila y la Transformaremos de A a B
;; => usemos la función reduce
;;
;; 4. definimos que haremos con cada columna de la fila
;; => delegemos en una función anónima pasada al reduce, ésta se aplicará a cada elemento recorrido por reduce
;;
;; 5. pensamos que devolverá el reduce, queremos un map {:clave valor}
;; => la semilla será un map (no se definió aún, suponemos que luego la recibirá el reduce)
;;
;; 6. pensamos como son los elementos con los que operará el reduce, será algo del tipo [:clave valor]
;; => aplicamos el concepto de destructuring para separar las columnas del vector (caracteristica, valor)
;;
;; 7. llegamos al objetivo, tenemos la semilla y las columnas
;; => le asociamos un map de la forma {:clave valor} a la semilla que es un map, se repetirá con cada elemento
;;
;; 8. ya hicimos lo dificil, ahora sólo le pasamos al reduce la semilla (un map vacío)
;; y los elementos que recorrerá (deben ser de la forma con la que definimos que se operarán osea [:clave valor])
;;
;; 9. sólo queda pasarle al map la secuencia de elementos que recorrerá y le aplicará la función definida al principio
(defn personaje-map
  [filas-pendientes]
  (map (fn [fila-pendiente]
         (reduce (fn [nueva-fila-transformada [caracteristica valor]]
                   (assoc nueva-fila-transformada caracteristica (caracteristica-personaje caracteristica valor)))
                 {}
                 (map vector personaje-caracteristicas fila-pendiente)))
       filas-pendientes))

(first (personaje-map (lista-de-filas (slurp personajes))))

(defn personajes-humanos
  [limite-edad lista-personajes]
  (filter #(<= (:tiempo-de-vida %) limite-edad) lista-personajes))

(personajes-humanos 60
                    (personaje-map (lista-de-filas (slurp personajes))))
