(ns jungerer.graph-test
  (:require [clojure.test :refer :all]
            [jungerer.graph :refer :all]
            [jungerer.test-common :refer :all])
  (:import [edu.uci.ics.jung.graph DirectedSparseGraph
                                   DirectedSparseMultigraph
                                   UndirectedSparseGraph
                                   UndirectedSparseMultigraph]))

(deftest directed-graph-test
  (testing "returns DirectedSparseGraph instance"
    (is (instance? DirectedSparseGraph (directed-graph test-edges)))))

(deftest directed-multigraph-test
  (testing "returns DirectedSparseMultigraph instance"
    (is (instance? DirectedSparseMultigraph
                   (directed-multigraph test-edges)))))

(deftest undirected-graph-test
  (testing "returns UndirectedSparseGraph instance"
    (is (instance? UndirectedSparseGraph
                   (undirected-graph test-edges)))))

(deftest undirected-multigraph-test
  (testing "returns UndirectedSparseMultigraph instance"
    (is (instance? UndirectedSparseMultigraph
                   (undirected-multigraph test-edges)))))
