(ns cljc-long.constants
 #?(:cljs (:require goog.math.Long)))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(def max-value
 #?(:clj Long/MAX_VALUE
    :cljs (goog.math.Long/getMaxValue)))

(def min-value
 #?(:clj Long/MIN_VALUE
    :cljs (goog.math.Long/getMinValue)))

(def zero
 #?(:cljs (goog.math.Long/getZero)
    :clj (long 0)))

(def one
 #?(:cljs (goog.math.Long/getOne)
    :clj (long 1)))

(def neg-one
 #?(:cljs (goog.math.Long/getNegOne)
    :clj (long -1)))

(def two-power-24
 #?(:cljs (goog.math.Long/getTwoPwr24)
    :clj (long (Double/parseDouble "0x1.0p24"))))
