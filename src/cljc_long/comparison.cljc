(ns cljc-long.comparison
 (:refer-clojure :exclude [> >= = <= < compare]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

#?(:clj (def > clojure.core/>)
   :cljs
   (defn >
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.greaterThan a b)))

#?(:clj (def >= clojure.core/>=)
   :cljs
   (defn >=
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.greaterThanOrEqual a b)))

#?(:clj (def = clojure.core/=)
   :cljs
   (defn =
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.equals a b)))

#?(:clj (def <= clojure.core/<=)
   :cljs
   (defn <=
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.lessThanOrEqual a b)))

#?(:clj (def < clojure.core/<)
   :cljs
   (defn <
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.lessThan a b)))

#?(:clj (def compare clojure.core/compare)
   :cljs
   (defn compare
    [a b]
    {:pre [(cljc-long.type/long? a)
           (cljc-long.type/long? b)]}
    (.compare a b)))