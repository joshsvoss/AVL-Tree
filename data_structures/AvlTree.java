package data_structures;

import java.util.Iterator;


public class AvlTree implements Iterable<Integer> {


	// Magic numbers
	private final static int INIT_SIZE = 0;
	private static final int NULL_NODE_HEIGHT = -1;
	private static final int LEFT_UNBALLANCED = 2; //TODO make sure these are consistent with balance method
	private static final int RIGHT_UNBALLANCED = -2;
	private static final int ROOT_DEPTH = 0;
	
	// Fields
	Node root;
	int size;
	
	
	
	
	
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
		
		for (int element: data ) {
			this.add(element);
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
	public AvlTree(NardeenAvlTree avlTree) {
		// TODO implement
		// TODO grab iterator from avl tree and call next until null?  Then add em all.
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
			subRoot = recAdd(newValue, subRoot.getLeft());
			
			// Now check balance to see if rotation is needed on this side:
			// Expect to see an extra long LEFT side: 2
			if (subRoot.heightBalance() == LEFT_UNBALLANCED) {
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
			subRoot = recAdd(newValue, subRoot.getRight());
			
			// Now check balance to see if rotation is needed on this side:
			// Expect to see an extra long RIGHT side: -2 
			if (subRoot.heightBalance() ==  RIGHT_UNBALLANCED) {
				
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
	private void leftRightRotation(Node node3) { 
		
		
		Node node1 = node3.getLeft();
		
		node3.setLeft(node1.getRight()); // Which is the boy node.
		// reset dad's reference to null
		node1.setRight(null);
		
		node3.getLeft().setLeft(node1);

		
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
		node2.setRight(node3); 
		node3.setLeft(null);
		
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
		node2.setRight(node3);
		// Reset double pointer to null:
		node3.setLeft(null);
		
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
		node2.setLeft(node1);
		
		// reset straggling node1.right pointer
		node1.setRight(null);
		
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
	 * @param curNode the node currenlty being inspected.
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
		// TODO dont' forget to reduce size here.
	}
	
	/** 
	 * @return the number of nodes in the tree.
	 */
	public int size() {
		
	}

	/* 
	 * @return an iterator on the Avl Tree.  The returned iterator
	 * iterates over the tree nodes in an ascending order, and does NOT
	 * implement the remove() method.  
	 */
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void forEach(Consumer action) {  //TODO what's a consumer action?
		// TODO Auto-generated method stub
		
	}
	
	//TODO  DO we need a spliterator too?
	
	/** Calculates the minimum number of nodes in an AVL tree of height h.
	 * 
	 * @param h the height of the tree (non-negative number).
	 * @return the minimum number of nodes in an avl tree of that height.  
	 */
	public static int findMinNodes(int h) {
		
	}
	
	@Override
	public String toString() {
		BTreePrinter.printNode(this.root);
	}
	
	public int height(Node n) {
		if (n == null) {
			return NULL_NODE_HEIGHT;
		}
		else {
			return n.height; //TODO should node have height field?
		}
	}
	

	

}
