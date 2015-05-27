package data_structures;

public class Driver {
	
	public static void main(String[] args) {
		
		// Try adding to tree:
		AvlTree tree = new AvlTree();
//		tree.add(5);
//		tree.add(2);
//		tree.add(3);
//		tree.add(1);
//		tree.add(-1);
//		tree.add(-1);
//		tree.add(-2);
//		tree.add(-3);
//		tree.add(-4);
//		tree.add(-5);
//		tree.add(-6);
//		tree.add(-7);
		
		for (int i= 0; i < 20; i++) tree.add(i);
		
		
		// Print the tree
		tree.printTree();
	
		// Check if tree contains what you're looking for:
		System.out.println(tree.contains(4));
		System.out.println(tree.contains(4));
				
		
		System.out.println("Finished Main.");
	}

}
