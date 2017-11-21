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
    (l/long 1))))

 ; can trigger integer overflow in jvm if not careful
 (is
  (l/=
   (l/long 4661505866122819911)
   (l/+
    (l/long -6739208953200378574)
    (l/long -7046029254386353131))))

 ; moar arity
 (is
  (l/=
   (l/long 10)
   (l/+
    (l/long 1)
    (l/long 2)
    (l/long 3)
    (l/long 4)))))

(deftest ??-
 ; (- 9007199254740994 1)
 ; 9007199254740992 in cljs
 (is
  (l/=
   (l/long "9007199254740993")
   (l/-
    (l/long "9007199254740994")
    (l/long 1))))

 ; can trigger integer overflow in jvm if not careful
 (is
  (l/=
   (l/long 4661505866122819911)
   (l/-
    (l/long -6739208953200378574)
    (l/long 7046029254386353131))))

 ; moar arity
 (is
  (l/=
   (l/long -8)
   (l/-
    (l/long 1)
    (l/long 2)
    (l/long 3)
    (l/long 4)))))

(deftest ??*
 ; (* 9007199254740994 3)
 ; 27021597764222984 in cljs
 (is
  (l/=
   (l/long "27021597764222982")
   (l/*
    (l/long "9007199254740994")
    (l/long 3))))

 ; can trigger integer overflow in jvm if not careful
 (is
  (l/=
   (l/long "-3126565326643526938")
   (l/*
    (l/long "-6739208953200378574")
    (l/long "7046029254386353131"))))

 ; moar arity
 (is
  (l/=
   (l/long 24)
   (l/*
    (l/long 1)
    (l/long 2)
    (l/long 3)
    (l/long 4)))))

(deftest ??div
 ; (/ 54043195528445970 2)
 ; 27021597764222984 in cljs
 (is
  (l/=
   (l/long "27021597764222985")
   (l//
    (l/long "54043195528445970")
    (l/long 2))))

 ; moar arity
 (is
  (l/=
   (l/long 2)
   (l//
    (l/long 16)
    (l/long 2)
    (l/long 2)
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
 (is (l/odd? (l/long 3)))
 (is (l/odd? (l/long "9007199254740993"))))

(deftest ??even?
 (is (not (l/even? (l/long -3))))
 (is (l/even? (l/long -2)))
 (is (not (l/even? (l/long -1))))
 (is (l/even? (l/long 0)))
 (is (not (l/even? (l/long 1))))
 (is (l/even? (l/long 2)))
 (is (not (l/even? (l/long 3))))
 (is (not (l/even? (l/long "9007199254740993")))))

(deftest ??zero?
 (is (not (l/zero? (l/long -1))))
 (is (l/zero? (l/long 0)))
 (is (not (l/zero? (l/long 1)))))

(deftest ??mod
 ; (mod 9007199254740993 2)
 ; 0 in cljs
 (is
  (l/=
   (l/long 1)
   (l/mod (l/long "9007199254740993") (l/long 2)))))

(deftest ??unchecked-negate
 (is
  (l/=
   l/min-value
   (l/unchecked-negate cljc-long.constants/min-value)))

 (is
  (l/=
   (l/+ l/min-value (l/long 1))
   (l/unchecked-negate l/max-value)))

 (is (l/zero? (l/unchecked-negate (l/long 0))))

 (is
  (l/=
   (l/long -1)
   (l/unchecked-negate (l/long 1))))

 (is
  (l/=
   (l/long 1)
   (l/unchecked-negate (l/long -1)))))
