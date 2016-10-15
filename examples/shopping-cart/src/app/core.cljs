(ns app.core
  (:require [app.blueprint :as blueprint]
            [app.api :as api]
            [app.view-model :as view-model]
            [app.view :as view]
            [carry.core :as carry]
            [carry-reagent.core :as carry-reagent]
            [reagent.core :as r]))

(enable-console-print!)

(defn main
  []
  (let [blueprint (blueprint/new-blueprint api/shop-api-stub)
        app (carry/app blueprint)
        [app-view-model app-view] (carry-reagent/connect app view-model/view-model view/view)]
    (r/render app-view (.getElementById js/document "root"))
    ((:dispatch-signal app) :on-start)
    (assoc app :view-model app-view-model)))

(def app (main))

;;;;;;;;;;;;;;;;;;;;;;;; Figwheel stuff
(defn before-jsload
  []
  ((:dispatch-signal app) :on-stop))

(defn on-jsload
  []
  #_(. js/console clear))