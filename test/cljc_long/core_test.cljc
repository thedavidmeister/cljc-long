(ns cljc-long.core-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(deftest ??long
 (is (l/long? (l/long "123"))))

(deftest ??max-value
 (is
  (l/=
   (l/long "9223372036854775807")
   l/max-value)))

(deftest ??min-value
 (is
  (l/=
   (l/long "-9223372036854775808")
   l/min-value)))
