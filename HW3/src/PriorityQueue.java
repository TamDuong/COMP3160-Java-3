/*
 * Implementation of a priority queue using an array-based heap.  The highest-priority
 * element is always stored at the top of the heap (index 0 of the array).  Enqueueing
 * corresponds to adding a new element to the heap, while dequeueing corresponds to
 * removing the top element.
 * 
 * We are using a min-heap, so "smaller" elements are considered higher priority.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E extends Comparable<E>>
{
	// This array list stores the heap data.  The root of the heap is at index 0.
    //  For an element at index n in this list, its left child is at index 2n + 1,
    //  its right child is at index 2n + 2, and its parent is at index (n - 1) / 2
    //  (using integer division).
    private ArrayList<E> data = new ArrayList<>();
    
    // Optional Comparator to determine how the items in the heap should be ordered.
    //  See the compare method in this class for details.
    private Comparator<E> c = null;

    // Default constructor
    public PriorityQueue()
    {
    }

    // Constructor that allows specifying a Comparator object to use.  See the compare
    //  method in this class for details.
    public PriorityQueue(Comparator<E> c)
    {
	this.c = c;
    }
    
    // Returns whether the heap is empty.
    public boolean isEmpty()
    {
	return data.isEmpty();
    }
    
    // Adds the specified newItem to the heap.
    public void enqueue(E newItem)
    {
	data.add(newItem);	// add to the end of the array list
	
	// track the child/parent indices in the array list
	int cIndex = data.size() - 1;
	int pIndex = (cIndex - 1) / 2;

	// compare the child vs. parent, and swap if the child is smaller than the parent... repeat up the heap
	while (compare(cIndex, pIndex) < 0) {
	    swap(cIndex, pIndex);
	    cIndex = pIndex;
	    pIndex = (cIndex - 1) / 2;
	}
    }
    
    // Removes and returns the top item from the heap.
    public E dequeue()
    {
	if (data.size() == 0)
	    throw new NoSuchElementException();
	
	if (data.size() == 1)
	    return data.remove(0);
	
	E temp = data.get(0);	// item to return
	data.set(0, data.remove(data.size() - 1));	// replace the top element with the last element, and remove the last element

	// compare the new root vs. its smaller child, and swap if the new root is larger... repeat down the heap
	int pIndex = 0;
	while (true) {
	    int lIndex = 2*pIndex + 1, rIndex = lIndex + 1, sIndex = lIndex;	// we assume the left child is the smaller one initially

	    if (lIndex >= data.size())	// left child doesn't exist (we have reached the bottom of the heap) - stop
		break;
	    
	    if (rIndex < data.size() && compare(rIndex, lIndex) < 0) 	// check if the right child exists and
												// is smaller than the left child
		sIndex = rIndex;
	    
	    if (compare(pIndex, sIndex) > 0) {	// check if the parent is larger than the smaller child, swap if needed
		swap(pIndex, sIndex);
		pIndex = sIndex;
	    } else	// parent is not larger than the smaller child - stop
		break;
	}
	
	return temp;
    }
    
    // Swaps the elements at index1 and index2 in the array list
    private void swap(int index1, int index2)
    {
	E temp = data.get(index1);
	data.set(index1, data.get(index2));
	data.set(index2, temp);
    }

    // Compares the elements at index1 and index2 in the array list.  If no Comparator object is specified,
    //  this just uses compareTo.  If a Comparator exists, this uses the Comparator's compare method
    //  instead.
    private int compare(int index1, int index2)
    {
	if (c == null)
	    return data.get(index1).compareTo(data.get(index2));
	else
	    return c.compare(data.get(index1), data.get(index2));
    }
    
	public String toString()
	{
	int level = 1, count = 0;
	String s = "";

	for(int i = 0; i < data.size(); i++, count++){
		if (count == level){
		s += "\n";
		count = -1;
		level = level + level;
		i--;
		} else
		s += data.get(i) + "	";
	}
	return s;
	}

    
    public static void main(String[] args)
    {
    	Integer[] random = {4, 1, 10, 11, 14, 16, 12};
    	random = heapsort(random);
    	for (int i = 0; i < random.length; i++)
    		System.out.println(random[i]);
    	
    	System.out.println("\n");
    	
    	PriorityQueue<Integer> numbers = new PriorityQueue<>();
		int[] num = {0, 8, 4, 10, 24, 7, 5, 9,5,9,90,89,97,56,87,32,44};
		
		for (int i : num)
			numbers.enqueue(i);
		System.out.println(numbers);

    	
    }
    
    // the big O of enqueue and dequeue operation inside of the for loop is log(n) 
    // and the big O of the for loop is n,
    // so the big O of this method is O(nlog(n))
	public static Integer[] heapsort(Integer[] a) {
		Integer[] sorted = new Integer[a.length];
		PriorityQueue<Integer> numbers = new PriorityQueue<>();
		
    	for (Integer s : a)
    		numbers.enqueue(s);
    	for (int i = 0; i < a.length; i++)
    		sorted[i] = numbers.dequeue();
    	return sorted;
	}
}
