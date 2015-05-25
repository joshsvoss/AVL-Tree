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
	public Node(int data, Node left, Node right) {
		this(data);
		this.left = left;
		this.right = right;
	}
	
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

	/** This method returns the height balance between the left and right subtrees 
	 * of the node.
	 * @return
	 */
	public int heightBalance() {
		return this.left.getHeight() - this.right.getHeight(); //TODO make sure you're consistent.  
	}
	
	/** This method returns the height of this node instance.
	 * @return height of the node.  -1 if null, 0 if node is a leaf, and incrementally upwards, where
	 * each node's height = max (leftHeight, rightHeight) + 1  
	 */
	public int getHeight() {
		//TODO implement
	}
	
	

}
