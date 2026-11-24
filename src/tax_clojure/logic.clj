(ns tax-clojure.logic)

(defn imposto-retido [valor]
  (if (<= valor 1903.98)
    0
    (if (and (>= valor 1903.99) (<= valor 2826.65))
      7.5
      (if (and (>= valor 2826.66) (<= valor 3751.05))
        15
        (if (and (>= valor 3751.06) (<= valor 4664.68))
          22.5
          27.5)))))
