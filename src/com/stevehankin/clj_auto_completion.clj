(ns
    ^{:doc "Auto Completion Tree"
      :author "Steve Hankin"}
    com.github.stevenhankin.clj-auto-completion)

(defn
    ^{:doc "Return an initial empty tree (a node) configured for a maximum selection size ms"
      :added "1.0.0"}
   make-tree [ms]
   (assoc {} :type 'node :max-group-size ms :value-set #{} :child-map {} :radix nil)
)

(def example-tree (make-tree 2))

(declare add-value-to-node)
(declare add-value-to-leaf)

(defn
    ^{:doc "Return a tree with the supplied value added"
      :added "1.0.0"}
   add-value [t v]
   (let [type (t :type)
         ms   (t :max-group-size)]
      (if (= type 'node)
         (add-value-to-node t v)
         (add-value-to-leaf t v ms)
      )
   )
)


(declare create-node)

(defn
    ^{:doc "Add a value to the node, by retrieving (or creating) the required radix and descending"
      :private true
      :added "1.0.0"}
   add-value-to-node [t [v & more]] ; v represents the current character and determines the radix
   (let [cm (:child-map t)]
      (if-let [cb (get cm v)]
         nil ; TODO - update this block
         (let [nb (create-node)]  ;  create a new block
            nb ; TODO - associate in the value
         )
      )
   ) 
)

(def example-add-value-to-node (add-value-to-node {:child-map {}} "Apple"))


(defn
    ^{:doc "Create an empty node. Similar to make-tree but does not contain the group size"
      :private true
      :added "1.0.0"}
   create-node [] (assoc {} :type 'node :value-set #{} :child-map {} :radix nil)
)


(defn
    ^{:doc "Add a value to an existing block, potentially converting the block into a node if max size (ms) is reached"
      :private true
      :added "1.0.0"}
   add-value-to-leaf [t [v & more] ms]
      (let [size (count (t :value-set))]
         (if (>= size ms)
            nil ; TODO - make this leaf a block and populate the children
            (assoc t :value-set (conj (t :value-set) v)) ; add the entry
         )
      )
)


(def example-value-add (add-value {:value-set #{\a 1 \b 2 \c 4} :type 'leaf :max-group-size 10} "apple"))


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



