(ns tax-clojure.logic)

(defn- isento? [valor]
  (<= valor 1903.98))

(defn- primeira-faixa? [valor]
  (and (>= valor 1903.99) (<= valor 2826.65)))

(defn- segunda-faixa? [valor]
  (and (>= valor 2826.66) (<= valor 3751.05)))

(defn- terceira-faixa? [valor]
  (and (>= valor 3751.06) (<= valor 4664.68)))

(defn consulta-taxa [valor]
  (if (isento? valor)
    0
    (if (primeira-faixa? valor)
      7.5
      (if (segunda-faixa? valor)
        15
        (if (terceira-faixa? valor)
          22.5
          27.5)))))

(defn- arredondar-centavos [valor]
  (/ (double (Math/round (* valor 100))) 100))

(defn- calcular-imposto-retido [salario taxa-padrao]
  (let [valor (* salario (/ (taxa-padrao salario) 100))]
    (arredondar-centavos valor)))

(defn imposto-retido [salario taxa-padrao]
  (if (isento? salario)
    salario
    (calcular-imposto-retido salario taxa-padrao)))
