(ns clojart.art.circles
  (:require [quil.core :as q]
            [clojart.canvas :refer [w h]]))

(def palette
  {:name        "Blue River"
   :background  [250 250 250]
   :colors      [[0 0 0]]})

(defn rand-color
  []
  (rand-nth (:colors palette)))

(defn circle
  [id]
  {:id    id
   :x     (Math/cos id)
   :y     (Math/sin id)
   :angle 0
   :color (rand-color)})

(def numcircles 1000)

(defn runtime 
  "the runtime variable our animations moves based on"
  []
  (+ 10 (Math/cos (/ (q/frame-count) 10))))

(defn angle 
  "calculate the angle that our circle is moving"
  [circle]
  (* (/ Math/PI (runtime))
     (:id circle)))



(defn wrap
  [n]
  (* (Math/cos n) 100))

(defn draw-circle
  [id r angle m]
  (q/ellipse (+ (/ w 2) (* (wrap (- id (q/frame-count)))  (* m (Math/cos angle)))) 
             (+ (/ h 2) (* (wrap (- id (q/frame-count))) (* m (Math/sin angle))))
             r r))

(defn sketch-setup
  "Returns inital state of page for update-render loop"
  []
  (apply q/background (:background palette))
  (map circle (range 0 numcircles)))

(defn sketch-update
  [state]
  state)

(defn sketch
  [circles]
  (apply q/background (:background palette))
  (q/no-stroke)

  (doseq [c circles]
    (apply q/fill (:color c))
    (draw-circle (:id c) 
                 10 
                 (/ (* .01 (q/frame-count)) (:id c) 5)
                 2)))