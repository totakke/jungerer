(ns jungerer.graph-test
  (:require [clojure.test :refer :all]
            [jungerer.graph :refer :all]
            [jungerer.test-common :refer :all])
  (:import [edu.uci.ics.jung.graph DirectedSparseGraph
                                   DirectedSparseMultigraph
                                   UndirectedSparseGraph
                                   UndirectedSparseMultigraph]))

(deftest directed-sparse-graph-test
  (testing "returns DirectedSparseGraph instance"
    (is (instance? DirectedSparseGraph (directed-sparse-graph test-edges)))))

(deftest directed-sparse-multigraph-test
  (testing "returns DirectedSparseMultigraph instance"
    (is (instance? DirectedSparseMultigraph
                   (directed-sparse-multigraph test-edges)))))

(deftest undirected-sparse-graph-test
  (testing "returns UndirectedSparseGraph instance"
    (is (instance? UndirectedSparseGraph
                   (undirected-sparse-graph test-edges)))))

(deftest undirected-sparse-multigraph-test
  (testing "returns UndirectedSparseMultigraph instance"
    (is (instance? UndirectedSparseMultigraph
                   (undirected-sparse-multigraph test-edges)))))
