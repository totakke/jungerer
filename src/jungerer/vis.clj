(ns jungerer.vis
  (:import java.awt.Dimension
           javax.swing.JFrame
           [edu.uci.ics.jung.algorithms.layout CircleLayout FRLayout ISOMLayout
                                               KKLayout Layout SpringLayout]
           edu.uci.ics.jung.graph.Graph
           edu.uci.ics.jung.visualization.VisualizationViewer))

;; Layout
;; ------

(defn ^CircleLayout circle-layout
  [graph]
  (CircleLayout. graph))

(defn ^FRLayout fr-layout
  [graph]
  (FRLayout. graph))

(defn ^ISOMLayout isom-layout
  [graph]
  (ISOMLayout. graph))

(defn ^KKLayout kk-layout
  [graph]
  (KKLayout. graph))

(defn ^SpringLayout spring-layout
  [graph]
  (SpringLayout. graph))

;; Visualization
;; -------------

(defprotocol Visualizable
  (view [this] [this option]))

(extend-type Graph
  Visualizable
  (view
    ([this]
     (view (circle-layout this) {}))
    ([this {:keys [layout-size] :as option}]
     (let [layout (circle-layout this)]
       (when layout-size
         (.setSize layout (Dimension. (first layout-size) (second layout-size))))
       (view layout option)))))

(extend-type Layout
  Visualizable
  (view
    ([this]
     (view this {}))
    ([this {:keys [^String title frame-size]}]
     (let [vv (VisualizationViewer. this)
           frame (JFrame. title)]
       (when frame-size
         (.setPreferredSize vv (Dimension. (first frame-size) (second frame-size))))
       (.. frame getContentPane (add vv))
       (.pack frame)
       (.setVisible frame true)))))
