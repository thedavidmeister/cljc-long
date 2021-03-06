(ns cljc-long.arithmetic
 (:refer-clojure :exclude [+ - * / neg? odd? even? zero? mod unchecked-negate])
 (:require
  cljc-long.type))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

#?(:clj
   (defn +
    ([a b & xs]
     (reduce + (into [a b] xs)))
    ([^long a ^long b]
     (clojure.core/+ a b)))
   :cljs
   (defn +
    ([a b & xs]
     (reduce + (into [a b] xs)))
    ([a b]
     {:pre [(cljc-long.type/long? a)
            (cljc-long.type/long? b)]}
     (.add a b))))

#?(:clj
   (defn -
    ([a b & xs]
     (reduce - (into [a b] xs)))
    ([^long a ^long b]
     (clojure.core/- a b)))
   :cljs
   (defn -
    ([a b & xs]
     (reduce - (into [a b] xs)))
    ([a b]
     {:pre [(cljc-long.type/long? a)
            (cljc-long.type/long? b)]}
     (.subtract a b))))

#?(:clj
   (defn *
    ([a b & xs]
     (reduce * (into [a b] xs)))
    ([^long a ^long b]
     (clojure.core/* a b)))
   :cljs
   (defn *
    ([a b & xs]
     (reduce * (into [a b] xs)))
    ([a b]
     {:pre [(cljc-long.type/long? a)
            (cljc-long.type/long? b)]}
     (.multiply a b))))

#?(:clj
   (defn /
    ([a b & xs]
     (reduce / (into [a b] xs)))
    ([^long a ^long b]
     (clojure.core// a b)))
   :cljs
   (defn /
    ([a b & xs]
     (reduce / (into [a b] xs)))
    ([a b]
     {:pre [(cljc-long.type/long? a)
            (cljc-long.type/long? b)]}
     (.div a b))))

#?(:clj (def neg? clojure.core/neg?)
   :cljs
   (defn neg?
    [a]
    {:pre [(cljc-long.type/long? a)]}
    (.isNegative a)))

#?(:clj (def odd? clojure.core/odd?)
   :cljs
   (defn odd?
    [a]
    {:pre [(cljc-long.type/long? a)]}
    (.isOdd a)))

(def even? (complement odd?))

#?(:clj (def zero? clojure.core/zero?)
   :cljs
   (defn zero?
    [a]
    {:pre [(cljc-long.type/long? a)]}
    (.isZero a)))

#?(:clj
   (defn mod
    [^long a ^long b]
    (clojure.core/mod a b))
   :cljs
   (defn mod
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.modulo a b)))

#?(:clj
   (defn unchecked-negate
    [^long a]
    (clojure.core/unchecked-negate a))
   :cljs
   (defn unchecked-negate
    [a]
    {:pre [(cljc-long.type/long? a)]}
    (.negate a)))
