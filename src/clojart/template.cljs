(ns clojart.template
  (:require [quil.core :as q]
            [clojart.canvas :refer [w h]]))

(def palette
  {:name        "Blue River"
   :background  [250 250 250]
   :colors      [[0 0 0]]})

(defn sketch-setup
  "Returns inital state of page for update-render loop"
  []
  (apply q/background (:background palette)))

(defn sketch-update
  "slowly move the river noise"
  [state]
  state)


(defn sketch
  [state]
  (apply q/background (:background palette))
  (q/no-stroke)
  (q/ellipse (/ w 2) (/ h 2) 100 100))