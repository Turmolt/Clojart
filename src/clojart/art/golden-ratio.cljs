(ns clojart.art.golden-ratio
  (:require [quil.core :as q]
            [clojart.canvas :refer [w h]]))

;recreation of my shader:
;https://www.shadertoy.com/view/tdlyzn

(def numcircles 750)

(def palette
  "a palette of colors"
  {:name        "Golden Ratio"
   :background  [0 0 0]
   :colors      [[100 0 0]
                 [200 100 50]
                 [250 200 100]
                 [90 8 0]]})

(defn rand-color
  "returns a random color from the palette"
  []
  (rand-nth (:colors palette)))

(defn circle
  "creates a map of randomly colored circles"
  [id]
  {:id     id
   :delta  id
   :color  (rand-color)})

(defn draw-circle
  "draws a circle at the position calculated by its angle from start and id"
  [id angle]
  (q/ellipse (+ (/ w 2) (* id (Math/cos angle))) 
             (+ (/ h 2) (* id (Math/sin angle)))
             10 10))

(defn angle
  "calulate the angle based on the delta provided"
  [delta]
  (-> (q/frame-count)
      (* .0001)
      (* delta)))

(defn sketch-setup
  "Returns inital state of page for update-render loop"
  []
  (apply q/background (:background palette))
  (map circle (range 0 numcircles)))

(defn sketch-update
  "update the state each frame"
  [state]
  (map #(assoc %
               :delta (+ .01 (- (:id %)  (:time %))))
       state))

(defn sketch
  "make art"
  [circles]
  (apply q/background (:background palette))
  (q/no-stroke)
  (doseq [c circles]
    (apply q/fill (:color c))
    (draw-circle (:id c) 
                 (angle (:delta c)))))