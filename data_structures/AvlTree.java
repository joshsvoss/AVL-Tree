package data_structures;

import java.util.Iterator;
//TODO used this?
// Import for the iterator:
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class AvlTree implements Iterable<Integer> {


	private static final int MIN_NODES_OF_HEIGHT_1 = 2;
	private static final int MIN_NODES_OF_HEIGHT_0 = 1;
	private static final int HEIGHT_ONE = 1;
	private static final int HEIGHT_ZERO = 0;
	private static final int MIN_HEIGHT = 0;
	// Magic numbers
	private final static int INIT_SIZE = 0;
	private static final int NULL_NODE_HEIGHT = -1;
	private static final int LEFT_UNBALLANCED = 2; //TODO make sure these are consistent with balance method
	private static final int RIGHT_UNBALLANCED = -2;
	private static final int ROOT_DEPTH = 0;
	
	// Fields
	private Node root;
	private int size;
	
	
	
	
	
	/** Default no-args constructor.
	 * 
	 */
	public AvlTree() {
		
		this.size = INIT_SIZE;
		root = null;  
		
	}
	
	/** A constructor that builds the tree by adding the elements in the input
	 * array one by one.  If a value appears more than once in the list,
	 * only the first appearance is added.  
	 * @param data
	 */
	public AvlTree(int[] data) {
		
		this();
		
		if (data != null) {
			for (int element: data ) {
				this.add(element);
			}
		}
		
	}
	
	/** A copy constructor that creates a deep copy of the given AvlTree.
	 * This means that for every node or any other internal object of 
	 * the given tree, a new, identical object, 
	 * is instantiated for the new tree.  (the internal object is simply
	 * referenced from it).  The new tree must contain all
	 * the values of the given tree but not necessarily the same structure.
	 * 
	 * @param avlTree an AVL tree.
	 */
	public AvlTree(AvlTree avlTree) {
		
		this();
		if (avlTree != null) {
			Iterator<Integer> it = avlTree.iterator();
			
			while (it.hasNext()) {
				this.add(it.next());
			}
		}
	}
	
	
	
	/** Add a new node with the given key to the tree.
	 * @param newValue The value of the new node to be added.
	 * @return true if the value to add is not already
	 * in the tree and was successfully added.  False otherwise.
	 */
	public boolean add(int newValue) {
		int beforeSize = this.size;
		
		// Call an internal method so you can pass a node reference as well:
		this.root = recAdd(newValue, root);  // TODO how can I both return the sub tree roots, and if the add was succesful or not?
	
		// TODO DEBUG Delete:
		if (this.size != beforeSize && this.size != beforeSize +1) {
			System.out.printf("DEBUG: size has increased by more then 1.  Before: %d, after: %d %n", beforeSize, this.size);
		}
		
		return beforeSize != this.size; // If the size has changed then the insertion occurred.  
	}
	
	// TODO should helper methods have javadocs?
	/* Helper method to allow us to use recursion to add integers.  
	 * @param newValue the int to be added
	 * @param subRoot the root of the subtree being worked on
	 * @return
	 */
	private Node recAdd(int newValue, Node subRoot) { 
		
		if (subRoot == null)  {
			// Then we've found the spot to place our element:
			subRoot = new Node(newValue); // TODO initialize parent here as well?
			this.size++;
		}
		
		else if (newValue < subRoot.getData() )  { 
			// Then the data should be placed to the LEFT of this subroot.
			subRoot.setLeft( recAdd(newValue, subRoot.getLeft()) );
			
			// Now check balance to see if rotation is needed on this side:
			// Expect to see an extra long LEFT side: 2
			if (heightBalance(subRoot) == LEFT_UNBALLANCED) {
				// Check on what side of left CHILD was the node placed:
				if (newValue > subRoot.getLeft().getData()) { //TODO should I consolidate the double rotation into one method?
					// Left-Right case: Then we need to perform double rotation
					leftRightRotation(subRoot);
					subRoot = leftLeftRotation(subRoot);  //TODO Repitition?  Consolidate to one "doubelRortation" caller method?
				}
				
				else if (newValue < subRoot.getLeft().getData()) {
					// Then we only need one rotation
					subRoot = leftLeftRotation(subRoot);
				}
				
				// Otherwise, newValue == the left child's data, -> duplicate, no add performed.

			}
		}
		
		else if (newValue > subRoot.getData()) {
			// Then the recursive calls will start going down RIGHT of this root.
			subRoot.setRight( recAdd(newValue, subRoot.getRight()) );
			
			// Now check balance to see if rotation is needed on this side:
			// Expect to see an extra long RIGHT side: -2 
			if (heightBalance(subRoot) ==  RIGHT_UNBALLANCED) {
				
				// Check on what side of RIGHT CHILD was the node placed:
				if (newValue < subRoot.getRight().getData()) {
					// Right-Left case -> perform double rotation.
					rightLeftRotation(subRoot);
					subRoot = rightRightRotation(subRoot); //TODO repition,
				}
				
				else if (newValue > subRoot.getRight().getData()) {
					subRoot = rightRightRotation(subRoot);
				}
				
				// Otherwise, newValue == the left child's data, -> duplicate, no add performed.
				
			}
		}
		
		else if (newValue > subRoot.getData()) {
			// Then the value should be inserted to the right of this node:
			subRoot = recAdd(newValue, subRoot.getRight());
		}
		
		else {
			// In this case, the newValue is equal to the subRoot's data,
			// And we don't want duplicates in the tree, so nothing is done.
		}
		
		// Return either the same node, or the new node if the insertion has taken place.  
		return subRoot;
	}

	/* This method performs the rotation necessary in the Left-Right case, converting it
	 * to the Left-Left case, as illustrated below
	 * 
	 *      3                3
	 *     /                /
	 *    1       -->      2
	 *     \              /  
	 *      2            1
	 * 
	 * This rotation is to be followed by the leftLeftRotation().
	 * 
	 * @param node3 the root of this subtree, which in this totation is unchanged.  Highest value of 
	 * the three nodes.  
	 */
	private void leftRightRotation(Node node3) { //TODO is this correct that it returns void?
		
		
		Node node1 = node3.getLeft();
		Node node2 = node1.getRight();
		
		node3.setLeft(node2); // Which is the boy node.
		// reset dad's reference to null
		node1.setRight(node2.getLeft());
		node2.setLeft(node1);
		
		

		
	}
	
	/* This method performs the rotation necessary in the left-left case.
	 * As depicted in the following visual:
	 * 
	 *      3            2
	 *     /            / \
	 *    2       -->  1   3
	 *   /               
	 *  1          
	 *  
	 *
	 * @param node3 the node with the data of highest value.  
	 * This is to be rotated with the node of second highest value.
	 * 
	 * @return the new root of this subtree so it can be reset recursively up the tree.
	 */
	private Node leftLeftRotation(Node node3) {
		
		// Set 2 node's right reference to point at 3 node
		Node node2 = node3.getLeft();
		Node beta = node2.getRight();
		node2.setRight(node3); 
		node3.setLeft(beta);
		
		return node2; // Return the new ROOT of this subtree.  
		
	}
	
	/* This method performs the rotation necessary in the Right-Left case,
	 * as depicted in the illustration:
	 * 
	 *    1            1
	 *     \            \
	 *      3   -->      2 
  	 *     /              \
	 *    2                3
	 * 
	 *  This rotation is to be followed by the rightRightRotation();
	 * 
	 * @param node1 the root of this subtree, not changed in this rotation.
	 */
	private void rightLeftRotation(Node node1) {
		
		Node node3 = node1.getRight();
		Node node2 = node3.getLeft();
		
		// Rotate:
		node1.setRight(node2);
		node3.setLeft(node2.getRight()); // Don't forget grandkids
		node2.setRight(node3);
		
		
	}
	
	
	/*  This method performs the rotation necessary in the Right-Right case,
	 * as depicted in the illustration:
	 *    1               2
	 *     \             / \
	 *      2   -->     1   3 
  	 *       \           
	 *        3           
	 * 
	 * This rotation is either performed by itself in the Right-Right case, 
	 * or performed after rightLeftRotation() in the Right-Left case.
	 * 
	 * @param node1 the original root of the subtree, of least value.
	 * @return the new root of this subtree, the middle value of the three nodes.
	 */
	private Node rightRightRotation(Node node1) {
		
		Node node2 = node1.getRight();
		Node beta = node2.getLeft();
		node2.setLeft(node1);
		
		// reset straggling node1.right pointer
		node1.setRight(beta);
		
		// Return new subtree root so it can be recursively assigned up the tree
		return node2;
		
	}

	/** Check whether the tree contains the given input value
	 * 
	 * @param searchVal the value to be searched for
	 * @return the depth of the node (0 for the root) with the given
	 * value if it was found in the tree, -1 otherwise.  
	 */
	public int contains(int searchVal) {
		return recContains(searchVal, ROOT_DEPTH, this.root);
	}
	
	/* This helper method allows recursive calls to itself so the 
	 * external contains() method can be implemented recursively.  
	 * @param searchVal int, the value to be looked for.
	 * @param curDepth the depth at this level of the recursion, at the calling level.
	 * @param curNode the node currently being inspected.
	 * @return int representing the depth of the node, -1 if not found, 0 for root.
	 */
	private int recContains(int searchVal, int curDepth, Node curNode) {
		
		// Base case 1: if we've reached a null, then searchVal isn't here
		if (curNode == null) {
			return -1;
		}
		
		// Base case 2: if curNode contains our searchVal
		else if (curNode.getData() == searchVal) {
			return curDepth;  // TODO 1-off here?
		}
		
		// Recursive case: call contains on the next level down
		else {
			Node nextSide = ( searchVal < curNode.getData() ) ? curNode.getLeft() : curNode.getRight();
			return recContains(searchVal, curDepth + 1, nextSide);
		}
	}
	
	/** Removes the node with the given value from the tree, if it exists.
	 * @param toDelete the value of the node to be deleted.
	 * @return true if the given node was found and delete, otherwise false.  
	 */
	public boolean delete(int toDelete) {
		
		int beforeSize = this.size();
		
		// Call internal recursive method
		this.root = recDelete(toDelete, this.root);
		
		// Check after call to see if size changed, to decide what boolean to return
		return (this.size() != beforeSize);
	}
	
	/* This internal helper method allows delete to be implemented recursively.
	 * 
	 * @param toDelete the value that we want to delete
	 * @param subRoot root of the subtree we're currently looking at.
	 * @return the subroot of the current tree, this may change during deletion and rebalancing.
	 */
	private Node recDelete(int toDelete, Node subRoot) {
		// If tree is empty, do nothing and return the tree so everything stays as is
		// Or alternatively, we've reached null because toDelte doesn't exist in our tree
		if (subRoot == null) {
			return subRoot;
		}
			
		// if toDelete is less than our root, go left:
		else if (toDelete < subRoot.getData() )  {
			subRoot.setLeft(recDelete(toDelete, subRoot.getLeft()));
		}
		
		// Likewise for the right side and greater than:
		else if (toDelete > subRoot.getData()) { 
			subRoot.setRight(recDelete(toDelete, subRoot.getRight()));
		}
		
		// Otherwise, we've found the desired node!
		else {
			subRoot = literalDelete(subRoot);
		}
		
		// Now check rotation conditions and call the appropriate rotation,
		// As long as it's not null (when a node was deleted by assigning itself to it's
		// right child, who could be null.
		if (subRoot != null) {
			int b = this.heightBalance(subRoot);
			
			if (b > 1) {
				// Then we're long on the left side:
				// Check if LL or LR case:
				if (heightBalance(subRoot.getLeft()) >= 0) {
					// Then we have left-left case:
					subRoot = leftLeftRotation(subRoot);
				}
				else {
					// Otherwise we have left-right case:
					leftRightRotation(subRoot);
					subRoot = leftLeftRotation(subRoot); // TODO repitiion as seen 5 lines above
				}
			}
		
		
			else if (b < -1) {
				// Then we're long on the right side!
				// Figure out if we're RL or RR case:
				if (heightBalance(subRoot.getRight()) < 0) {
					// Then we're in Right-right:
					subRoot = rightRightRotation(subRoot);
				}
				else {
					// Otherwise we must be in Right-Left:
					rightLeftRotation(subRoot);
					subRoot = rightRightRotation(subRoot);
				}
			}
		}
			
		return subRoot;
	}
	
	/* This internal method performs the actual REMOVING of the undesired data.
	 * 
	 * @param removeNode the node which contains the data we want to remove
	 * @return Node a node reference back up the call stack to reasign the parent pointers.
	 */
	private Node literalDelete(Node removeNode) {
		
		// Decrement size!
		this.size--;
		
		// If the current node has no left child, replace it with the right child, 
		// Which will either be a Node or null:
		if (removeNode.getLeft() == null) return removeNode.getRight();
		
		// Otherwise if the node has only a left child, replace with the left
		else if (removeNode.getRight() == null) return removeNode.getLeft();
		
		// Otherwise, our node has TWO CHILDREN.  Find it's successor on the right, copy it's data 
		// and remove that node.  
		else {
			int replacingData = getSuccessor(removeNode.getRight());
			removeNode.setData(replacingData);
			// Now call the recursive delete for that same data on the right subtree:
			removeNode.setRight(recDelete(replacingData, removeNode.getRight()));
			
			// Return the newly initialized root node
			return removeNode;
		}
	}
	
	private int getSuccessor(Node root) {
		
		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		
		// Once we've found a null, return the node right before the null
		return root.getData();
	}
	
	/** 
	 * @return the number of nodes in the tree.
	 */
	public int size() {
		return this.size;
	}

	/* 
	 * @return an iterator on the Avl Tree.  The returned iterator
	 * iterates over the tree nodes in an ascending order, and does NOT
	 * implement the remove() method.  
	 */
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new AvlIterator(this);
	}

