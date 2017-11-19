(ns cljc-long.comparison-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

(deftest ??=
 ; (= 9007199254740992 9007199254740993)
 ; true in cljs
 (is
  (not
   (l/=
    (l/long "9007199254740992")
    (l/long "9007199254740993")))))
