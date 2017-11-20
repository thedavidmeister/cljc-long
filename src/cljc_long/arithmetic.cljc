(ns cljc-long.arithmetic
 (:refer-clojure :exclude [+ - * / neg? odd? even? zero? mod unchecked-negate])
 (:require
  cljc-long.type))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

#?(:clj (def + clojure.core/+)
   :cljs
   (defn +
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.add a b)))

#?(:clj (def - clojure.core/-)
   :cljs
   (defn -
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.subtract a b)))

#?(:clj (def * clojure.core/*)
   :cljs
   (defn *
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.multiply a b)))

#?(:clj (def / clojure.core//)
   :cljs
   (defn /
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.div a b)))

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
    (.isZero a)))

#?(:clj (def mod clojure.core/mod)
   :cljs
   (defn mod
    [a b]
    (.modulo a b)))

#?(:clj (def unchecked-negate clojure.core/unchecked-negate)
   :cljs
   (defn unchecked-negate
    [a]
    (.negate a)))
