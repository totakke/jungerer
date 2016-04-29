(ns jungerer.algo-test
  (:require [clojure.test :refer :all]
            [jungerer.algo :refer :all]
            [jungerer.graph :as g]
            [jungerer.test-common :refer :all])
  (:import [edu.uci.ics.jung.algorithms.scoring
            BarycenterScorer BetweennessCentrality ClosenessCentrality
            DegreeScorer EigenvectorCentrality HITS HITS$Scores PageRank]))

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
        (is (instance? HITS$Scores (score scorer 2)))))))
