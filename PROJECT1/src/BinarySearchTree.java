import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
/*
 * Implementation of a binary search tree.
 */

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
    private E deleteReturn;	// tracks which item was just deleted from the tree (so that the delete method can return something)

    public void addIterator(E newItem)
    {
	if (root == null)	// special case for adding to the root (we need to modify the root reference)
	    root = new Node<E>(newItem, null, null);
	else {
	    Node<E> temp = root;
		while (true) {
			int compare = newItem.compareTo(temp.data);
			
			if (compare < 0 && temp.left == null) {
				temp.left = new Node<E>(newItem, null, null);
				break;
			} else if (compare > 0 && temp.right == null) {
				temp.right = new Node<E>(newItem, null, null);
				break;
			} else if (compare < 0) {
				temp = temp.left;
			} else if (compare > 0) {
				temp = temp.right;
			}
		}
    }
    }
    
    public E findIterator(E someItem)
    {	
    Node<E> temp = root;
    while (temp != null) {
    	int compare = someItem.compareTo(temp.data);
    	if (compare == 0)
    		return temp.data;		// someItem found
    	else if (compare > 0)
    		temp = temp.right;
    	else
    		temp = temp.left;
    }
    return null;			// Return null if list is empty or had reach the end of list
    }
    
    // Wrapper method for delete.  Returns the item that was deleted if found, or null if item not found.
    public E delete(E someItem)
    {
	root = delete(someItem, root);
	return deleteReturn;
    }
    
    // Recursively deletes someItem from the tree rooted at where.  Returns a
    //  reference to the root of that tree, with someItem deleted.
    private Node<E> delete(E someItem, Node<E> where)
    {
	if (where == null) {	// base case - empty tree
	    deleteReturn = null;
	    return null;
	}
	
	int compare = someItem.compareTo(where.data);
	if (compare < 0) {
	    where.left = delete(someItem, where.left);
	    return where;
	} else if (compare > 0) {
	    where.right = delete(someItem, where.right);
	    return where;
	} else {	// base cases - someItem found
	    deleteReturn = where.data;
	    if (where.left == null && where.right == null) {	// case 1 - where has no children
		return null;
	    } else if (where.right == null) {			// case 2a - where has only a left child
		return where.left;
	    } else if (where.left == null) {			// case 2b - where has only a right child
		return where.right;
	    } else {						// case 3 - oh noes!  two children
		where.data = findAndDeleteIOP(where);
		return where;
	    }
	}
    }
    
    // Finds and deletes the in-order predecessor of the node where.  Returns the value of
    //  the IOP that was deleted.
    private E findAndDeleteIOP(Node<E> where) {
	Node<E> parent = where, temp = where.left;

	// at the end of this loop, temp is pointing at the IOP, and parent is pointing
	//  at the IOP's parent
	while (temp.right != null) {
	    parent = temp;
	    temp = temp.right;
	}
	
	if (parent == where)	// if parent and temp did not move down the tree at all (i.e., the IOP is where.left)
	    parent.left = temp.left;
	else
	    parent.right = temp.left;
	return temp.data;
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
    }
}
