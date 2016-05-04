(defproject
  carry-persistence "0.1.0"
  :description "Carry middleware for automatic model saving/loading using browser storage."
  :url "https://github.com/metametadata/carry/tree/master/contrib/persistence"
  :license {:name "MIT" :url "http://opensource.org/licenses/MIT"}

  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.8.51" :scope "provided"]

                 [org.clojure/core.match "0.3.0-alpha4" :scope "provided"]]

  :pedantic? :abort

  :source-paths ["src"]

  :repositories {"clojars" {:sign-releases false}})
