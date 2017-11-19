(ns cljc-long.arithmetic
 (:refer-clojure :exclude [+ - * /])
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
