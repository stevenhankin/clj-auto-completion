


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

(defn map-merge 
   [mapcol]
   (let [firstm (first mapcol)
         restm (rest mapcol)]
      (if (> (count restm) 0)
         (merge firstm (map-merge restm))
         firstm
      )
   )
)

; add to tree the full string represent by current partial
(defn add-string
   ([t f]
      (add-string t f 1)
   )
   ([t f ofs] ; tree, full value, offset
      (let [p (subs f 0 ofs)
            rem (subs f ofs)]
      (if-let [v (t p)] ; is there a match for partial search str p?
         (let [size (count v)] ; check size of the vector
            (if (< size max) 
               (assoc t p (conj v f)) ; size is ok so add
               (let  [nt (dissoc t (str p)) ; tree with old vector removed
                      newofs (inc ofs)]
                  (map  #(add-string {} % newofs) (conj v f) )
               )
            )
         )
         (assoc t p (vector f)) ; not exists, so add this first element
      )
      )
   )
)

(add-string {"b" ["bandaid" "banana"] "moo" ["moon" "moonshine" "moomin"]} "banyan")




