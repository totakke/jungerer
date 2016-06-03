(ns jungerer.graph
  (:import [edu.uci.ics.jung.graph DirectedSparseGraph
                                   DirectedSparseMultigraph
                                   Graph
                                   Hypergraph
                                   UndirectedSparseGraph
                                   UndirectedSparseMultigraph]
           edu.uci.ics.jung.graph.util.Pair))

;; FIXME
(defn- edge->j-edge
  [edge]
  (let [[a b] edge]
    (str a b)))

(defn- j-edge->edge
  [^Graph graph j-edge]
  (let [pair ^Pair (.getEndpoints graph j-edge)]
    [(.getFirst pair) (.getSecond pair)]))

;; Constructor
;; -----------

(defn directed-graph
  ([] (DirectedSparseGraph.))
  ([edges]
   (let [graph (DirectedSparseGraph.)]
     (doseq [[a b :as edge] edges]
       (doto graph
         (.addVertex a)
         (.addVertex b)
         (.addEdge (edge->j-edge edge) a b)))
     graph)))

(defn directed-multigraph
  ([] (DirectedSparseMultigraph.))
  ([edges]
   (let [graph (DirectedSparseMultigraph.)]
     (doseq [[a b :as edge] edges]
       (doto graph
         (.addVertex a)
         (.addVertex b)
         (.addEdge (edge->j-edge edge) a b)))
     graph)))

(defn undirected-graph
  ([] (UndirectedSparseGraph.))
  ([edges]
   (let [graph (UndirectedSparseGraph.)]
     (doseq [[a b :as edge] edges]
       (doto graph
         (.addVertex a)
         (.addVertex b)
         (.addEdge (edge->j-edge edge) a b)))
     graph)))

(defn undirected-multigraph
  ([] (UndirectedSparseMultigraph.))
  ([edges]
   (let [graph (UndirectedSparseMultigraph.)]
     (doseq [[a b :as edge] edges]
       (doto graph
         (.addVertex a)
         (.addVertex b)
         (.addEdge (edge->j-edge edge) a b)))
     graph)))

;; Manipulation
;; ------------

(defn add-node!
  [^Hypergraph graph node]
  (.addVertex graph node))

(defn remove-node!
  [^Hypergraph graph node]
  (.removeVertex graph node))

(defn contains-node?
  [^Hypergraph graph node]
  (.containsVertex graph node))

(defn add-edge!
  [^Graph graph edge]
  (let [[a b] edge]
    (.addEdge graph (edge->j-edge edge) a b)))

(defn remove-edge!
  [^Graph graph edge]
  (.removeEdge graph (edge->j-edge edge)))

(defn contains-edge?
  [^Hypergraph graph edge]
  (.containsEdge graph (edge->j-edge edge)))

(defn nodes
  [^Graph graph]
  (set (.getVertices graph)))

(defn edges
  [^Graph graph]
  (map (partial j-edge->edge graph) (.getEdges graph)))

(defn in-edges
  [^Graph graph node]
  (map (partial j-edge->edge graph) (.getInEdges graph node)))

(defn degree
  [^Hypergraph graph node]
  (.degree graph node))

(defn in-degree
  [^Hypergraph graph node]
  (.inDegree graph node))

(defn out-degree
  [^Hypergraph graph node]
  (.outDegree graph node))
