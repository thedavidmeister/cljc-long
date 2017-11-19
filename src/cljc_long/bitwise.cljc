(ns cljc-long.bitwise
 (:refer-clojure :exclude [bit-shift-left bit-shift-right])
 (:require
  cljc-long.type))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

#?(:clj (def bit-and clojure.core/bit-and)
   :cljs
   (defn bit-and
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.and a b)))

#?(:clj (def bit-not clojure.core/bit-not)
   :cljs
   (defn bit-not
    [a]
    (.not a)))

#?(:clj (def bit-or clojure.core/bit-or)
   :cljs
   (defn bit-or
    [a b]
    (.or a b)))

#?(:clj (def bit-xor clojure.core/bit-xor)
   :cljs
   (defn bit-xor
    [a b]
    (.xor a b)))

#?(:clj (def bit-shift-left clojure.core/bit-shift-left)
   :cljs
   (defn bit-shift-left
    [a n]
    (.shiftLeft a n)))

#?(:clj (def bit-shift-right clojure.core/bit-shift-right)
   :cljs
   (defn bit-shift-right
    [a n]
    (.shiftRight a n)))

#?(:clj (def unsigned-bit-shift-right clojure.core/unsigned-bit-shift-right)
   :cljs
   (defn unsigned-bit-shift-right
    [a n]
    (.shiftRightUnsigned a n)))

(defn high-bits
 [^long a]
 #?(:cljs (.getHighBits a)))

(defn low-bits
 [^long a]
 #?(:cljs (.getLowBits a)))

(defn low-bits-unsigned
 [^long a]
 #?(:cljs (.getLowBitsUnsigned a)))

(defn absolute-number-bits
 [^long a]
 #?(:cljs (.getNumBitsAbs a)))

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
