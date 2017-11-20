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

(deftest ??neg?
 (is (l/neg? (l/long -1)))
 (is (not (l/neg? (l/long 0))))
 (is (not (l/neg? (l/long 1)))))

(deftest ??odd?
 (is (l/odd? (l/long -3)))
 (is (not (l/odd? (l/long -2))))
 (is (l/odd? (l/long -1)))
 (is (not (l/odd? (l/long 0))))
 (is (l/odd? (l/long 1)))
 (is (not (l/odd? (l/long 2))))
 (is (l/odd? (l/long 3))))

(deftest ??even?
 (is (not (l/even? (l/long -3))))
 (is (l/even? (l/long -2)))
 (is (not (l/even? (l/long -1))))
 (is (l/even? (l/long 0)))
 (is (not (l/even? (l/long 1))))
 (is (l/even? (l/long 2)))
 (is (not (l/even? (l/long 3)))))

(deftest ??zero?
 (is (not (l/zero? (l/long -1))))
 (is (l/zero? (l/long 0)))
 (is (not (l/zero? (l/long 1)))))
