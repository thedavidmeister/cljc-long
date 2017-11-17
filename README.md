# cljc-long

Signed 64 bit integers for Clojure(Script).

Function names follow clojure conventions, e.g. [`bit-xor`](https://clojuredocs.org/clojure.core/bit-xor) not just `xor`.

A few new functions have been added too, e.g. `long?`.

If you want a new function added, please feel free to open a github issue!

## Clojure

The JVM natively supports signed 64 bit integers as `Long` and clojure natively provides most of the wrappers we need here.

Many of the functions here are simply direct references to clojure core functions.

E.g.

```clojure
(def bit-xor clojure.core/bit-xor)
```

## ClojureScript

JavaScript does _not_ natively support 64 bit _integers_ at all. Instead it provides [IEEE 754](https://en.wikipedia.org/wiki/Double-precision_floating-point_format) 64 bit _floats_. All the "integer" methods provided such as `Number.isInteger()` work on the _value_ of a float, not its type (there are no other number types in JavaScript).

In short, we get this scenario:

```clojure
; clojure/JVM
(integer? 1) ; true
(integer? 1.0) ; false

; clojurescript/JS
(integer? 1) ; true
(integer? 1.0) ; true
```

Most of the time the difference between integers and floats doesn't matter at all, but sometimes it really does matter and native JavaScript comes up short.

Notably we don't get access to the full 64 bits for integer operations. JavaScript "integers" only supports [53 bits for regular arithmatic](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Number/MAX_SAFE_INTEGER) and [32 bits for bitwise operations](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Bitwise_Operators).

```clojure
; clojure/JVM
(inc 9007199254740992) ; 9007199254740993
(bit-shift-left 4294967296 1) ; 8589934592

; clojurescript/JS
(inc 9007199254740992) ; 9007199254740992
(bit-shift-left 4294967296 1) ; 0
```

Google Closure provides full 64 bit integer support via the `goog.math.Long` class, but the methods are a little clumsier than native clojure functions. For example, `(= a b c)` is valid in clojure but the closure equivalent would be something like `(and (.equals a b) (.equals b c))`.

Arbitrary arities are currently not supported as `goog.math.Long` doesn't support arbitrary arities. [This might change in the future](https://github.com/thedavidmeister/cljc-long/issues/1).

Many of the functions here are wrappers around the `goog.math.Long` methods.

E.g.

```clojure
(defn bit-xor
  [a b]
  (.xor a b))
```

This abstraction is a little leaky though as the return values necessarily need to be `goog.math.Long` _objects_ rather than native floats.
