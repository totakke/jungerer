(defproject jungerer "0.1.1-SNAPSHOT"
  :description "Clojure network/graph library wrapping JUNG"
  :url "https://github.com/totakke/jungerer"
  :license {:name "The BSD 3-Clause License"
            :url "https://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [net.sf.jung/jung-algorithms "2.1"]
                 [net.sf.jung/jung-api "2.1"]
                 [net.sf.jung/jung-graph-impl "2.1"]
                 [net.sf.jung/jung-visualization "2.1"]]
  :profiles {:dev {:global-vars {*warn-on-reflection* true}}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}})
