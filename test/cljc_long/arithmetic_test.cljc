(ns cljc-long.arithmetic-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

(deftest ??+
 ; (+ 9007199254740992 1)
 ; 9007199254740992 in cljs
 (is
  (l/=
   (l/long "9007199254740993")
   (l/+
    (l/long "9007199254740992")
    (l/long 1)))))

(deftest ??-
 ; (- 9007199254740994 1)
 ; 9007199254740992 in cljs
 (is
  (l/=
   (l/long "9007199254740993")
   (l/-
    (l/long "9007199254740994")
    (l/long 1)))))

(deftest ??*
 ; (* 9007199254740994 3)
 ; 27021597764222984 in cljs
 (is
  (l/=
   (l/long "27021597764222982")
   (l/*
    (l/long "9007199254740994")
    (l/long 3)))))

(deftest ??div
 ; (/ 54043195528445970 2)
 ; 27021597764222984 in cljs
 (is
  (l/=
   (l/long "27021597764222985")
   (l//
    (l/long "54043195528445970")
    (l/long 2)))))
