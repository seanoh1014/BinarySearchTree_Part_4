public class Runner {

    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(45);
        tree.add(25);
        tree.add(15);
        tree.add(10);
        tree.add(20);
        tree.add(35);
        tree.add(30);
        tree.add(40);
        tree.add(65);
        tree.add(55);
        tree.add(50);
        tree.add(60);

        System.out.println("In order     " + tree.inOrder());
        System.out.println("Pre order    " + tree.preOrder());
        System.out.println("Level Order  " + tree.levelOrder());
        System.out.println(tree.printTree(5));

        // tree.delete(25);
        // tree.delete(65);
		// tree.delete(10);
		// tree.delete(20);
		// tree.delete(40);
		tree.delete(35);

        System.out.println("In order     " + tree.inOrder());
        System.out.println("Pre order    " + tree.preOrder());
        System.out.println("Level Order  " + tree.levelOrder());
        System.out.println(tree.printTree(5));
        
    }
}
