(defproject daoloth "0.1.0-SNAPSHOT"
  :description "Video Game"
  :url "http://github.com/pimpale/daoloth"
  :license {:name "Unlicense"
            :url "https://unlicense.org/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [seesaw "1.5.1-SNAPSHOT"]]
  :main daoloth.core
  :target-path "target/%s"
  :plugins [[lein-cljfmt "0.6.4"]]
  :profiles {:uberjar {:aot :all}})
