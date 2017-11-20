(ns cljc-long.core
 (:refer-clojure :exclude [long
                           str
                           int
                           double
                           +
                           -
                           *
                           /
                           neg?
                           odd?
                           even?
                           zero?
                           mod
                           unchecked-negate
                           >
                           >=
                           =
                           <=
                           <
                           compare
                           unsigned-bit-shift-right
                           bit-shift-left
                           bit-shift-right
                           bit-and
                           bit-not
                           bit-or
                           bit-xor])

 (:require
  #?(:cljs goog.math.Long)
  cljc-long.type
  cljc-long.coerce
  cljc-long.arithmetic
  cljc-long.bitwise
  cljc-long.constants
  cljc-long.comparison))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(def long? cljc-long.type/long?)
(def long cljc-long.coerce/long)
(def str cljc-long.coerce/str)
(def int cljc-long.coerce/int)
(def double cljc-long.coerce/double)
(def string-in-range? cljc-long.coerce/string-in-range?)

(def max-value cljc-long.constants/max-value)
(def min-value cljc-long.constants/min-value)
(def zero cljc-long.constants/zero)
(def one cljc-long.constants/one)
(def neg-one cljc-long.constants/neg-one)
(def two-power-24 cljc-long.constants/two-power-24)

(def + cljc-long.arithmetic/+)
(def - cljc-long.arithmetic/-)
(def * cljc-long.arithmetic/*)
(def / cljc-long.arithmetic//)
(def neg? cljc-long.arithmetic/neg?)
(def odd? cljc-long.arithmetic/odd?)
(def even? cljc-long.arithmetic/even?)
(def zero? cljc-long.arithmetic/zero?)
(def mod cljc-long.arithmetic/mod)
(def unchecked-negate cljc-long.arithmetic/unchecked-negate)

(def bit-and cljc-long.bitwise/bit-and)
(def bit-not cljc-long.bitwise/bit-not)
(def bit-or cljc-long.bitwise/bit-or)
(def bit-xor cljc-long.bitwise/bit-xor)
(def bit-shift-left cljc-long.bitwise/bit-shift-left)
(def bit-shift-right cljc-long.bitwise/bit-shift-right)
(def unsigned-bit-shift-right cljc-long.bitwise/unsigned-bit-shift-right)
(def high-bits cljc-long.bitwise/high-bits)
(def low-bits cljc-long.bitwise/low-bits)
(def unsigned-low-bits cljc-long.bitwise/unsigned-low-bits)
(def absolute-number-bits cljc-long.bitwise/absolute-number-bits)
(def bit-rotate-left cljc-long.bitwise/bit-rotate-left)

(def > cljc-long.comparison/>)
(def >= cljc-long.comparison/>=)
(def = cljc-long.comparison/=)
(def <= cljc-long.comparison/<=)
(def < cljc-long.comparison/<)
(def compare cljc-long.comparison/compare)

(defn native-rand
 []
 #?(:cljs
    ; lifted from https://cljs.github.io/api/cljs.core/random-uuid
    (let [hex #(.toString (rand-int 16) 16)]
     (goog.math.Long.fromString
      (apply str (take 16 (repeatedly hex)))
      16))
    :clj
    (.nextLong (java.util.Random.))))
