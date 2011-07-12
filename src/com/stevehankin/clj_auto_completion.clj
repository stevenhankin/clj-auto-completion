(ns
    ^{:doc "Auto Completion Tree"
      :author "Steve Hankin"}
    com.github.stevenhankin.clj-auto-completion)

(defn
    ^{:doc "Return an initial empty tree (a node) configured for a maximum selection size ms"
      :added "1.0.0"}
   make-tree [ms]
   (assoc {} :type 'node :max-group-size ms :value-set #{} :children {} :radix nil)
)

(def example-tree (make-tree 2))

(declare add-value-to-node)
(declare add-value-to-leaf)

(defn
    ^{:doc "Return a tree with the supplied value added"
      :added "1.0.0"}
   add-value [t [v & more]]
   (let [type (t :type)]
      (if (= type 'node)
         (add-value-to-node t v)
         (add-value-to-leaf t v)
      )
   )
)


(defn
    ^{:doc "Add a value to the node, by retrieving (or creating) the required radix and descending"
      :private true
      :added "1.0.0"}
   add-value-to-node [t v]
      nil ; TODO - everything 
)


(defn
    ^{:doc "Add a value to an existing block, potentially converting the block into a node if max size (ms) is reached"
      :private true
      :added "1.0.0"}
   add-value-to-leaf [t v ms]
      (let [size (count (t :value-set))]
         (if (>= size ms)
            nil ; TODO - make this leaf a block and populate the children
            (assoc t :value-set (conj (t :value-set) v)) ; add the entry
         )
      )
)


(def example-value-add (add-value-to-leaf {:value-set #{\a 1 \b 2 \c 4} :type 'leaf} "apple" 10))


(defn
    ^{:doc "Return 0 or more matching items"
      :added "1.0.0"}
   find-partial? [t [v & more]]
   (let [type (t :type)]
      (if (= type 'node)
         nil ; TODO - descend
         (t :value-set) ; Not a node - so must be a block.  Return the items.
      )
   )
)



