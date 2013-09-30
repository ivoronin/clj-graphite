(ns clj-graphite.client
  (:import (java.net DatagramSocket InetAddress DatagramPacket))
  (:gen-class))

(defprotocol graphite-client
  (open [client])
  (feed [client path value timestamp])
  (close [client]))

(defrecord graphite-udp-client [host port]
  graphite-client
  (open [this]
    (assoc this :socket (DatagramSocket.) :host host :port port))
  (feed [this path value timestamp]
    (let [line (format "%s %f %d\n" path value timestamp)
      bytes (.getBytes line)
      length (count bytes)
      address (InetAddress/getByName (:host this))
      datagram (DatagramPacket. bytes length address port)]
      (.send (:socket this) datagram)))
  (close [this]
    (.close (:socket this))))

(defn client
  [opts]
  (let [opts (merge {:host "127.0.0.1"
                     :port 2003
                     :protocol :udp} opts)
    host (:host opts)
    port (:port opts)]
    (open (case (:protocol opts)
      :tcp (throw (Exception. "Not implemented"))
      :udp (graphite-udp-client. host port)
      (throw (Exception. "Unsupported protocol"))))))
