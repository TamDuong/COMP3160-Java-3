import java.util.Arrays;

/**
 * Implementations of some better-than-quadratic sorting algorithms: Shell sort,
 * merge sort, heapsort, quicksort.
 * 
 * Shell sort (with our selection of gap sizes) is about O(n^1.5), while the
 * other three are O(n log n) on average. Quicksort can fall to O(n^2) on
 * certain data sets if the pivot is poorly chosen.
 * 
 * Also worth noting is that merge sort requires O(n) extra memory to store its
 * temporary arrays as it works through the merges.
 *
 */

public class BetterSorts
{
    // Shell sorts the array a
    public static void shellSort(int[] a)
    {
	// determine a gap size
	int gapSize = a.length / 2;

	while (gapSize > 0) {
	    // insertion sort the array elements at {0, gapSize, 2*gapSize, ...},
	    // then {1, 1 + gapSize, 1 + 2*gapSize, ...},
	    // then {2, 2 + gapSize, 2 + 2*gapSize, ...}, etc.
	    for (int k = 0; k < gapSize; k++) {
		for (int i = gapSize + k; i < a.length; i += gapSize) {
		    int toInsert = a[i];

		    int j = i - gapSize;
		    while (j >= 0 && toInsert < a[j]) {
			a[j + gapSize] = a[j];
			j -= gapSize;
		    }

		    a[j + gapSize] = toInsert;
		}
	    }

	    // now reduce the gap size and repeat until the gap size becomes 0
	    gapSize = (gapSize == 2) ? 1 : (int) (gapSize / 2.2);
	}
    }

    // Wrapper method for merge sort
    public static void mergeSort(int[] a)
    {
	mergeSort(a, 0, a.length - 1, "");
    }

    // Recursively merge sorts the array a between indices start and end,
    // inclusive.
    private static void mergeSort(int[] a, int start, int end, String s)
    {
	System.out.println(s + "calling mergeSort(" + start + ", " + end + ")");
	
	// base case is when start/end are the same -- in this case there's only
	// one element, so no action is needed
	if (end - start > 0) {
	    // find the middle index
	    int mid = (start + end) / 2;

	    // sort each half
	    mergeSort(a, start, mid, s + " ");
	    mergeSort(a, mid + 1, end, s + " ");

	    // now merge the two sorted halves together!
	    System.out.println(s + " merging between " + start + " and " + end);

	    // temp array to hold the merged elements
	    int[] temp = new int[end - start + 1];

	    // i tracks the position in the left half, j tracks position in
	    // right half, k tracks position in merged array
	    int i = start, j = mid + 1, k = 0;
	    while (i <= mid && j <= end) {
		if (a[i] < a[j])
		    temp[k++] = a[i++];
		else
		    temp[k++] = a[j++];
	    }

	    // copy remaining elements from left half
	    while (i <= mid)
		temp[k++] = a[i++];

	    // copy remaining elements from right half
	    while (j <= end)
		temp[k++] = a[j++];

	    // the merged elements are in a temp array right now, so copy them
	    // back into original array
	    for (i = 0; i < temp.length; i++)
		a[start + i] = temp[i];
	} else {
	    System.out.println(s + " single element, no action taken");
	}
    }

