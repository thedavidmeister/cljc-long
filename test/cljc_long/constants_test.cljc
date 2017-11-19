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
