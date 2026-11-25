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

(defn- calcular-imposto-retido [taxa-padrao salario]
  (let [valor (* salario (/ (taxa-padrao salario) 100))]
    (arredondar-centavos valor)))

(defn imposto-retido [taxa-padrao salario]
  (if (isento? salario)
    salario
    (calcular-imposto-retido taxa-padrao salario)))

(defn -consulta-taxa-padrao-fixa  []
   0.10)

(defn -minha-taxa-padrao [_]
  (-consulta-taxa-padrao-fixa))

(defn escolhe-taxa-padrao [ambiente]
  (if (= ambiente :teste)
    -minha-taxa-padrao
    consulta-taxa
    ))


(imposto-retido (escolhe-taxa-padrao :producao) 13000)
