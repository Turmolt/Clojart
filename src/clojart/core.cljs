(ns clojart.core
  (:require [clojart.art.circles :as art]
            [quil.core :as q]
            [quil.middleware :as m]
            [clojart.canvas :refer [w h]]))


(defn sketch-setup []
  []
  (art/sketch-setup))

(defn sketch-update [state]
  (art/sketch-update state))

(defn sketch-draw [state]
  (art/sketch state))

(defn create [canvas]
  (q/sketch
   :host canvas
   :size [w h]
   :draw #'sketch-draw
   :setup #'sketch-setup
   :update #'sketch-update
   :middleware [m/fun-mode]
   :settings (fn []
               (q/random-seed 1337)
               (q/noise-seed 1337))))

(defonce sketch (create "sketch"))