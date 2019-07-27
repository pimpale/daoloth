(ns daoloth.core
  (:gen-class)
  (:import [javax.imageio ImageIO]
           [java.awt.image BufferedImage])
  (:require [clojure.java.io :as io])
  (:use seesaw.core
        seesaw.graphics
        seesaw.color))

(def img (ImageIO/read (io/file "resources/maptiles/ice.png")))

(defn paint [c g]
  (let [w (.getWidth c)
        h (.getHeight c)]
    (doto g
      (.setColor (color 224 224 0 128))
      (.drawImage img, 0, 0, nil)

      )))



(defn -main [& args]
  (invoke-later
    (->
      (frame
        :title "Daoloth"
        :on-close :exit
        :width 1920 :height 1080
        :content
        (canvas :id :canvas :background "#BBBBDD" :paint paint))
      pack!
      show!)))
