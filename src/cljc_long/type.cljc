(ns cljc-long.type
 (:refer-clojure :exclude [long? long int double str]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(defn long? [a]
 #?(:cljs (instance? goog.math.Long a)
    :clj (instance? Long a)))

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

(defn str
 ([^long a] (str a 10))
 ([^long a ^long radix]
  #?(:clj
     (Long/toString a radix)
     :cljs
     (.toString a radix))))
