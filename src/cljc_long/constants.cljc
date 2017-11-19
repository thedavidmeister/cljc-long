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