    // Heapsort generic
    // Heapsorts the specified array of ints. We start by assuming
    // that index 0 is the root of a max-heap.
    public static <T extends Comparable<T>> void heapsort(T[] a)
    {
	// go through the array elements at indices 1, 2, 3, ..., placing each
	// one into the max-heap
	for (int i = 1; i < a.length; i++) {
	    int cIndex = i, pIndex = (cIndex - 1) / 2;

	    while (cIndex > 0 && a[cIndex].compareTo(a[pIndex]) > 0) {
		T temp = a[cIndex];
		a[cIndex] = a[pIndex];
		a[pIndex] = temp;

		cIndex = pIndex;
		pIndex = (cIndex - 1) / 2;
	    }
	}
	
	// now repeatedly remove the top element from the max-heap, placing it
	// at the end of the array
	for (int i = 0; i < a.length; i++) {

	    // swap root with last element in the array
	    T topHeapElement = a[0];
	    a[0] = a[a.length - 1 - i];
	    a[a.length - 1 - i] = topHeapElement;

	    // work our way back down the heap, swapping as necessary
	    int pIndex = 0;
	    while (true) {
		// check for left child
		int lIndex = 2 * pIndex + 1;
		if (lIndex >= a.length - 1 - i) // no left child - heapify is
						// done!
		    break;

		int cIndex = lIndex; // index of the greater child (initially we
				     // assume it's the left)

		// check for right child, and see if it's greater than the left
		int rIndex = lIndex + 1;
		if (rIndex < a.length - 1 - i && a[rIndex].compareTo(a[lIndex]) > 0)
		    cIndex = rIndex;

		// check if parent is less than greater child, swap if so
		if (a[pIndex].compareTo(a[cIndex]) < 0) {
		    T temp = a[cIndex];
		    a[cIndex] = a[pIndex];
		    a[pIndex] = temp;

		    pIndex = cIndex;
		} else
		    break; // parent is not < greater child - heapify is done!
	    }
	}
    }

    // Wrapper method for quicksort
    public static <T extends Comparable<T>>void quicksort(T[] a)
    {
	quicksort(a, 0, a.length - 1);
    }

    // Recursively quicksorts the array a between indices start and end,
    // inclusive
    private static <T extends Comparable<T>> void quicksort(T[] a, int start, int end)
    {
    if (end - start <= 4) {		// i think when is 4 then insertion sort will be a good time
    							// since using the quicksort then it will perform too much work
    	insertionSort(a, start, end);
    }
    else if (start < end) { // base case is when start is no longer < end (i.e.,
			   // nothing to sort)
	    int j = quicksortPartition(a, start, end);
	    quicksort(a, start, j - 1);
	    quicksort(a, j + 1, end);
	}
    }

    // Partitions the array a between indices start and end, inclusive. We use
    // the start index as the pivot element, and arrange all the array elements
    // such that the ones <= pivot are on the left, and the ones > pivot are on the right.
    private static <T extends Comparable<T>> int quicksortPartition(T[] a, int start, int end)
    {
	// pick a pivot
	T pivot = a[start];

	int lower = start, upper = end;
	while (lower < upper) {
	    // look for the first element (from the left) that's greater than
	    // the pivot
	    while (lower < end) {
		if (a[lower].compareTo(pivot) > 0)
		    break;
		lower++;
	    }

	    // look for the first element (from the right) that's less than or
	    // equal to the pivot
	    while (upper > start) {
		if (a[upper].compareTo(pivot) <= 0)
		    break;
		upper--;
	    }

	    // swap lower/upper indices if lower < upper
	    if (lower < upper) {
		T temp = a[lower];
		a[lower] = a[upper];
		a[upper] = temp;
	    }
	}

	// swap the pivot (at index start) with index upper
	T temp = a[start];
	a[start] = a[upper];
	a[upper] = temp;

	return upper;
    }
    
    // Generic version of insertion sort.  This will work to sort an array of any
    //  type that implements Comparable.
    public static <T extends Comparable<T>> void insertionSort(T[] a, int start, int end)
    {
	// insert each item relative to the items on its left, starting from index 1
	for (int i = start + 1; i < end + 1; i++) {
	    T thingToInsert = a[i];
	    
	    // find out where to insert this item
	    int j = i - 1;
	    while (j >= start && thingToInsert.compareTo(a[j]) < 0) {
		a[j+1] = a[j];
		j--;
	    }
	    
	    a[j+1] = thingToInsert;				//if thingToInsert is where it need to be then this statement pretty much do nothing.
	}
    }

	public static void main(String[] args){
    	// when the array get really big and when the side that going to be sort is 
    	// 2, 4, in this case it will sort a little faster
		Integer[] a = {12, 7, 8, 3, 2, 1, 10, 5, 9, 11};
		long startT, endT;
		startT = System.currentTimeMillis();
		System.out.println(Arrays.toString(a));
		quicksort(a);
		System.out.println(Arrays.toString(a));
		endT = System.currentTimeMillis();
		System.out.println("Time took: " + (endT - startT));
	}
}