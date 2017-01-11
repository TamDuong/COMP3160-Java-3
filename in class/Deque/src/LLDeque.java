/**
 * Implementation of Deque using a linked list.  The front of the
 * deque corresponds to the head of the list, and the back of the
 * deque corresponds to the tail of the list.
 * 
 */

import java.util.NoSuchElementException;

public class LLDeque<E> implements Deque<E> {
	// Nested class representing a single node in the list
	private static class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	// we keep track of both the head and tail nodes so we have easy access
	// to the end of the list
	private Node<E> head, tail;

	// Big-O: O(1)
	@Override
	// the @Override annotation tells the compiler to complain if we're not
	// actually overriding
	// anything with this method
	public boolean isEmpty() {
		return (head == null);
	}

	// Big-O: O(1)
	@Override
	public E peekFront() {
		if (!isEmpty())
			return head.data;
		else
			throw new NoSuchElementException();
	}

	// Big-O: O(1)
	@Override
	public E peekBack() {
		if (!isEmpty())
			return tail.data;
		else
			throw new NoSuchElementException();
	}

	// Big-O: O(1)
	@Override
	public void enqueueFront(E newItem) {
		head = new Node<E>(newItem, head); // add a new node to the head of the
											// list
		if (head.next == null) // if I have only 1 element in the list, also
								// update tail reference
			tail = head;
	}

	// Big-O: O(1)
	@Override
	public void enqueueBack(E newItem) 
	{
		if (isEmpty()) {
			enqueueFront(newItem);
		} else {
			tail.next = new Node<E>(newItem, null); // add a new node to the
													// tail of the list
			tail = tail.next;
		}
	}

	// Big-O: O(1)
	@Override
	public E dequeueFront() {
		if (!isEmpty()) {
			E toReturn = head.data;
			head = head.next; // remove the head node from the list
			if (isEmpty()) // update tail node if removal results in an empty
							// list
				tail = null;
			return toReturn;
		} else
			throw new NoSuchElementException();
	}

	// Big-O: O(n)
	@Override
	public E dequeueBack() {
		if (head == tail) { // if the list has 0 or 1 elements...
			return dequeueFront();
		} else {
			Node<E> temp = head; // find the next-to-last node in the list
			while (temp.next.next != null)
				temp = temp.next;
			E toReturn = tail.data;
			tail = temp; // ... and remove the tail node
			temp.next = null;
			return toReturn;
		}
	}

	public String toString() {
		String r = "head -> ";
		for (Node<E> temp = head; temp != null; temp = temp.next)
			r += temp.data + " -> ";
		r += "null";
		return r;
	}

	// Test main
	public static void main(String[] args) {
		Deque<String> d = new LLDeque<>();
		System.out.println(d);
		d.enqueueFront("Absolut");
		d.enqueueFront("Svedka");
		d.enqueueFront("Grey Goose");
		d.enqueueFront("Skyy");
		d.enqueueBack("Ciroc");
		System.out.println(d);
		System.out.println(d.dequeueFront());
		System.out.println(d);
		System.out.println(d.dequeueBack());
		System.out.println(d);
		System.out.println(d.dequeueFront());
		System.out.println(d);
	}
}
