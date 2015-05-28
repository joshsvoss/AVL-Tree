package data_structures;
import java.util.Iterator;

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
		
		for (int i= 0; i < 100; i++) tree.add(i);
//		tree.printTree();
		System.out.printf("SIZE: %d %n", tree.size());
		
		// Try out iterator
		Iterator<Integer> it = tree.iterator();
		for (int i = 0; i < tree.size(); i ++) {
			System.out.println(it.next());
		}
		
		// Try copy constructor:
//		AvlTree tree2 = new AvlTree(tree);
//		tree2.printTree();
		
//		for (int i= 0; i < 24; i++) tree.delete(i);
//		System.out.printf("SIZE: %d %n", tree.size());
		
//		for (int i= 0; i > -6; i--) tree.add(i);
		
		
//		// Print the tree
//		tree.printTree();
	
		// Check if tree contains what you're looking for:
		System.out.println(tree.contains(4));
		System.out.println(tree.contains(4));
				
		
		System.out.println("Finished Main.");
	}

}
