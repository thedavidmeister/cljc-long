(ns cljc-long.type
 (:refer-clojure :exclude [long? long]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(defn long? [a]
 #?(:cljs (instance? goog.math.Long a)
    :clj (instance? java.lang.Long a)))

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
