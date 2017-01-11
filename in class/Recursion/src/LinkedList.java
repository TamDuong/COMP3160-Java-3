import java.util.NoSuchElementException;

/*
 * LinkedList class that implements some of its operations recursively.
 * 
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
		for (int i = 1; i <= 10; i++)
			list.addToHead(i);
		System.out.println(list);
    }
}
