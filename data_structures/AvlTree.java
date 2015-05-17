package data_structures;

import java.util.Iterator;
import java.util.function.Consumer;

public class AvlTree implements Iterable<Integer> {
	
	
	
	/** Default no-args constructor.
	 * 
	 */
	public AvlTree() {
		
	}
	
	/** A constructor that builds the tree by adding the elements in the input
	 * array one by one.  If a value appears more than once in the list,
	 * only the first appearance is added.  
	 * @param data
	 */
	public AvlTree(int[] data) {
		
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
		
	}
	
	
	
	/** Add a new node with the given key to the tree.
	 * @param newValue The value of the new node to be added.
	 * @return true if the value to add is not already
	 * in the tree and was successfully added.  False otherwise.
	 */
	public boolean add(int newValue) {
		
	}
	
	/** Check whether the tree contains the given input value
	 * 
	 * @param searchVal the value to be searched for
	 * @return the depth of the node (0 for the root) with the given
	 * value if it was found in the tree, -1 otherwise.  
	 */
	public int contains(int searchVal) {
		
	}
	
	/** Removes the node with the given value from the tree, if it exists.
	 * @param toDelete the value of the node to be deleted.
	 * @return true if the given node was found and delete, otherwise false.  
	 */
	public boolean delete(int toDelete) {
		
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

	public void forEach(Consumer action) {
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
	

	

}