//	public void forEach(Consumer action) {  //TODO what's a consumer action?
//		// TODO Auto-generated method stub
//		
//	}
	
	//TODO  DO we need a spliterator too?
	
	/** Calculates the minimum number of nodes in an AVL tree of height h.
	 * 
	 * @param h the height of the tree (non-negative number).
	 * @return the minimum number of nodes in an avl tree of that height.  
	 */
	public static int findMinNodes(int h) { 
		//TODO 1) base case start with 0?  2) validate negative heights? 3) Magic numbers? 
		
		// Make sure to return -1 for negative heights
		if (h < MIN_HEIGHT) {
			return NULL_NODE_HEIGHT;
		}
		
		// base cases
		if (h == HEIGHT_ZERO) {
			return MIN_NODES_OF_HEIGHT_0;
		}
		else if (h == HEIGHT_ONE) {
			return MIN_NODES_OF_HEIGHT_1;
		}
		
		// Recursive cases: 
		else{
			return findMinNodes(h - HEIGHT_ONE) + findMinNodes(h - MIN_NODES_OF_HEIGHT_1) + HEIGHT_ONE; 
			
		}
		
	}
	


	
	/** This method returns the height balance between the left and right subtrees 
	 * of the node.
	 * @return
	 */
	public int heightBalance(Node n) { // TODO moving this also to Avl Tree??
		return getHeight(n.getLeft()) - getHeight(n.getRight()); 
	}
	
	/* This method returns the height of the node in the argument as 
	 * an integer.  REMEMBER: we're treating the height of a leaf as 0 NOT 1.
	 * So height is really the counting of the EDGES not the NODES on the longest path.  
	 * @param subRoot
	 * @return
	 */
	private static int getHeight(Node subRoot) {
		
		if (subRoot == null) {
			return NULL_NODE_HEIGHT;
		}
		
		// Recursive case:
		else {
			return intMax(getHeight(subRoot.getLeft()), getHeight(subRoot.getRight())) + HEIGHT_ONE;
		}
	}
	
	/* This helper method returns the max of two integers
	 * @param a one integer
	 * @param b another integer
	 * @return in the max of the two
	 */
	private static int intMax(int a, int b) {
		return (a > b) ? a : b;  //TODO is this the correct logics?
	}
	
	/* This package level method returns a reference to the root node.
	 * It's used in the iterator to populate the list.
	 * @return Node, the root of the tree.
	 */
	Node getRoot() { //TODO is this allowed?
		return this.root;
		
	}
	

	

}
