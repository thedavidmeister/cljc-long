(ns cljc-long.type
 (:refer-clojure :exclude [long? long int double str])
 #?(:cljs (:require goog.math.Long)))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(defn long? [a]
 #?(:cljs (instance? goog.math.Long a)
    :clj (instance? Long a)))

(defn long
 ([s r]
  {:pre [(string? s)
         (integer? r)]}
  #?(:cljs (goog.math.Long.fromString s r)
     :clj (Long/parseLong s r)))
 ([a]
  {:post [(long? %)]}
  (cond
   (long? a)
   a

   (string? a)
   (long a 10)

   (== a (clojure.core/int a))
   #?(:cljs (goog.math.Long.fromInt a)
      :clj (clojure.core/long a))

   (number? a)
   #?(:cljs (goog.math.Long.fromNumber a)
      :clj (clojure.core/long a))

   (sequential? a)
   #?(:cljs (apply goog.math.Long/fromBits a)
      :clj
      ; @todo
      a))))

#?(:clj (def int clojure.core/int)
   :cljs
   (defn int
    [a]
    (.toInt a)))

#?(:clj (def double clojure.core/double)
   :cljs
   (defn double
    [a]
    (.toNumber a)))

(defn string-in-range?
 ([s] (string-in-range? s 10))
 ([s r]
  #?(:cljs (goog.math.Long.isStringInRange s r))))

(defn str
 ([^long a]
  (str a 10))
 ([^long a ^long radix]
  #?(:clj
     (Long/toString a radix)
     :cljs
     (.toString a radix))))
