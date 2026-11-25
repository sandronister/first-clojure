(ns tax-clojure.logic-test
  (:require [clojure.test :refer :all]
            [tax-clojure.logic :refer [consulta-taxa escolhe-taxa-padrao imposto-retido]]))

(deftest consulta-taxa-test
  (testing "Testando a faixa isenta de imposto de renda abaixo de 1903,98"
    (is (= 0 (consulta-taxa 1903.98)))
    (is (= 0 (consulta-taxa 1))))
  (testing "Testando a faixa de imposto de renda entre 1903,99 e 2826,65"
    (is (= 7.5 (consulta-taxa 2826.65)))
    (is (= 7.5 (consulta-taxa 1903.99))))
  (testing "Testando a faixa de imposto de renda entre 2826,66 e 3751,05"
    (is (= 15 (consulta-taxa 3751.05)))
    (is (= 15 (consulta-taxa 2826.66))))
  (testing "Testando a faixa de imposto de renda entre 3751,06 e 4664,68"
    (is (= 22.5 (consulta-taxa 4664.68)))
    (is (= 22.5 (consulta-taxa 3751.06))))
  (testing "Testando a faixa de imposto de renda acima de 4664,68"
    (is (= 27.5 (consulta-taxa 4664.69)))))

(deftest imposto-retido-test
  (testing "Testando a faixa isenta de imposto de renda abaixo de 1903,98"
    (is (= 1903.98 (imposto-retido  consulta-taxa 1903.98)))
    (is (= 1 (imposto-retido  consulta-taxa 1))))
  (testing "Testando a faixa de imposto de renda entre 1903,99 e 2826,65"
    (is (= 212.0 (imposto-retido consulta-taxa 2826.65 )))
    (is (= 142.8 (imposto-retido consulta-taxa 1903.99))))
  (testing "Testando a faixa de imposto de renda entre 2826,66 e 3751,05"
    (is (= 562.66 (imposto-retido consulta-taxa 3751.05 )))
    (is (= 424.0 (imposto-retido consulta-taxa 2826.66 ))))
  (testing "Testando a faixa de imposto de renda entre 3751,06 e 4664,68"
    (is (= 1049.55 (imposto-retido consulta-taxa 4664.68 )))
    (is (= 843.99 (imposto-retido consulta-taxa 3751.06 ))))
  (testing "Testando a faixa de imposto de renda acima de 4664,68"
    (is (= 1282.79 (imposto-retido consulta-taxa 4664.69 )))))

(deftest escolhe-taxa-padrao-test
  (testing "A taxa de teste fixa pode ser usada no cálculo do imposto"
    (is (= 80.0 (imposto-retido (escolhe-taxa-padrao :teste) 80000)))))
