(defproject daoloth "0.1.0-SNAPSHOT"
  :description "Video Game"
  :url "http://github.com/pimpale/daoloth"
  :license {:name "Unlicense"
            :url "https://unlicense.org/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [quil "3.0.0"]]
  :main ^:skip-aot daoloth.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
