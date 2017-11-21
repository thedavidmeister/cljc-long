# cljc-long

Signed 64 bit integers for Clojure(Script).

[![Clojars Project](https://img.shields.io/clojars/v/thedavidmeister/cljc-long.svg)](https://clojars.org/thedavidmeister/cljc-long)

Function names and behaviour follow clojure conventions, e.g. [`bit-xor`](https://clojuredocs.org/clojure.core/bit-xor) not just `xor`.

A few new functions have been added too, notably `long?` and `bit-rotate-left`.

## Supported functions by namespace

For convenience and API stability, every namespace is aliased into `cljc-long.core`. For example, `cljc-long.core/+` is a direct reference to `cljc-long.arithmetic/+`.

### Namespaces & functions

`cljc-long.arithmetic`

All these functions follow clojure core.

`+`, `-`, `*`, `/`, `neg?`, `odd?`, `even?`, `zero?`, `mod`, `unchecked-negate`.

`cljc-long.bitwise`

This is a mix of functions from clojure core, `goog.math.Long` and Java functionality.

From core:

`bit-and`, `bit-not`, `bit-or`, `bit-xor`, `bit-shift-left`, `bit-shift-right`, `unsigned-bit-shift-right`.

From `goog.math.Long`:

`high-bits`: The high 32-bits as a signed value.
`low-bits`: The low 32-bits as a signed value.
`unsigned-low-bits`: The low 32-bits as an unsigned value.
`absolute-number-bits`: Returns the number of bits needed to represent the absolute value of this Long.

From Java:

`bit-rotate-left`: As per `Long/rotateLeft`.

`cljc-long.coerce`

This is a set of convenience functions, mixing the features and normalising the limitations of clojure and `goog.math.Long`.

`long`: Coerce a long, string, tuple, 32 bit int or any other number to a `Long` or `goog.math.Long`. String coercion supports an optional radix parameter, e.g. `(cljc-long.coerce/long "10" 16) ; 16`

`str`: Coerce a long to a string. Supports an optional radix parameter, e.g. `(cljc-long.coerce/str (cljc-long.coerce/long 16) 16) ; "10"`

`int`: Coerce a long to a 32 bit int. Errors if value is out of 32 bit range, as per clojure.

`double`: Coerce a long to a double as per clojure.

`string-in-range?`: Returns true if passed a string that can be coerced to a 64 bit long. Supports an optional radix parameter.

`cljc-long.comparison`

These functions are all as per clojure.

`>`, `>=`, `=`, `not=`, `<=`, `<`, `compare`.

`cljc-long.constants`

These constants are all as per static methods of `goog.math.Long`.

`max-value`: Equivalent to `Long/MAX_VALUE` on the JVM.

`min-value`: Equivalent to `Long/MIN_VALUE` on the JVM.

`zero`: Zero as a long.

`one`: One as a long.

`neg-one`: Negative one as a long.

`two-power-24`: 2^24 as a long.

`cljc-long.type`

`long?`: Returns `true` if passed a value with type `Long` or `goog.math.Long`.

## Clojure

The JVM natively supports signed 64 bit integers as `Long` and clojure natively provides most of the wrappers we need here.

Many of the functions here are simple wrappers with type hinting for clojure core functions OR direct references to a core clojure function.

E.g.

```clojure
(defn +
 [^long a ^long b]
 (clojure.core/+ a b))
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

Notably we don't get access to the full 64 bits for integer operations. JavaScript's "integer" only supports [53 bits for regular arithmatic](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Number/MAX_SAFE_INTEGER) and [32 bits for bitwise operations](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Bitwise_Operators).

```clojure
; clojure/JVM
(inc 9007199254740992) ; 9007199254740993
(bit-shift-left 4294967296 1) ; 8589934592

; clojurescript/JS
(inc 9007199254740992) ; 9007199254740992
(bit-shift-left 4294967296 1) ; 0
```

Google Closure provides full 64 bit integer support via the `goog.math.Long` class, but the methods are a little clumsier than native clojure functions. For example, `(= a b c)` is valid in clojure but the native clojurescript equivalent would be something like `(and (.equals a b) (.equals b c))`.

This library provides consistently named variadic functions in clojurescript everywhere that there is an equivalent clojure function. This means that `(= a b c)` and similar will work for longs on both platforms.

Many of the functions here are wrappers around the `goog.math.Long` methods.

E.g.

```clojure
(defn bit-xor
 ([a b & xs]
  (reduce bit-xor (into [a b] xs)))
 ([a b]
  {:pre [(cljc-long.type/long? a)
         (cljc-long.type/long? b)]}
  (.xor a b))))
```

This abstraction is a little leaky though as the return values necessarily need to be `goog.math.Long` _objects_ rather than native floats.

Note that many of the functions only work correctly if all arguments passed are Google longs. I have added `:pre` checks where this is the case for safety, but there will be some small performance overhead here. You can always disable this by passing [`{:elide-asserts true}`](https://cljs.github.io/api/compiler-options/elide-asserts) to the cljs compiler.
