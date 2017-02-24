(ns jungerer.vis
  "Functions to visualize a graph on Swing."
  (:require [jungerer.graph :as g])
  (:import java.awt.Dimension
           javax.swing.JFrame
           [edu.uci.ics.jung.algorithms.layout CircleLayout FRLayout ISOMLayout
                                               KKLayout Layout SpringLayout]
           edu.uci.ics.jung.graph.Graph
           edu.uci.ics.jung.visualization.VisualizationViewer
           edu.uci.ics.jung.visualization.decorators.ToStringLabeller))

;; Layout
;; ------

(defn ^CircleLayout circle-layout
  "Returns an edu.uci.ics.jung.algorithms.layout.CircleLayout for graph. This
  layout positions nodes equally spaced on a regular circle."
  [graph]
  (CircleLayout. graph))

(defn ^FRLayout fr-layout
  "Returns an edu.uci.ics.jung.algorithms.layout.FRLayout for graph. This layout
  implements the Fruchterman-Reingold force-directed algorithm for node layout."
  [graph]
  (FRLayout. graph))

(defn ^ISOMLayout isom-layout
  "Returns an edu.uci.ics.jung.algorithms.layout.ISOMLayout for graph. This
  layout implements a self-organizing map layout algorithm, based on Meyer's
  self-organizing graph methods."
  [graph]
  (ISOMLayout. graph))

(defn ^KKLayout kk-layout
  "Returns an edu.uci.ics.jung.algorithms.layout.KKLayout for graph. This layout
  implements the Kamada-Kawai algorithm for node layout. Does not respect filter
  calls, and sometimes crashes when the view changes to it."
  [graph]
  (KKLayout. graph))

(defn ^SpringLayout spring-layout
  "Returns an edu.uci.ics.jung.algorithms.layout.SpringLayout for graph."
  [graph]
  (SpringLayout. graph))

;; Visualization
;; -------------

(defprotocol Visualizable
  (view [this] [this option]
    "Visualizes graph on Swing. The first argument must be
  edu.uci.ics.jung.graph.Graph or edu.uci.ics.jung.algorithms.layout.Layout. The
  optional second argument is an option map including :title, :frame-size,
  :layout-size, :node-label-fn, and :edge-label-fn."))

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
    ([this {:keys [^String title frame-size node-label-fn edge-label-fn]
            :or {node-label-fn str, edge-label-fn str}}]
     (let [vv (VisualizationViewer. this)
           frame (JFrame. title)]
       (when frame-size
         (.setPreferredSize vv (Dimension. (first frame-size) (second frame-size))))
       (doto (.getRenderContext vv)
         (.setVertexLabelTransformer (proxy [ToStringLabeller] []
                                       (apply [node]
                                         ((comp str node-label-fn) node))))
         (.setEdgeLabelTransformer (proxy [ToStringLabeller] []
                                     (apply [inner-edge]
                                       ((comp str edge-label-fn g/->edge) inner-edge)))))
       (.setDefaultCloseOperation frame JFrame/DISPOSE_ON_CLOSE)
       (.. frame getContentPane (add vv))
       (.pack frame)
       (.setVisible frame true)))))
