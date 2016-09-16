(defproject jungerer "0.1.4-SNAPSHOT"
  :description "Clojure network/graph library wrapping JUNG"
  :url "https://github.com/totakke/jungerer"
  :license {:name "The BSD 3-Clause License"
            :url "https://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [net.sf.jung/jung-algorithms "2.1"]
                 [net.sf.jung/jung-api "2.1"]
                 [net.sf.jung/jung-graph-impl "2.1"]
                 [net.sf.jung/jung-io "2.1"]
                 [net.sf.jung/jung-visualization "2.1"]]
  :profiles {:dev {:global-vars {*warn-on-reflection* true}
                   :resource-paths ["dev-resources"]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]
                   :resource-paths ["dev-resources"]}}
  :deploy-repositories [["snapshots" {:url "https://clojars.org/repo/"
                                      :username [:env/clojars_username :gpg]
                                      :password [:env/clojars_password :gpg]}]]
  :codox {:source-uri "https://github.com/totakke/jungerer/blob/{version}/{filepath}#L{line}"})
