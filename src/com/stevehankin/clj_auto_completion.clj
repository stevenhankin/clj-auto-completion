


(defn create-tree [] {})


; Get matches in tree for string
(defn get-matches [t [s & more]]
   (if-let [v (t s)]
      v
      (if more
         (recur t (cons (str s (first more)) (rest more) ) )
         nil
      )
   )
)

(def max 2)

; add to tree the full string represent by current partial
(defn add-string
   ([t f]
      (add-string t f f)
   )
   ([t f part]
      (let [p (first part)
            rem (subs part 1)]
      (if-let [v (t (str p))]
         (let [size (count v)]
            (if (< size max)
               (assoc t (str p) (conj v f))
               (let  [nt (dissoc t (str p))]
                  ;(map #(add-string nt % (subs % 0 (inc (count p)))) v )
                  (add-string nt
                              (first v) 
                              (cons (subs (first v) 0 (inc (count part))) 
                                    (subs (first v) (inc (count part)) ))) 
               )
            )
         )
         (assoc t p (vector f))
      )
      )
   )
)

(add-string {"ba" ["bandaid" "banana"] "moo" ["moon" "moonshine" "moomin"]} "banyan")


(cons (subs "banyan" 0 (inc (count "ban"))) (subs "banyan" (inc (count "ban")) ))


