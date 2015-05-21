package data_structures;

public class Node {
	
	// Fields
	private Node left;
	private Node right;
	private Node parent;
	private int data;
	
//	//TODO possible fields:
//	private int height;
//	private int balance; 
//	
	
	
	public Node() {
		// No need to do anything since fields are automatically initialized as null.
	}
	
	public Node(int data) {
		this.setData(data);
	}
	
	// TODO create a FULL-ARGS constructor here, with left right and parent to be assigned?
	
	
	//TODO add validation for below getters and setters?
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	

}
