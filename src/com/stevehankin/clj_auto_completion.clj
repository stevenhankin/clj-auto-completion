(ns
    ^{:doc "Auto Completion Hinter"
      :author "Steven Hankin"}
  com.github.stevenhankin.clj-auto-completion)

(defn
  ^{:doc "Returns a map of partial prefix matches to sequences of strings groups from a sorted input"}
  produce-map [s ac-map]
  (let [{b :buffer, m :maxgrpsize, p :matchedptr :or {p 1}} ac-map
        to-take (- m (count b))
        b (concat b (take to-take s))] ;; Rebind a topped-up buffer
    (if (empty? b)
      ac-map
      (let [hash-str (subs (first b) 0 p)]
        (if (ac-map hash-str)
            (recur s (assoc ac-map :matchedptr (inc p))) ;; Already exists a hash, so will try a larger hash
            (let [match-seq (first (partition-by #(and (>= (count %) p ) (= (subs % 0 p) hash-str)) b))]
                (recur (drop to-take s) (assoc (assoc ac-map hash-str match-seq) :matchedptr 1))))))))



