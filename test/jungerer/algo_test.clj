(ns jungerer.algo-test
  (:require [clojure.test :refer :all]
            [jungerer.algo :refer :all]
            [jungerer.graph :as g]
            [jungerer.test-common :refer :all])
  (:import [edu.uci.ics.jung.algorithms.scoring
            BarycenterScorer BetweennessCentrality ClosenessCentrality
            DegreeScorer EigenvectorCentrality HITS HITS$Scores PageRank]))

(deftest dijkstra-path-test
  (let [graph (g/directed-graph test-edges)]
    (g/add-node! graph 5)
    (testing "for directed graph"
      (is (dijkstra-path graph 1 3) [1 2 3])
      (is (dijkstra-path graph 4 3) [4 2 3])
      (is (dijkstra-path graph 4 1) [])
      (is (dijkstra-path graph 1 1) [1])
      (is (dijkstra-path graph 1 5) [])
      (is (thrown? IllegalArgumentException (dijkstra-path graph 1 100)))))
  (let [graph (g/undirected-graph test-edges)]
    (g/add-node! graph 5)
    (testing "for undirected graph"
      (is (dijkstra-path graph 1 3) [1 2 3])
      (is (dijkstra-path graph 4 3) [4 2 3])
      (is (dijkstra-path graph 4 1) [4 2 1])
      (is (dijkstra-path graph 1 1) [1])
      (is (dijkstra-path graph 1 5) [])
      (is (thrown? IllegalArgumentException (dijkstra-path graph 1 100))))))

(deftest barycenter-scorer-test
  (let [graph (g/directed-graph test-edges)]
    (testing "returns BarycenterScorer instance"
      (is (instance? BarycenterScorer (barycenter-scorer graph))))))

(deftest degree-scorer-test
  (let [graph (g/directed-graph test-edges)]
    (testing "returns DegreeScorer instance"
      (is (instance? DegreeScorer (degree-scorer graph))))))

(deftest betweenness-centrality-test
  (let [graph (g/directed-graph test-edges)]
    (testing "returns BetweennessCentrality instance"
      (is (instance? BetweennessCentrality (betweenness-centrality graph))))))

(deftest closeness-centrality-test
  (let [graph (g/directed-graph test-edges)]
    (testing "returns ClosenessCentrality instance"
      (is (instance? ClosenessCentrality (closeness-centrality graph))))))

(deftest eigenvector-centrality-test
  (let [graph (g/directed-graph test-edges)]
    (testing "returns EigenvectorCentrality instance"
      (is (instance? EigenvectorCentrality (eigenvector-centrality graph))))))

(deftest page-rank-test
  (let [graph (g/directed-graph test-edges)]
    (testing "returns PageRank instance"
      (is (instance? PageRank (page-rank graph))))))

(deftest hits-test
  (let [graph (g/directed-graph test-edges)]
    (testing "returns HITS instance"
      (is (instance? HITS (hits graph))))))

(deftest score-test
  (let [graph (g/directed-graph test-edges)]
    (testing ""
      (let [scorer (barycenter-scorer graph)]
        (is (number? (score scorer 2))))
      (let [scorer (degree-scorer graph)]
        (is (number? (score scorer 2))))
      (let [scorer (betweenness-centrality graph)]
        (is (number? (score scorer 2))))
      (let [scorer (closeness-centrality graph)]
        (is (number? (score scorer 2))))
      (let [scorer (eigenvector-centrality graph)]
        (is (number? (score scorer 2))))
      (let [scorer (page-rank graph)]
        (is (number? (score scorer 2))))
      (let [scorer (hits graph)]
        (is (map? (score scorer 2)))))))
