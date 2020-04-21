(ns clojart.canvas)

(def body (.-body js/document))
(def w (.-clientWidth body))
(def h (.-clientHeight body))