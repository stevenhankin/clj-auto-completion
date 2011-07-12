(ns
    ^{:doc "Auto Completion Tree"
      :author "Steve Hankin"}
    com.github.stevenhankin.clj-auto-completion)

(defn
    ^{:doc "Return an initial empty tree (a node) configured for a maximum selection size ms"
      :added "1.0.0"}
   make-tree [ms]
   (assoc {} :type 'node :max-group-size ms :items {} :children {} :radix nil)
)

(def example-tree (make-tree 2))

(declare add-value-to-node)
(declare add-value-to-block)

(defn
    ^{:doc "Return a tree with the supplied value added"
      :added "1.0.0"}
   add-value [t [v & more]]
   (let [type (t :type)]
      (if (= type 'node)
         (add-value-to-node t v)
         (add-value-to-block t v)
      )
   )
)


(defn
    ^{:doc "Add a value to the node, by retrieving (or creating) the required radix and descending"
      :private true
      :added "1.0.0"}
   add-value-to-node [t v]
      nil ; TODO 
)


(defn
    ^{:doc "Add a value to an existing block, potentially converting the block into a node"
      :private true
      :added "1.0.0"}
   add-value-to-block [t v]
      nil ; TODO
)


(def example-value-add (add-value {:items {\a 1 \b 2 \c 4}} "apple"))


(defn
    ^{:doc "Return 0 or more matching items"
      :added "1.0.0"}
   find-partial? [t [v & more]]
   (let [type (t :type)]
      (if (= type 'node)
         nil ; TODO - descend
         (t :items) ; Not a node - so must be a block.  Return the items.
      )
   )
)



