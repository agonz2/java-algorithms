/*
 * Alexander Gonzalez Ramirez
 */

class BSTNode {
    String key;
    BSTNode left, right;

    public BSTNode (String data){
        key = data;
        left = right = null;
    }
}

public class NodeClass {
    int counter;
    BSTNode root;

    // Set data for root and counter on constructor call
    NodeClass(){
        root = null;
        counter = 0;
    }

    // Recursive BST node insertion
    BSTNode insertNode(BSTNode root, String key){
        // Starting at root if node is empty, fill the node with String key as data
        if (root == null){
            root = new BSTNode(key);
            return root;
        }

        // Otherwise convert key param with root key
        int k = key.compareTo(root.key);

        // Traverse down the tree
        if (k < 0){
            root.left = insertNode(root.left, key);
        } else if (k > 0) {
            root.right = insertNode(root.right, key);
        }

        return root;
    }

    // Insertion to start recursion
    public void insert(String key){root = insertNode(root, key);}

    // Post Order Transversal
    private void postOrder(BSTNode node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        counter++;
    }

    // Counts each node encountered with post order and returns total nodes
    public int getNodeTotal(){
        postOrder(root);
        return counter;
    }

    // Recursively goes down both sides and calculates the furthest from root
    private int treeDepth(BSTNode node) {
        if (node == null) return 0;

        int left = treeDepth(node.left);
        int right = treeDepth(node.right);

        return Math.max(left, right) + 1;
    }

    // Start depth recursion
    public int getTreeDepth(){return treeDepth(root);}
}
