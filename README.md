# jungerer

Clojure network/graph library wrapping [JUNG][jung].

[![Clojars Project](https://img.shields.io/clojars/v/jungerer.svg)](https://clojars.org/jungerer)
[![Build Status](https://travis-ci.org/totakke/jungerer.svg?branch=master)](https://travis-ci.org/totakke/jungerer)

**Caveat**: This library is now in developing. Not all functions are implemented yet.

## Usage

### Namespaces

* jungerer.graph
* jungerer.algo
* jungerer.vis

### Basics

jungerer.graph:

```clojure
(require '[jungerer.graph :as g])

(def graph (g/directed-sparse-graph [[1 2] [2 3] [4 2]])

(g/add-edge! graph [4 5])

(g/nodes graph)
=> #{1 4 3 2 5}

(g/edges graph)
=> ([1 2] [2 3] [4 5] [4 2])
```

jungerer.algo:

```clojure
(require '[jungerer.algo :as a])

(def pr (a/page-rank graph))

(a/score pr 2)
=> 0.06451612903225803
```

jungerer.vis:

```clojure
(require '[jungerer.vis :as v])

(v/view graph)

(v/view (v/kk-layout graph) {:title "My Graph", :frame-size [640 480]})
```

## License

Copyright © 2016 Toshiki Takeuchi

Distributed under the BSD 3-Clause License.

[jung]: http://jrtom.github.io/jung/
