(ns jungerer.algo
  "Functions to find a path and calculate a score of a graph."
  (:import [edu.uci.ics.jung.graph Graph Hypergraph]
           [edu.uci.ics.jung.algorithms.scoring
            BarycenterScorer BetweennessCentrality ClosenessCentrality
            DegreeScorer EigenvectorCentrality HITS HITS$Scores PageRank VertexScorer]
           [edu.uci.ics.jung.algorithms.shortestpath DijkstraShortestPath
                                                     Distance
                                                     ShortestPathUtils
                                                     UnweightedShortestPath]))

;; Path finding
;; ------------

(defn bf-path
  "Returns a vector of the nodes on the shortest path from src-node to dst-node,
  in order of their occurrence on this path, using Breadth-First Search (BFS)
  algorithm. Returns an empty vector if the path does not exist."
  [^Hypergraph graph src-node dst-node]
  (if-not (.containsVertex graph src-node)
    (throw (IllegalArgumentException.
            (str "Specified source node " src-node " is not part of graph " graph))))
  (if-not (.containsVertex graph dst-node)
    (throw (IllegalArgumentException.
            (str "Specified destination node " dst-node " is not part of graph " graph))))
  (if (= src-node dst-node)
    [src-node]
    (let [jedges (ShortestPathUtils/getPath graph
                                            (UnweightedShortestPath. graph)
                                            src-node
                                            dst-node)]
      (if (seq jedges)
        (conj (mapv #(.getSource graph %) jedges) dst-node)
        []))))

(defn dijkstra-path
  "Returns a vector of the nodes on the shortest path from src-node to dst-node,
  in order of their occurrence on this path, using Dijkstra's single-source-
  shortest-path algorithm. Returns an empty vector if the path does not exist."
  [^Hypergraph graph src-node dst-node]
  (if (= src-node dst-node)
    [src-node]
    (let [jedges (.. (DijkstraShortestPath. graph) (getPath src-node dst-node))]
      (if (seq jedges)
        (conj (mapv #(.getSource graph %) jedges) dst-node)
        []))))

;; Scorer
;; ------

(defn barycenter-scorer
  "Returns an edu.uci.ics.jung.algorithms.scoring.BarycenterScorer for graph. It
  can be used for calculating scores for each node according to the sum of its
  distances to all other nodes."
  [graph]
  (BarycenterScorer. graph))

(defn degree-scorer
  "Returns an edu.uci.ics.jung.algorithms.scoring.DegreeScorer for graph. It can
  be used for calculating scores for each node equal to its degree."
  [graph]
  (DegreeScorer. graph))

(defn betweenness-centrality
  "Returns an edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality for
  graph. It can be used for calculating betweenness centrality for each node and
  edge in the graph."
  [graph]
  (BetweennessCentrality. graph))

(defn closeness-centrality
  "Returns an edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality for graph.
  It can be used for calculating scores for each node based on the mean distance
  to each other node."
  ([graph]
   (ClosenessCentrality. graph))
  ([^Hypergraph graph ^Distance distance]
   (ClosenessCentrality. graph distance)))

(defn eigenvector-centrality
  "Returns an edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality for
  graph. It can be used for calculating eigenvector centrality for each node in
  the graph. The 'eigenvector centrality' for a node is defined as the fraction
  of time that a random walk(er) will spend at that node over an infinite time
  horizon. Assumes that the graph is strongly connected."
  [graph]
  (EigenvectorCentrality. graph))

(defn page-rank
  "Returns an edu.uci.ics.jung.algorithms.scoring.PageRank for graph. It can be
  used for calculating scores for each node according to the PageRank algorithm."
  ([graph]
   (page-rank graph 0.15))
  ([graph alpha]
   (doto (PageRank. graph alpha)
     (.evaluate))))

(defn hits
  "Returns an edu.uci.ics.jung.algorithms.scoring.HITS for graph. It can be used
  for calculating hub and authority scores to each node depending on the
  topology of the network."
  [graph]
  (doto (HITS. graph)
    (.evaluate)))

;;

(defprotocol ScoreAvailable
  (score [this node]
    "Returns a score of node calculated with scorer algorithm. Note that type of
  the returned score is different depending on the scorer."))

(extend-type HITS
  ScoreAvailable
  (score [this node]
    (let [^HITS$Scores hits-scores (.getVertexScore this node)]
      {:hub (.hub hits-scores)
       :authority (.authority hits-scores)})))

(extend-type VertexScorer
  ScoreAvailable
  (score [this node]
    (.getVertexScore this node)))
