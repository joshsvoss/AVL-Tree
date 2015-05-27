package data_structures;

public class Driver {
	
	public static void main(String[] args) {
		
		// Try adding to tree:
		AvlTree tree = new AvlTree();
		tree.add(5);
		tree.add(2);
		tree.add(3);
		tree.add(1);
		tree.add(-1);
		tree.add(-1);
		tree.add(-2);
		tree.add(-3);
		
	
		
		// Print the tree
		tree.printTree();
		
		System.out.println("Finished Main.");
	}

}
