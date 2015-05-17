package data_structures;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AvlTree implements Iterable<Integer> {
	
	
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

	public Spliterator spliterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
