(ns speculative.instrument-test
  (:require
   [clojure.spec.test.alpha :as stest]
   [clojure.test :as t :refer [deftest is testing]]
   [respeced.test :refer [caught?]]
   [speculative.instrument :as instrument]
   ;; included for self-hosted cljs
   [workarounds-1-10-439.core]))

(deftest instrument-test
  (testing "speculative specs should be instrumentable and unstrumentable"
    (let [instrumented (instrument/instrument)
          unstrumented (instrument/unstrument)]
      (println "instrumented:" (count instrumented))
      (println "unstrumented:" (count unstrumented)))))

(deftest fixture-test
  (testing "without instrumentation"
    (is (some? (fnil 1 1))))

  (testing "with instrumentation"
    (is (caught? `fnil
                 (instrument/fixture
                  #(fnil 1 1))))))
