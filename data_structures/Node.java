package data_structures;

public class Node {
	
	// Fields
	private Node left;
	private Node right;
	private Node parent;
	private int value;
	
//	//TODO possible fields:
//	private int height;
//	private int balance; 
//	
	
	
	public Node() {
		// No need to do anything since fields are automatically initialized as null.
	}
	
	public Node(int value) {
		this.setValue(value);
	}
	
	// TODO create a FULL-ARGS constructor here, with left right and parent to be assigned?
	
	
	
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	

}
