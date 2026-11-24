(ns tax-clojure.logic-test
  (:require [clojure.test :refer :all]
            [tax-clojure.logic :refer [imposto-retido]]))




(deftest imposto-retido-test
  (testing "Testando a faixa isenta de imposto de renda abaixo de 1903,98"
    (is (= 0 (imposto-retido 1903.98)))
    (is (= 0 (imposto-retido 1)))
    )
  (testing "Testando a faixa de imposto de renda entre 1903,99 e 2826,65"
    (is (= 7.5 (imposto-retido 2826.65)))
    (is (= 7.5 (imposto-retido 1903.99)))
    )
  (testing "Testando a faixa de imposto de renda entre 2826,66 e 3751,05"
    (is (= 15 (imposto-retido 3751.05)))
    (is (= 15 (imposto-retido 2826.66)))
    )
  (testing "Testando a faixa de imposto de renda entre 3751,06 e 4664,68"
    (is (= 22.5 (imposto-retido 4664.68)))
    (is (= 22.5 (imposto-retido 3751.06)))
    )
  (testing "Testando a faixa de imposto de renda acima de 4664,68"
    (is (= 27.5 (imposto-retido 4664.69)))
    ))

