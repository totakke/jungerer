(ns jungerer.algo
  (:import [edu.uci.ics.jung.graph Graph Hypergraph]
           [edu.uci.ics.jung.algorithms.scoring
            BarycenterScorer BetweennessCentrality ClosenessCentrality
            DegreeScorer EigenvectorCentrality HITS PageRank VertexScorer]
           [edu.uci.ics.jung.algorithms.shortestpath DijkstraDistance
                                                     DijkstraShortestPath
                                                     Distance
                                                     UnweightedShortestPath]))

;; Distance
;; --------

(defn dijkstra-distance
  ([graph]
   (DijkstraDistance. graph))
  ([^Graph graph ^Boolean cached]
   (DijkstraDistance. graph cached)))

(defn dijkstra-shortest-path
  ([graph]
   (DijkstraShortestPath. graph))
  ([^Graph graph ^Boolean cached]
   (DijkstraShortestPath. graph cached)))

(defn unweighted-shortest-path
  [graph]
  (UnweightedShortestPath. graph))

;; Scorer
;; ------

(defn barycenter-scorer
  [graph]
  (BarycenterScorer. graph))

(defn degree-scorer
  [graph]
  (DegreeScorer. graph))

(defn betweenness-centrality
  [graph]
  (BetweennessCentrality. graph))

(defn closeness-centrality
  ([graph]
   (ClosenessCentrality. graph))
  ([^Hypergraph graph ^Distance distance]
   (ClosenessCentrality. graph distance)))

(defn eigenvector-centrality
  [graph]
  (EigenvectorCentrality. graph))

(defn page-rank
  ([graph]
   (page-rank graph 0.15))
  ([graph alpha]
   (doto (PageRank. graph alpha)
     (.evaluate))))

(defn hits
  [graph]
  (doto (HITS. graph)
    (.evaluate)))

;;

(defn score
  [^VertexScorer scorer node]
  (.getVertexScore scorer node))
