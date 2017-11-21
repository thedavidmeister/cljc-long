(def project 'thedavidmeister/cljc-long)
(def version "1.0.0")
(def description "Signed 64 bit integers for CLJC")
(def url "https://github.com/thedavidmeister/cljc-long")

(set-env!
 :source-paths #{"src"}
 :dependencies
 '[[org.clojure/clojure "1.9.0-RC1"]
   [org.clojure/clojurescript "1.9.946"]
   [org.clojure/tools.namespace "0.2.11"]

   [samestep/boot-refresh "0.1.0" :scope "test"]
   [adzerk/boot-cljs "2.1.2" :scope "test"]
   [doo "0.1.7" :scope "test"]
   [criterium "0.4.4" :scope "test"]
   [adzerk/boot-test "RELEASE" :scope "test"]
   [adzerk/bootlaces "RELEASE" :scope "test"]
   [crisptrutski/boot-cljs-test "0.3.4" :scope "test"]])

(task-options!
 pom {:project project
      :version version
      :description description
      :url url
      :scm {:url url}})

(require
 '[adzerk.bootlaces :refer :all]
 '[adzerk.boot-test :refer [test]]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]]
 '[samestep.boot-refresh :refer [refresh]])

(bootlaces! version)

(replace-task!
 [t test]
 (fn [& xs]
  (set-env! :source-paths #{"src" "test"})
  (apply t xs)))

(deftask tests-cljs
 "Run all the CLJS tests"
 []
 (set-env! :source-paths #{"src" "test"})
 (comp
  (test-cljs
   :cljs-opts {:process-shim false}
   :optimizations :advanced
   ; :js-env :chrome
   :namespaces [#".*test.*"])))

(deftask repl-server
 []
 (set-env! :source-paths #{"src" "test"})
 (comp
  (watch)
  (refresh)
  (repl :server true)))

(deftask repl-client
 []
 (repl :client true))

(deftask deploy
 []
 (comp
  (build-jar)
  (push-release)))
