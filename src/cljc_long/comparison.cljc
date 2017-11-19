(ns cljc-long.comparison
 (:refer-clojure :exclude [> >= = <= <]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

#?(:clj (def > clojure.core/>))

#?(:clj (def >= clojure.core/>=))

#?(:clj (def = clojure.core/=)
   :cljs
   (defn =
    [a b]
    (.equals a b)))

#?(:clj (def <= clojure.core/<=))

#?(:clj (def < clojure.core/<))
