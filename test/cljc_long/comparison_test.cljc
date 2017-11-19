(ns cljc-long.comparison-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

(deftest ??>
 ; (> 9007199254740993 9007199254740992)
 ; false in cljs
 (is
  (l/>
   (l/long "9007199254740993")
   (l/long "9007199254740992"))))

(deftest ??>=
 (is
  (l/>=
   (l/long "9007199254740993")
   (l/long "9007199254740992"))))

(deftest ??=
 ; (= 9007199254740992 9007199254740993)
 ; true in cljs
 (is
  (not
   (l/=
    (l/long "9007199254740992")
    (l/long "9007199254740993")))))

(deftest ??<=
 (is
  (l/<=
   (l/long "9007199254740992")
   (l/long "9007199254740993"))))

(deftest ??<
 ; (< 9007199254740992 9007199254740993)
 ; false in cljs
 (is
  (l/<
   (l/long "9007199254740992")
   (l/long "9007199254740993"))))

(deftest ??compare
 (is
  (==
   -1
   (l/compare
    (l/long "9007199254740992")
    (l/long "9007199254740993"))))

 (is
  (==
   0
   (l/compare
    (l/long "9007199254740993")
    (l/long "9007199254740993"))))

 (is
  (==
   1
   (l/compare
    (l/long "9007199254740993")
    (l/long "9007199254740992")))))
