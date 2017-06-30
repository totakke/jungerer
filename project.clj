(defproject jungerer "0.4.1"
  :description "Clojure network/graph library wrapping JUNG"
  :url "https://github.com/totakke/jungerer"
  :license {:name "The BSD 3-Clause License"
            :url "https://opensource.org/licenses/BSD-3-Clause"}
  :min-lein-version "2.7.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [net.sf.jung/jung-algorithms "2.1.1"]
                 [net.sf.jung/jung-api "2.1.1"]
                 [net.sf.jung/jung-graph-impl "2.1.1"]
                 [net.sf.jung/jung-io "2.1.1"]
                 [net.sf.jung/jung-visualization "2.1.1"]]
  :profiles {:dev {:global-vars {*warn-on-reflection* true}}
             :1.9 {:dependencies [[org.clojure/clojure "1.9.0-alpha12"]]}
             :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}}
  :deploy-repositories [["snapshots" {:url "https://clojars.org/repo/"
                                      :username [:env/clojars_username :gpg]
                                      :password [:env/clojars_password :gpg]}]]
  :codox {:source-uri "https://github.com/totakke/jungerer/blob/{version}/{filepath}#L{line}"})
