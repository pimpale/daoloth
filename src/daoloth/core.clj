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
      (draw (polygon [0 h] [(/ w 4) 0] [(/ w 2) (/ h 2)] [w (/ h 2)] [0 h])
            (style :foreground java.awt.Color/BLACK
                   :background (color 128 128 128 128)
                   :stroke     (stroke :width 4)))
      (.setColor (color 224 224 0 128))
      (.fillRect 0 0 (/ w 2) (/ h 2))
      (.setColor (color 0 224 224 128))
      (.fillRect 0 (/ h 2) (/ w 2) (/ h 2))
      (.setColor (color 224 0 224 128))
      (.fillRect (/ w 2) 0 (/ w 2) (/ h 2))
      (.setColor (color 224 0 0 128))
      (.fillRect (/ w 2) (/ h 2) (/ w 2) (/ h 2))
      (.setColor (color 0 0 0))

      (.drawImage img, 0, 0, nil)

      (.drawString "Hello. This is a canvas example" 20 20))))



(defn -main [& args]
  (invoke-later
    (->
      (frame
        :title "Canvas Example"
        :width 1920 :height 1080
        :content
        (canvas :id :canvas :background "#BBBBDD" :paint paint))
      pack!
      show!)))
