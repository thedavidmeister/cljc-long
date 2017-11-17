(ns cljc-long.core
 (:refer-clojure :exclude [long unsigned-bit-shift-right bit-shift-left bit-and bit-xor * + = -])
 #?(:cljs (:require goog.math.Long)))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(defn long? [a]
 #?(:cljs (instance? goog.math.Long a)
    :clj (instance? java.lang.Long a)))

(defn long
 [a]
 {:post [(long? %)]}
 (cond
  (long? a)
  a

  (string? a)
  #?(:cljs (goog.math.Long.fromString a 10)
     :clj (Long/parseLong a))

  (number? a)
  #?(:cljs (goog.math.Long.fromNumber a)
     :clj (cast Long a))))

(defn +
 [^long a ^long b]
 #?(:cljs (.add a b)
    :clj (clojure.core/+ a b)))

(defn -
 [^long a ^long b]
 #?(:cljs (.subtract a b)
    :clj (clojure.core/- a b)))

(defn *
 [^long a ^long b]
 #?(:cljs (.multiply a b)
    :clj (clojure.core/* a b)))

(defn =
 [^long a ^long b]
 #?(:cljs (.equals a b)
    :clj (clojure.core/= a b)))

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
