(ns daoloth.core
  (:gen-class)
  (:import [javax.imageio ImageIO]
           [java.awt.image BufferedImage])
  (:require [clojure.java.io :as io]
            [cheshire.core :as json])
  (:use seesaw.core
        seesaw.graphics
        seesaw.color))

; Dungeon Struct
(defrecord Dungeon [name grid])

; Tile Struct
(defrecord Tile [name level image])

; returns the image loaded from this resource path
(defn load-image [resource-path]
  (ImageIO/read (io/resource resource-path)))

; returns a map of the tiles associating char to tile
(defn load-maptiles []
  ; we insert each tile described inside of the json into the map
  (reduce
    (fn [map jsonentry]
      (assoc map
             ; get char that represents in map
             (get "char" jsonentry)
             ; create tile
             (Tile. (get "name" jsonentry)
                    (get "level" jsonentry)
                    (load-image (get "img" jsonentry)))))
      {} ; add them to empty map containing the char:tile binding
      ; get the list at the root of maptiles/maptiles.json
      (->
        (io/resource "maptiles/maptiles.json")
        (slurp)
        (json/decode))))

(defn paint [c g]
  (let [w (.getWidth c)
        h (.getHeight c)]
    (doto g
      (.setColor (color 224 224 0 128))
      ;(.drawImage img, 0, 0, nil)
      )))

(defn -main [& args]
  (invoke-later
   (->
    (frame
     :title "Daoloth"
     :on-close :exit
     :width 1920 :height 1080
     :content (canvas :id :canvas :paint paint))
    pack!
    show!)))
