(ns jungerer.io-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [jungerer.graph :as g]
            [jungerer.io :refer :all]))

(def sample-pairs-f (io/resource "sample.pairs"))

(deftest load-pairs!-test
  (testing "returns nil if loading is succeeded"
    (is (nil? (load-pairs! sample-pairs-f (g/directed-graph))))))
