(defproject timsg.ports.loom 
  :description "Provisional port of aysylu/loom graph library for Clojure to Arcadia."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 ;;[org.clojure/data.priority-map "0.0.5"]
                 ;;[tailrecursion/cljs-priority-map "1.1.0"]
                 ]
  :url "https://github.com/timsgardner/timsg.ports.loom"
  :test-selectors {:default (fn [m] (not (:test-check-slow m)))
                   :all (constantly true)
                   :test-check-slow :test-check-slow}
  :profiles {:dev
             {:dependencies [[org.clojure/test.check "0.5.7"]]}}
  :aliases {"release" ["do" "clean," "with-profile" "default" "deploy" "clojars"]}

  :plugins  [[codox "0.8.12"]]
  :codox  {:src-dir-uri "https://github.com/aysylu/loom/blob/master/"
           :src-linenum-anchor-prefix "L"
           :exclude loom.multigraph})
