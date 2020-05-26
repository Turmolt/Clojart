(ns clojart.art.line-dance
  (:require [quil.core :as q]))

(defn icon-circle [id]
  (* (/ 3.1415927 3.0) id))

(defn sketch-setup []
  (apply q/background [255 255 255])
  (map icon-circle (range 6)))

(defn sketch-update [circles]
  (map #(+ 0.05 %) circles))

(def o 480)
(def m 320)
(def f 240)

(defn icon-center [t]
  [(+ o (* m (Math/cos t))) (+ o (* m (Math/sin t)))])

(defn sketch [circles]
  (apply q/background [255 255 255])
  (q/ellipse 480 480 160 160)
  (doseq [t circles]
    (let [[x y] (icon-center t)]
      (q/line [x y] [(+ f (/ x 2)) (- o (/ y 2))]))))

