(ns jungerer.graph
  "Functions to create and manipulate a graph."
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
  "Returns an edu.uci.ic.jung.graph.DirectedSparseGraph. If a list or vector
  of edges is passed, returning graph including the edges."
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
  "Returns an edu.uci.ic.jung.graph.DirectedSparseMultigraph. If a list or
  vector of edges is passed, returning graph including the edges."
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
  "Returns an edu.uci.ic.jung.graph.UndirectedSparseGraph. If a list or vector
  of edges is passed, returning graph including the edges."
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
  "Returns an edu.uci.ic.jung.graph.UndirectedSparseMultigraph. If a list or
  vector of edges is passed, returning graph including the edges."
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
  "Adds node to graph. Returns true if the addition suceeded. Note that this
  function is not immutable, changing the graph state."
  [^Hypergraph graph node]
  (.addVertex graph node))

(defn remove-node!
  "Removes node from graph. Returns true if the removal suceeded. As a side
  effect, removes any edges incident to the node if the removal of the node
  would cause the edge to be incident to an illegal number of nodes. Note that
  this function is not immutable, changing the graph state."
  [^Hypergraph graph node]
  (.removeVertex graph node))

(defn contains-node?
  "Returns true if graph's node collection contains node."
  [^Hypergraph graph node]
  (.containsVertex graph node))

(defn add-edge!
  "Adds edge to graph. Returns true if the addition suceeded. Note that this
  function is not immutable, changing the graph state."
  [^Graph graph edge]
  (let [[a b] edge]
    (.addEdge graph (edge->j-edge edge) a b)))

(defn remove-edge!
  "Removes edge from graph. Returns true if the removal suceeded. Note that this
  function is not immutable, changing the graph state."
  [^Graph graph edge]
  (.removeEdge graph (edge->j-edge edge)))

(defn contains-edge?
  "Returns true if graph's edge collection contains edge."
  [^Hypergraph graph edge]
  (.containsEdge graph (edge->j-edge edge)))

(defn nodes
  "Returns all nodes in graph as set."
  [^Graph graph]
  (set (.getVertices graph)))

(defn edges
  "Returns all edges in graph."
  [^Graph graph]
  (map (partial j-edge->edge graph) (.getEdges graph)))

(defn in-edges
  "Returns incoming edges incident to node in graph."
  [^Graph graph node]
  (map (partial j-edge->edge graph) (.getInEdges graph node)))

(defn degree
  "Returns the number of edges incident to node."
  [^Hypergraph graph node]
  (.degree graph node))

(defn in-degree
  "Returns the number of incoming edges incident to node."
  [^Hypergraph graph node]
  (.inDegree graph node))

(defn out-degree
  "Returns the number of outgoing edges incident to node."
  [^Hypergraph graph node]
  (.outDegree graph node))

(defn successors
  "Returns the successors of node in graph as set. A successor of node is
  defined as a node v which is connected to node by an edge e, where e is an
  incoming edge of v and an outgoing edge of node."
  [^Hypergraph graph node]
  (set (.getSuccessors graph node)))

(defn predecessors
  "Returns the predecessors of node in graph as set. A predecessor of node is
  defined as a node v which is connected to node by an edge e, where e is an
  outgoing edge of v and an incoming edge of node."
  [^Hypergraph graph node]
  (set (.getPredecessors graph node)))
