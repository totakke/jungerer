# Jungerer

[![Clojars Project](https://img.shields.io/clojars/v/jungerer.svg)](https://clojars.org/jungerer)
[![Build Status](https://travis-ci.org/totakke/jungerer.svg?branch=master)](https://travis-ci.org/totakke/jungerer)

Clojure network/graph library wrapping [JUNG][jung].

## Installation

Jungerer is available as a Maven artifact from [Clojars](https://clojars.org/jungerer).

With Leiningen/Boot:

```clojure
[jungerer "0.4.1"]
```

## Usage

### Namespaces

* [jungerer.graph][jungerer.graph] - creates and manipulates graph
* [jungerer.algo][jungerer.algo] - calculates scores such as PageRank
* [jungerer.vis][jungerer.vis] - visualizes graph on Swing
* [jungerer.io][jungerer.io] - load/saves external graph formats

### Documentation

[API Reference](https://totakke.github.io/jungerer/)

### Example

See [example project](https://github.com/totakke/jungerer/tree/master/example) to quickly try Jungerer.

### Basics

jungerer.graph:

```clojure
(require '[jungerer.graph :as g])

(def graph (g/directed-graph [[1 2] [2 3] [4 2]]))

(g/add-edge! graph [4 5])

(g/nodes graph)
;;=> #{1 4 3 2 5}

(g/edges graph)
;;=> ([1 2] [2 3] [4 5] [4 2])
```

jungerer.algo:

```clojure
(require '[jungerer.algo :as a])

(def scorer (a/page-rank graph))

(a/score scorer 2)
;;=> 0.26350079629361517

(a/dijkstra-path graph 4 3)
;;=> [4 2 3]
```

jungerer.vis:

```clojure
(require '[jungerer.vis :as v])

;; To visualize simply,
(v/view graph)

;; To specify layout and other options,
(v/view (v/kk-layout graph) {:title "My Graph"
                             :frame-size [640 480]
                             :node-label-fn (fn [node]
                                              (str "node" node))
                             :edge-label-fn (fn [[node1 node2]]
                                              (str node1 " -> " node2))})
```

jungerer.io:

```clojure
(require '[jungerer.io :as i])

(def graph (g/directed-graph))

(i/load-pairs! "dev-resources/sample.pairs" graph)

(g/add-edge! graph ["AAA" "LLL"])

(i/save-as-pairs "/tmp/sample-edited.pairs" graph)
```

## License

Copyright © 2016-2019 [Toshiki Takeuchi](https://github.com/totakke)

Distributed under the BSD 3-Clause License.

[jung]: http://jrtom.github.io/jung/
[jungerer.graph]: https://github.com/totakke/jungerer/blob/master/src/jungerer/graph.clj
[jungerer.algo]: https://github.com/totakke/jungerer/blob/master/src/jungerer/algo.clj
[jungerer.vis]: https://github.com/totakke/jungerer/blob/master/src/jungerer/vis.clj
[jungerer.io]: https://github.com/totakke/jungerer/blob/master/src/jungerer/io.clj
