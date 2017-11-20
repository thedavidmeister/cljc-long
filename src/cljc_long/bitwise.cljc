(ns cljc-long.bitwise
 (:refer-clojure :exclude [bit-and bit-not bit-or bit-xor bit-shift-left bit-shift-right unsigned-bit-shift-right])
 (:require
  cljc-long.type
  cljc-long.comparison
  cljc-long.arithmetic
  cljc-long.constants))

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
    {:pre [(cljc-long.type/long? a)]}
    (.not a)))

#?(:clj (def bit-or clojure.core/bit-or)
   :cljs
   (defn bit-or
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.or a b)))

#?(:clj (def bit-xor clojure.core/bit-xor)
   :cljs
   (defn bit-xor
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.xor a b)))

#?(:clj (def bit-shift-left clojure.core/bit-shift-left)
   :cljs
   (defn bit-shift-left
    [a n]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? n)]}
    (.shiftLeft a n)))

#?(:clj (def bit-shift-right clojure.core/bit-shift-right)
   :cljs
   (defn bit-shift-right
    [a n]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? n)]}
    (.shiftRight a n)))

#?(:clj (def unsigned-bit-shift-right clojure.core/unsigned-bit-shift-right)
   :cljs
   (defn unsigned-bit-shift-right
    [a n]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? n)]}
    (.shiftRightUnsigned a n)))

(defn high-bits
 [^long a]
 {:pre [(cljc-long.type/long? a)]}
 #?(:cljs (.getHighBits a)
    :clj (bit-shift-right a 32)))

(defn low-bits
 [^long a]
 {:pre [(cljc-long.type/long? a)]}
 #?(:cljs (.getLowBits a)
    :clj
    (bit-shift-right
     (bit-shift-left a 32)
     32)))

(defn unsigned-low-bits
 [^long a]
 {:pre [(cljc-long.type/long? a)]}
 #?(:cljs (.getLowBitsUnsigned a)
    :clj
    (unsigned-bit-shift-right
     (bit-shift-left a 32)
     32)))

(defn absolute-number-bits
 [^long a]
 {:pre [(cljc-long.type/long? a)]}
 #?(:cljs (.getNumBitsAbs a)
    :clj
    ; mirror internal goog.math.Long logic for negs
    (if (cljc-long.arithmetic/neg? a)
     (if (cljc-long.comparison/= a cljc-long.constants/min-value)
      64
      (absolute-number-bits (cljc-long.arithmetic/unchecked-negate a)))
     ; https://stackoverflow.com/questions/4300066/number-of-bits-used-in-long-java
     (-
      Long/SIZE
      (Long/numberOfLeadingZeros a)))))

; @see int-rotate-left
; https://github.com/clojure/clojurescript/blob/master/src/main/cljs/cljs/core.cljs#L879
(defn bit-rotate-left
 [^long x ^long n]
 {:pre [(cljc-long.type/long? x)
        (cljc-long.type/long? n)]}
 #?(:cljs
    (.or
     (.shiftLeft x n)
     (.shiftRightUnsigned x (clojure.core/- n)))
    :clj
    (Long/rotateLeft x n)))
