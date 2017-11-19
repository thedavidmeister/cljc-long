(ns cljc-long.type-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(deftest ??long
 (is (l/long? (l/long "123"))))
