(ns
    ^{:doc "Eaxmple of Auto Completion Hinter using a subset of words from a dictionary"
      :author "Steven Hankin"}
  com.github.stevenhankin.clj-auto-completion
  (:require [clojure.contrib.duck-streams :as ds]))

;;
;; This example is taking 1000 words
;; and using a max group size of 8
;;
(def *words-file* "/usr/share/dict/words")
(defn all-words []
  (lazy-seq (ds/read-lines *words-file*)))
(def m (produce-map (take 1000 (sort (all-words))) {:maxgrpsize 8}))
(doall (map println m))





