(ns timsg.ports.loom.viz
  (:require [timsg.ports.loom.graph :as lg]
            [timsg.ports.loom.attr :as la]
            [timsg.ports.loom.io :as lio]
            [clojure.clr.shell :as sh])
  (:import [System.IO FileInfo FileSystemInfo]))

;; (defn- file-info [x]
;;   (cond
;;     (instance? FileInfo x) x
;;     (string? x) (try
;;                   (FileInfo. x)
;;                   (catch System.ArgumentException e))
;;     :else (throw (System.ArgumentException. "Expects FileInfo or String."))))

;; (defn- info ^FileSystemInfo [x]
;;   (cond
;;     (instance? FileSystemInfo x) x
;;     ;; Yes I hate it too
;;     (string? x) (let [fi (file-info x)] 
;;                   (if (and fi (.Exists fi))
;;                     fi
;;                     (let [di (directory-info x)] 
;;                       (when (and di (.Exists di))
;;                         di))))
;;     :else (throw (System.ArgumentException. "Expects FileSystemInfo or String."))))

;; (defn- path ^String [x]
;;   (cond
;;     (instance? FileSystemInfo x) (.FullName x)
;;     (string? x) (if-let [fsi (info x)]
;;                   (path fsi)
;;                   (path (FileInfo. x)))
;;     :else (throw (ArgumentException.
;;                    (str "expects FileSystemInfo or string, got "
;;                      (class x))))))

(defn- file-info [x]
  (cond
    (instance? FileInfo x) x
    (string? x) (try
                  (FileInfo. x)
                  (catch System.ArgumentException e))
    :else (throw (System.ArgumentException. "Expects FileInfo or String."))))

(defn show [g {:keys [alg path]
               :or {alg "/usr/local/bin/dot"
                    path "Temp/"} ;; this is bullshit
               }]
  (let [path' (.FullName (file-info path))
        algopath "/usr/local/bin/dot"
        dotstr (apply lio/dot-str g opts)]
    ;;(.Create (fs/file-info path'))
    (sh/sh algopath  "-o" path' "-Tpng" :in dotstr)
    (sh/sh "open" path')
    path'))
