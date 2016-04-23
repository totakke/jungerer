(ns jungerer.graph-test
  (:require [clojure.test :refer :all]
            [jungerer.graph :refer :all]
            [jungerer.test-common :refer :all])
  (:import [edu.uci.ics.jung.graph DirectedSparseGraph
                                   DirectedSparseMultigraph]))

(deftest directed-sparse-graph-test
  (testing "returns DirectedSparseGraph instance"
    (is (instance? DirectedSparseGraph (directed-sparse-graph test-edges)))))

(deftest directed-sparse-multigraph-test
  (testing "returns DirectedSparseMultigraph instance"
    (is (instance? DirectedSparseMultigraph (directed-sparse-multigraph test-edges)))))
