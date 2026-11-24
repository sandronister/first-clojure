(ns tax-clojure.logic-test
  (:require [clojure.test :refer :all]
            [tax-clojure.logic :refer [consulta-taxa imposto-retido]]))

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
    (is (= 1903.98 (imposto-retido 1903.98 consulta-taxa)))
    (is (= 1 (imposto-retido 1 consulta-taxa))))
  (testing "Testando a faixa de imposto de renda entre 1903,99 e 2826,65"
    (is (= 212.0 (imposto-retido 2826.65 consulta-taxa)))
    (is (= 142.8 (imposto-retido 1903.99 consulta-taxa))))
  (testing "Testando a faixa de imposto de renda entre 2826,66 e 3751,05"
    (is (= 562.66 (imposto-retido 3751.05 consulta-taxa)))
    (is (= 424.0 (imposto-retido 2826.66 consulta-taxa))))
  (testing "Testando a faixa de imposto de renda entre 3751,06 e 4664,68"
    (is (= 1049.55 (imposto-retido 4664.68 consulta-taxa)))
    (is (= 843.99 (imposto-retido 3751.06 consulta-taxa))))
  (testing "Testando a faixa de imposto de renda acima de 4664,68"
    (is (= 1282.79 (imposto-retido 4664.69 consulta-taxa)))))
