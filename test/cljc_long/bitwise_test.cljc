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
   (l/long "-1152921504606846976")
   (l/bit-shift-right
    (l/long "-100000000000000000000000000000000000000000000000000000000000000" 2)
    (l/long 2))))

 (is
  (l/=
   (l/long "1073741823")
   (l/bit-shift-right
    (l/long l/max-value)
    (l/long 33)))))

(deftest ??unsigned-bit-shift-right
 (is
  (l/=
   (l/long "3458764513820540928")
   (l/unsigned-bit-shift-right
    (l/long "-100000000000000000000000000000000000000000000000000000000000000" 2)
    (l/long 2))))

 (is
  (l/=
   (l/long "1073741823")
   (l/unsigned-bit-shift-right
    (l/long l/max-value)
    (l/long 33)))))

(deftest ??high-bits
 (is
  (==
   21800986
   (l/high-bits (l/long "93634523492376492")))))

(deftest ??low-bits
 (is
  (==
   -7
   (l/low-bits
    (l/long "-100000000000000000000000000000000000000000000000000000000000111" 2)))))

(deftest ??unsigned-low-bits
 (is
  (==
   4294967289
   (l/unsigned-low-bits
    (l/long "-100000000000000000000000000000000000000000000000000000000000111" 2)))))

(deftest ??absolute-number-bits
 (doseq [[i o] [[l/min-value 64]
                [l/max-value 63]
                [1 1]
                [-1 1]
                [2 2]
                [-2 2]
                [3 2]
                [-3 2]
                [4 3]
                [-4 3]
                [8 4]
                [-8 4]]]
  (is (== o (l/absolute-number-bits (l/long i))))))

(deftest ??bit-rotate-left
 (is
  (l/=
   (l/long "-6814464169451580147")
   (l/bit-rotate-left
    (l/long "3928749263476283476")
    (l/long 6)))))
