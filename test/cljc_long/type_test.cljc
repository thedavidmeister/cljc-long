(ns cljc-long.type-test
 (:require
  [cljc-long.core :as l]
  [clojure.test :refer [deftest is]]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(deftest ??long?
 (is (l/long? (l/long "123")))
 (is (not (l/long? 123.0)))
 (is (not (l/long? {}))))

(deftest ??long
 ; strings and radixes
 (is
  (l/=
   (l/long 123)
   (l/long "123")))

 (is
  (l/=
   (l/long 123)
   (l/long "123" 10)))

 (is
  (l/=
   (l/long 16)
   (l/long "10" 16)))

 (is
  (l/=
   (l/long 16)
   (l/long "10000" 2)))

 ; floats and ints
 (is
  (l/=
   (l/long 9007199254740994)
   (l/long "9007199254740994")))

 (is
  (l/=
   (l/long "1")
   (l/long 1.0)))

 (is
  (l/=
   (l/long "1")
   (l/long 1.1)))

 ; sequences

 (is
  (l/=
   (l/long 4294967297)
   (l/long [(int 1) (int 1)])))

 (is
  (l/=
   (l/long 9223372032559808512)
   (l/long [(int 0) (int (dec (Math/pow 2 31)))])))

 (is
  (l/=
   (l/long -4294967295)
   (l/long [(int 1) (int -1)])))

 (is
  (l/=
   (l/long "-9223372036854775807")
   (l/long [(int 1) (int (- (Math/pow 2 31)))]))))
