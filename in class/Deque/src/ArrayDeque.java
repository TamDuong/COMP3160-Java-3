/**
 * Implementation of Deque using an array.  We keep track of the array indices
 * corresponding to the front and back of the queue.  Enqueueing/dequeueing from
 * the back involves incrementing/decrementing the back index, respectively.
 * Enqueueing/dequeueing from the front involves decrementing/incrementing the
 * front index, respectively.  Both the front and back indices are allowed to wrap
 * around from (data.length - 1) to 0 and vice-versa if necessary.  All operations
 * are O(1) when implemented this way, since there is no need to shift any
 * existing array elements.
 * 
 */

import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {
	private E[] data = (E[]) (new Object[5]); // where the data is actually kept
	private int size = 0; // tracks the number of elements in the deque
	private int front = 0, back = data.length - 1; // tracks the front and back
													// indices

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public E peekFront() {
		if (!isEmpty())
			return data[front];
		else
			throw new NoSuchElementException();
	}

	@Override
	public E peekBack() {
		if (!isEmpty())
			return data[back];
		else
			throw new NoSuchElementException();
	}

	@Override
	public void enqueueFront(E newItem) {
		if (size == data.length)
			reallocate();
		front--;
		if (front < 0)
			front = data.length - 1;
		data[front] = newItem;
		size++;
	}

	@Override
	public void enqueueBack(E newItem) {
		if (size == data.length)
			reallocate();
		back++;
		if (back == data.length)
			back = 0;
		data[back] = newItem;
		size++;
	}

	// Reallocates space for the data array (twice the capacity of the old one),
	// and resets the front and back indices (front = 0, back = size - 1).
	private void reallocate() {
		E[] newData = (E[]) (new Object[2 * data.length]);
		for (int i = 0, j = front; i < data.length; i++, j = (j + 1)
				% data.length) {
			newData[i] = data[j];
		}
		data = newData;
		front = 0;
		back = size - 1;
	}

	@Override
	public E dequeueFront() {
		if (!isEmpty()) {
			E toReturn = data[front];
			front++;
			if (front == data.length)
				front = 0;
			size--;
			return toReturn;
		} else
			throw new NoSuchElementException();
	}

	@Override
	public E dequeueBack() {
		if (!isEmpty()) {
			E toReturn = data[back];
			back--;
			if (back < 0)
				back = data.length - 1;
			size--;
			return toReturn;
		} else
			throw new NoSuchElementException();
	}

	public String toString() {
		String r = "ArrayDeque (size = " + size + ", front = " + front
				+ ", back = " + back + "): ";
		for (int i = 0, j = front; i < size; i++, j = (j + 1) % data.length) {
			r += data[j] + " ";
		}
		return r;
	}

	// Test main
	public static void main(String[] args) {
		Deque<String> d = new ArrayDeque<>();
		System.out.println(d);
		d.enqueueBack("Capt. Morgan");
		d.enqueueFront("Kraken");
		d.enqueueFront("Sailor Jerry");
		d.enqueueFront("Stingray");
		d.enqueueBack("Bacardi");
		System.out.println(d);
		d.enqueueFront("Guinness");
		d.enqueueBack("Natty Lite");
		System.out.println(d);
	}

}
