(ns clojart.flow
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [clojart.canvas :refer [w h]]
            [clojart.noise.perlin :as noise]))

(def palette
  {:name        "Blue River"
   :background  [50 70 200]
   :colors      [[32 0 50]
                 [10 10 120]
                 [0 10 150]
                 [0 100 150]
                 [5 20 250]
                 [100 100 250]]})

(defn particle
  "creates a particle map"
  [id]
  {:id        id
   :vx        1
   :vy        1
   :size      3
   :direction 0
   :x         (q/random w)
   :y         (q/random h)
   :color     (rand-nth (:colors palette))})

(defn step-value
  "steps a current value by the delta, applying an operator/value"
  [current delta value operator]
  (operator (+ current delta) value))

(defn sketch-setup
  "Returns inital state of page for update-render loop"
  []
  (apply q/background (:background palette))
  (map particle (range 0 4000)))

(defn sketch-update
  "slowly move the river noise" 
  [particles]
  (map #(assoc %
               :x         (step-value (:x %)  (:vx %) w mod)
               :y         (step-value (:y %)  (:vy %) h mod)
               :direction (noise/direction (:x %) (:y %))
               :vx        (step-value (:vx %) (Math/cos (:direction %)) 2 /)
               :vy        (step-value (:vy %) (Math/sin (:direction %)) 2 /))
       particles))


(defn sketch 
  [particles]
  ;(apply q/background (:background palette))
  (q/no-stroke)
  (doseq [p particles]
    (apply q/fill (:color p))
    (q/ellipse (:x p) (:y p) (:size p) (:size p))))