package data_structures;
import java.util.NoSuchElementException;

import java.util.Iterator;

class AvlIterator implements Iterator<Integer> { //TODO should iterator be public or package?
	
	AvlTree tree;
	Node curNode;
	
	AvlIterator(AvlTree tree) { // TODO same here, shoudl constructor be public instead of package?
		this.tree = tree;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
