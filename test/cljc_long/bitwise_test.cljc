(ns cljc-long.bitwise-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

(deftest ??bit-and
 (is
  (l/=
   (l/long "9007199254740993")
   (l/bit-and
    (l/long "9007199254740993")
    (l/long "9007199254740993"))))

 (is
  (l/=
   (l/long "18016743578425444")
   (l/bit-and
    (l/long "19007199254740990")
    (l/long "90093029317957732")))))

(deftest ??bit-not
 (is
  (l/=
   (l/long "-475073650890459")
   (l/bit-not (l/long "475073650890458")))))

(deftest ??bit-or
 (is
  (l/=
   (l/long "691008609599231")
   (l/bit-or
    (l/long "92857093718760")
    (l/long "598638761098423")))))

(deftest ??bit-xor
 (is
  (l/=
   (l/long "595757005573193")
   (l/bit-xor
    (l/long "598123923857103")
    (l/long "2387610948230")))))

(deftest ??bit-shift-left
 (is
  (l/=
   (l/long "7427761112226416")
   (l/bit-shift-left
    (l/long "928470139028302")
    (l/long 3)))))

(deftest ??bit-shift-right
 (is
  (l/=
   (l/long "-1073741824")
   (l/bit-shift-right
    (l/long l/min-value)
    (l/long 33))))

 (is
  (l/=
   (l/long "1073741823")
   (l/bit-shift-right
    (l/long l/max-value)
    (l/long 33)))))

(deftest ??unsigned-bit-shift-right
 (is
  (l/=
   (l/long "-1073741824")
   (l/unsigned-bit-shift-right
    (l/long l/min-value)
    (l/long 33))))

 (is
  (l/=
   (l/long "1073741823")
   (l/unsigned-bit-shift-right
    (l/long l/max-value)
    (l/long 33)))))
