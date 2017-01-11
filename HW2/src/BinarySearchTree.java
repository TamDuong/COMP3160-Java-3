/*
 * Implementation of a binary search tree with addIterative and findIterative methods
 */

// The "extends Comparable<E>" in the type parameter means that E must be from a class that implements
//  the Comparable interface (located in java.lang).  This is needed since you must be able to "order"
//  elements in some way to place them into a BST.
public class BinarySearchTree<E extends Comparable<E>>
{
    // Nested class representing one node of the tree.  Very similar to the Node
    //  class we used for LinkedList -- the only difference is that we keep
    //  track of two children instead of a single "next" node.
    private static class Node<E>
    {
	private E data;
	private Node<E> left, right;
	
	public Node(E data, Node<E> left, Node<E> right)
	{
	    this.data = data;
	    this.left = left;
	    this.right = right;
	}
    }
    
    private Node<E> root;
    
    
    // add newItem to the tree
    public void addIterative(E newItem)
    {
    	if (root == null)
    		root = new Node<E>(newItem, null, null);
    	else {
    		Node<E> temp = root;
        	while (temp != null) {
        		int compare = newItem.compareTo(temp.data);
        		if (compare < 0 && temp.left == null) {						// add newItem to temp left node
        			temp.left = new Node<E>(newItem, null, null);
        			break;
        		} else if (compare > 0 && temp.right == null) {				// add newItem to temp right node
        			temp.right = new Node<E>(newItem, null, null);
        			break;
        		} else if (compare < 0) {									// newItem will be at the left side of temp
        			temp = temp.left;
        		} else if (compare > 0)										// newItem will be at the right side of temp
        			temp = temp.right;
        	}
    	}
    }
 
// another way of addIterative
//    public void addIterative2(E newItem)
//    {
//    if (root == null)
//    	root = new Node<E>(newItem, null, null);
//    else {
//    	Node<E> temp = root;
//    	while (temp != null) {
//    		int compare = newItem.compareTo(temp.data);
//    		if (compare < 0)
//    			if (temp.left == null) {
//    				temp.left = new Node<E>(newItem, null, null);
//    				break;
//    			} else
//    				temp = temp.left;
//    		else if (compare > 0)
//    			if (temp.right == null) {
//    				temp.right = new Node<E>(newItem, null, null);
//    				break;
//    			} else
//    				temp = temp.right;
//    	}
//    }
//    }
       
    //find if someItem exist in the tree. Return item if found or null if not found
    public E findIterative(E someItem)
    {
    	Node<E> temp = root;
    	while (temp != null) {
    		int compare = someItem.compareTo(temp.data);
    		if (compare == 0)
    			return temp.data;
    		else if (compare < 0)
    			temp = temp.left;
    		else
    			temp = temp.right;
    	}	
    	return null;							// return null if the item not found or root = null
    }
 
    
    // Wrapper method for toString
    public String toString()
    {
	return toString(root, "");
    }
    
    // Recursive version of toString
    private String toString(Node<E> where, String offset)
    {
	if (where == null)
	    return offset + "null";
	else
	    return offset + where.data + "\n" + toString(where.left, offset + " ") + "\n" + toString(where.right, offset + " ");
    }
    
    public static void main(String[] args)
    {
	BinarySearchTree<String> bst = new BinarySearchTree<>();
	String[] stuff = {"Prius", "Aston Martin Vanquish", "Corvette C7 Stingray", "El Camino", "Panzer tank", "M1A1 Abrams", "Optimus Prime"};
	for (String s : stuff)
	    bst.addIterative(s);
	System.out.println(bst);
	for (String s : stuff)
		System.out.println(bst.findIterative(s));
	System.out.println(bst.findIterative("chicken"));
	}
}
