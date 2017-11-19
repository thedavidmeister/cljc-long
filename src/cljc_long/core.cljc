(ns cljc-long.core
 (:refer-clojure :exclude [long
                           +
                           -
                           *
                           /
                           >
                           >=
                           =
                           <=
                           <
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

(def > cljc-long.comparison/>)
(def >= cljc-long.comparison/>=)
(def = cljc-long.comparison/=)
(def <= cljc-long.comparison/<=)
(def < cljc-long.comparison/<)

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

(defn bit-xor
 [^long a ^long b]
 #?(:cljs (.xor a b)
    :clj (clojure.core/bit-xor a b)))

(defn bit-and
 [^long a ^long b]
 #?(:cljs (.and a b)
    :clj (clojure.core/bit-and a b)))

(defn bit-shift-left
 [^long a ^long n]
 #?(:cljs (.shiftLeft a n)
    :clj (clojure.core/bit-shift-left a n)))

(defn unsigned-bit-shift-right
 [^long a ^long n]
 #?(:cljs (.shiftRightUnsigned a n)
    :clj (clojure.core/unsigned-bit-shift-right a n)))

; @see int-rotate-left
; https://github.com/clojure/clojurescript/blob/master/src/main/cljs/cljs/core.cljs#L879
(defn bit-rotate-left
 [^long x ^long n]
 #?(:cljs
    (.or
     (.shiftLeft x n)
     (.shiftRightUnsigned x (clojure.core/- n)))
    :clj
    (Long/rotateLeft x n)))
