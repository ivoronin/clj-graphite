# clj-graphite

A Clojure library for sending metrics to Graphite

## Artifacts

`clj-graphite` artifacts are [released to Clojars](https://clojars.org/clj-graphite).

### Latest release

Add the following dependency to your `project.clj`:

``` clj
[clj-graphite "0.1.0"]
```

## Usage

``` clj
(require 'clj-graphite.client)

(let [graphite (clj-graphite.client/client { :host "127.0.0.1" :port 2003 })]
  (.feed graphite "server.requests.count" 7856.0 1380528686)
)
```

## License

Copyright © 2013 Ilya Voronin

Distributed under the Eclipse Public License 1.0