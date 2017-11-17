# cljc-long

Signed 64 bit integers for Clojure(Script).

Function names follow clojure conventions, e.g. [`bit-xor`](https://clojuredocs.org/clojure.core/bit-xor) not just `xor`.

A few new functions have been added too, e.g. `long?`.

If you want a new function added, please feel free to open a github issue!

## Clojure

The JVM natively supports signed 64 bit integers as `Long` and clojure natively provides most of the wrappers we need here.

Many of the functions here are simply direct references to clojure core functions.

E.g.

```
(def bit-xor clojure.core/bit-xor)
```

## ClojureScript

JavaScript does _not_ natively support signed 64 bit _integers_. Instead it provides [IEEE 754](https://en.wikipedia.org/wiki/Double-precision_floating-point_format) 64 bit _floats_.

Google Closure provides 64 bit integer support via the `goog.math.Long` class, but the methods are a little clumsier than native clojure functions.

Many of the functions here are wrappers around the `goog.math.Long` methods.

E.g.

```
(defn bit-xor
  [a b]
  (.xor a b))
```
