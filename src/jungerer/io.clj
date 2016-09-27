(ns jungerer.io
  "Functions to load/save external graph formats."
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [jungerer.graph :as g])
  (:import com.google.common.base.Function
           edu.uci.ics.jung.graph.Hypergraph
           [edu.uci.ics.jung.io GraphMLReader GraphMLWriter]))

(defn load-pairs!
  [f graph]
  (with-open [r (io/reader f)]
    (doseq [edge (->> (line-seq r)
                      (map #(string/split % #"\t")))]
      (g/add-edge! graph edge)))
  nil)

(defn save-as-pairs
  [f graph]
  (with-open [w (io/writer f)]
    (doseq [[a b] (g/edges graph)]
      (.write w (str a \tab b \newline)))))

(defn load-graphml!
  [^String f ^Hypergraph graph]
  (.load (GraphMLReader.) f graph))

(defn save-as-graphml
  [f graph]
  (doto (GraphMLWriter.)
    (.setEdgeIDs (reify Function
                   (apply [_ edge]
                     edge)))
    (.save graph (io/writer f)))
  nil)
