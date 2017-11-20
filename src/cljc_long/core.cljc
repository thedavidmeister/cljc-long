(ns cljc-long.core
 (:refer-clojure :exclude [long
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
                           bit-and
                           bit-xor])

 (:require
  #?(:cljs goog.math.Long)
  cljc-long.type
  cljc-long.arithmetic
  cljc-long.constants
  cljc-long.comparison))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(def long? cljc-long.type/long?)
(def long cljc-long.type/long)

(def max-value cljc-long.constants/max-value)
(def min-value cljc-long.constants/min-value)

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
