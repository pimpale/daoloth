(ns daoloth.core
  (:gen-class)
  (:import [javax.imageio ImageIO]
           [java.awt.image BufferedImage])
  (:require [clojure.java.io :as io])
  (:use seesaw.core
        seesaw.graphics
        seesaw.color))


; serialize to string
(defn serialize [obj] (pr-str obj))

; deserialize string to object
(defn deserialize [str] (read-string str))


; returns the image loaded from this resource path
(defn load-image [resource-path]
  (ImageIO/read (io/resource resource-path)))

; returns a map of the tiles associating names to tiles
(defn load-tiles [resource-path]
  ; we insert each tile described inside into the map
  (reduce
    (fn [map entry]
      (assoc map
             ; get name that represents in map
             (get "name" entry)
             ; create tile from values in map
             (update entry "img" load-image)))
    {} ; add them to empty map containing the name:tile binding
    ; get the list of mappings
    (->
      (io/resource resource-path)
      (slurp)
      (deserialize))))


; returns a map associating dungeons with names
(defn load-dungeons [resource-path]
  (reduce
    (fn [map entry]
      (assoc map
             ; get name that identifies dungeon
             (get "name" entry)
             ; create Dungeon
             entry))
    {} ; empty map containing name:dungeon mapping
    ; get the list of mappings
    (->
      (io/resource resource-path)
      (slurp)
      (deserialize))))


(defn paint [c g]
  (let [w (.getWidth c)
        h (.getHeight c)]
    (doto g
      (.setColor (color 224 224 0 128))
      ;(.drawImage img, 0, 0, nil)
      )))




(defn -main [& args]
  (let [tile-map (load-tiles "tile.cljstr")
        dungeons (load-dungeons "dungeon.cljstr")]
    (invoke-later
      (->
        (frame
          :title "Daoloth"
          :on-close :exit
          :width 1920 :height 1080
          :content (canvas :id :canvas :paint paint))
        pack!
        show!))))
