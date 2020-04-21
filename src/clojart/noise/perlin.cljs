(ns clojart.noise.perlin
  (:require [quil.core :as q]))

(def zoom
  "Noise size"
  0.0005)

(defn direction
  "Calculate direction between [0,2 PI] based on the perlin noise sampled"
  [x y]
  (* 2
     Math/PI
     (q/noise (* x zoom) (* y zoom))))