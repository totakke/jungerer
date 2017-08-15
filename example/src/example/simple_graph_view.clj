(ns example.simple-graph-view
  "The simple work necessary to load and visualize a graph."
  (:require [clojure.java.io :as io]
            [jungerer.graph :as g]
            [jungerer.io :as i]
            [jungerer.vis :as v])
  (:gen-class))

(defn -main [& args]
  (let [graph (g/directed-graph)]
    (i/load-pairs! (io/resource "sample.pairs") graph)
    (v/view (v/fr-layout graph))))
