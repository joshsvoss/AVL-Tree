package data_structures;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Iterator;

class AvlIterator implements Iterator<Integer> { //TODO should iterator be public or package?
	
	AvlTree tree;
	Node curNode;
	LinkedList<Integer> list;
	
	/** Constructor of the avl tree's iterator.  
	 * @param tree the avl tree that is to be iterated.
	 */
	AvlIterator(AvlTree tree) { // TODO same here, should constructor be public instead of package?
		this.tree = tree;
		this.list = new LinkedList<Integer>();
		
		// Populate the linked list
		populateList(tree.getRoot());
	}
	
	/* Private helper method that recursively perforems a reverse in-order traversal
	 * to fill up the linkedlist in order.  
	 * @param root Root of the tree
	 */
	private void populateList(Node root) { //TODO optimization: just save current node and get successor each time
		// TODO optimization: Populate linked list in backwards order, so lowest number will be first node, faster pop.
		
		
		// Recursive case:
		if (root != null) {
			
			populateList(root.getLeft());
			list.add(root.getData());
			populateList(root.getRight());
		}
		
	}

	@Override
	public boolean hasNext() {
		return (list.peek() != null);
	}

	@Override
	public Integer next() {
		Integer toReturn = list.poll();
		
		if (toReturn == null) {
			throw new NoSuchElementException();
		}
		else {
			return toReturn;
		}
		
		
	}

	@Override
	public void remove() {
		
		throw new UnsupportedOperationException();
		
	}

}
