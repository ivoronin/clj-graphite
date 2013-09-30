(ns clj-graphite.client-test
  (:require [clojure.test :refer :all]
            [clj-graphite.client :refer :all]))

(deftest send-metric-default-options-test
  (testing "Sending metric (default client options)"
    (let [graphite (client {})] 
      (.feed graphite "server.requests.count" 7856.0 1380528686))))

(deftest unsupported-protocol-test
  (testing "Specifying unsupported options"
    (is
      (thrown-with-msg? Exception #"^Unsupported protocol$" 
        (client { :protocol :icmp })))))
