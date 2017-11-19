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

(deftest ??+
 (is
  (l/=
   (l/long "9007199254740993")
   (l/+
    (l/long "9007199254740992")
    (l/long 1)))))

(deftest ??-
 (is
  (l/=
   (l/long "9007199254740993")
   (l/-
    (l/long "9007199254740994")
    (l/long 1)))))

(deftest ??*
 (is
  (l/=
   (l/long "18014398509481988")
   (l/*
    (l/long "9007199254740994")
    (l/long 2)))))

(deftest ??div
 (is
  (l/=
   (l/long "27021597764222985")
   (l//
    (l/long "54043195528445970")
    (l/long 2)))))
