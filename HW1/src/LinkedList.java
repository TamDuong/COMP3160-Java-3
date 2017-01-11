//#3 GRADE: 15/15

import java.util.NoSuchElementException;

/*
 * LinkedList class that implements some of its operations recursively.
 * With contains, search, and remove method added to the front of the class.
 */
public class LinkedList<E>
{
    // Nested class representing a single node in the list (same as the Node class we used in LLDeque)
    private static class Node<E>
    {
		private E data;
		private Node<E> next;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
    }
    
    private Node<E> head;
    
    // Wrapper method for contains
    public boolean contains(E someItem) {
    	return contains(someItem, head);
    }
    
    // Recursive implementation of contains
    private boolean contains(E someItem, Node<E> where)
    {
    	if (where == null)						// base case - end of list (item not found)
    		return false;
    	else if (where.data == someItem)					// base case - item found
    		return true;
    	else 
    		return contains(someItem, where.next);
    }
    
    // Wrapper method for search
    public int search(E someItem) {
    	return search(someItem, head, 0);
    }
    
    // Recursive implementation of search
    private int search(E someItem, Node<E> where, int index)
    {
    	if (where == null)
    		return -1;
    	else if (where.data == someItem)
    		return index;
    	else
    		return search(someItem, where.next, index + 1);
    }
    
   // Wrapper method for remove
    public E remove(int index) {
    	if (index < 0)
    		throw new NoSuchElementException();
    	else if (index == 0) {
    		E temp = head.data;
    		head = head.next;
    		return temp;
    	}	else
    		return remove(index - 1, head);
    }
    
    // Recursive implementation of remove
    private E remove(int index, Node<E> where)
    {
    	if (index == 0) {
    		E temp = where.next.data;
    		where.next = where.next.next;
    		return temp;
    	}	else
    			return remove(index - 1, where.next);
    }
    
	// Adds the specified newItem to the head of the list.  (Not recursive)
    public void addToHead(E newItem)
    {
    	head = new Node<E>(newItem, head);
    }

    // Wrapper method for size.
    public int size()
    {
    	return size(head);
    }

    // Recursive implementation of size.  The size of the list starting
    //  from node "where" is 1 (for where itself), plus the size of the
    //  rest of the list.
    private int size(Node<E> where)
    {
		if (where == null) // base case - end of list
			return 0;
		else
			return 1 + size(where.next);
	}

	// Wrapper method for get.
	public E get(int index) {
		if (index < 0 || index >= size()) // check for valid index
			throw new NoSuchElementException();
		else
			return get(index, head);
	}

	// Recursive implementation of get.
	private E get(int index, Node<E> where) {
		if (index == 0) // base case - we have reached the desired location in the list
			return where.data;
		else
			return get(index - 1, where.next);
	}

    // Wrapper method for addToTail.
    public void addToTail(E newItem)
    {
		if (head == null) // check for empty list
			addToHead(newItem);
		else
			addToTail(newItem, head);
	}

	// Recursive implementation of addToTail.
	private void addToTail(E newItem, Node<E> where) {
		if (where.next == null) // base case - where is pointing at the tail of
								// the list
			where.next = new Node<E>(newItem, null);
		else
			addToTail(newItem, where.next);
	}

	// Wrapper method for toString.
	public String toString() {
		return "head -> " + toString(head);
	}

    // Recursive implementation of toString.  To get the string for the list starting
    //  from node "where", take that node's data and add it to the string for the
    //  rest of the list.
    private String toString(Node<E> where)
    {
		if (where == null) // base case - end of list
			return "null";
		else
			return where.data + " -> " + toString(where.next);
	}
    
    // Test main
    public static void main(String[] args)
    {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i <= 3; i++)
			list.addToHead(i);
		System.out.println(list);
		
		System.out.println(list.contains(0));
		System.out.println(list.contains(1));
		
		System.out.println(list.search(1));
		System.out.println(list.search(0));
		
		System.out.println(list.remove(2));		// 2 is the index
		System.out.println(list);
		System.out.println(list.remove(-1));
    }
}
