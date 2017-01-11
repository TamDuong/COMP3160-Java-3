/**
 * Implementations of insertion sort, selection sort, and bubble sort.
 * All of these are quadratic sorts -- they require O(n^2) time on average.
 * Insertion sort improves to O(n) in the best case of already-sorted data.
 * 
 */

import java.util.Arrays;

public class SortingAlgorithms
{
    // Generic version of insertion sort.  This will work to sort an array of any
    //  type that implements Comparable.
    public static <T extends Comparable<T>> void insertionSort(T[] a)
    {
	// insert each item relative to the items on its left, starting from index 1
	for (int i = 1; i < a.length; i++) {
	    T thingToInsert = a[i];
	    
	    // find out where to insert this item
	    int j = i - 1;
	    while (j >= 0 && thingToInsert.compareTo(a[j]) < 0) {
		a[j+1] = a[j];
		j--;
	    }
	    
	    a[j+1] = thingToInsert;				//if thingToInsert is where it need to be then this statement pretty much do nothing.
	}
    }
    
    // Selection sort
    public static void selectionSort(int[] a)
    {
	for (int j = 0; j < a.length; j++) {
	    // find the minimum element starting from index j
	    int min = a[j], minIndex = j;
	    for (int i = j + 1; i < a.length; i++) {
		if (a[i] < min) {
		    min = a[i];
		    minIndex = i;
		}
	    }

	    // swap the minimum element with index j
	    int temp = a[j];
	    a[j] = a[minIndex];
	    a[minIndex] = temp;
	}
    }    

    // Bubble sort
    public static void bubbleSort(int[] a)
    {
	for (int j = 0; j < a.length; j++) {
	    // compare a[i] vs. a[i+1], swap if a[i] is greater
	    for (int i = 0; i < a.length - 1; i++) {
		if (a[i] > a[i + 1]) {
		    int temp = a[i];
		    a[i] = a[i + 1];
		    a[i + 1] = temp;
		}
	    }
	}
    }
    
    public static void main(String[] args)
    {
	String[] stuff = {"fun", "not fun", "moderately fun", "not so fun", "absolutely fun"};
	int[] a = {12, 7, 8, 3, 2, 1, 10};
	System.out.println(Arrays.toString(a));
	bubbleSort(a);
	System.out.println(Arrays.toString(a));
    }
}
