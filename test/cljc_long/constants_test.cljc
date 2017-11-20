(ns cljc-long.constants-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

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

(deftest ??zero
 (is
  (l/=
   (l/long 0)
   l/zero)))

(deftest ??one
 (is
  (l/=
   (l/long 1)
   l/one)))

(deftest ??neg-one
 (is
  (l/=
   (l/long -1)
   l/neg-one)))

(deftest ??two-power-24
 (is
  (l/=
   (l/long (Math/pow 2 24))
   l/two-power-24)))
