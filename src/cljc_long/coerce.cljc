(ns cljc-long.coerce
 (:refer-clojure :exclude [long int double str])
 (:require
  cljc-long.type
  cljc-long.bitwise
  cljc-long.comparison
  #?(:cljs goog.math.Long)))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(defn long
 ([s r]
  {:pre [(string? s)
         (integer? r)]}
  #?(:cljs (goog.math.Long.fromString s r)
     :clj (Long/parseLong s r)))
 ([a]
  {:post [(cljc-long.type/long? %)]}
  (cond
   (cljc-long.type/long? a)
   a

   (string? a)
   (long a 10)

   (sequential? a)
   #?(:cljs (apply goog.math.Long/fromBits a)
      :clj
      ; https://stackoverflow.com/questions/10686178/convert-long-to-two-int-and-vice-versa
      (let [[l h] a]
       ; use core bitwise fns to avoid circular deps on the ns
       (cljc-long.bitwise/bit-or
        (cljc-long.bitwise/bit-shift-left ^long (long h) 32)
        (cljc-long.bitwise/unsigned-bit-shift-right
         (cljc-long.bitwise/bit-shift-left
          ^long (long l)
          ^long (long 1))
         ^long (long 1)))))

   (= a (clojure.core/int a))
   #?(:cljs (goog.math.Long.fromInt a)
      :clj (clojure.core/long a))

   (number? a)
   #?(:cljs (goog.math.Long.fromNumber a)
      :clj (clojure.core/long a)))))

(defn str
 ([^long a]
  (str a (long 10)))
 ([^long a ^long radix]
  {:pre [(cljc-long.type/long? a)
         (cljc-long.type/long? radix)]}
  #?(:clj
     (Long/toString a radix)
     :cljs
     (.toString a radix))))

#?(:clj (def int clojure.core/int)
   :cljs
   (defn int
    [a]
    {:pre [(cljc-long.type/long? a)]}
    (when-not
     ; JVM errors if int is out of range, but goog.math.Long returns low
     ; bits. We are following JVM and erroring here.
     (cljc-long.comparison/=
      a
      (long (cljc-long.bitwise/low-bits a)))
     (throw (js/Error (clojure.core/str "Value out of range for int: " (str a)))))
    (.toInt a)))

#?(:clj (def double clojure.core/double)
   :cljs
   (defn double
    [a]
    {:pre [(cljc-long.type/long? a)]}
    (.toNumber a)))

(defn string-in-range?
 ([s] (string-in-range? s (long 10)))
 ([s r]
  {:pre [(string? s)
         (cljc-long.type/long? r)]}
  #?(:cljs (goog.math.Long.isStringInRange s r))))
