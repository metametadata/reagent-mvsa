(ns app.core
  (:require [friend-list.core :as friend-list]
            [app.api :as api]
            [carry.core :as carry]
            [carry-reagent.core :as carry-reagent]
            [carry-history.core :as h]
            [carry-logging.core :as logging]
            [carry-debugger.core :as debugger]
            [reagent.core :as r]
            [hodgepodge.core :as hp]
            [devtools.core :as chrome-devtools]))

(enable-console-print!)
(chrome-devtools/install! [:custom-formatters :sanity-hints])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn main
  []
  (let [history (h/new-hash-history)
        storage hp/local-storage
        blueprint (-> (friend-list/new-blueprint history api/search)
                      logging/add
                      (debugger/add storage :friend-list-debugger-model))
        app (carry/app blueprint)
        [app-view-model app-view] (carry-reagent/connect app friend-list/view-model friend-list/view)
        [_ debugger-view] (debugger/connect app)]
    (r/render [:div app-view debugger-view] (.getElementById js/document "root"))
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