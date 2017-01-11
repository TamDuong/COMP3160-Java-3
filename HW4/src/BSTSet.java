//GRADE: 7.5/7.5
/*
 * Implements a set (a collection of distinct, unordered elements) using a
 * binary search tree as the underlying data structure.  Most of the methods
 * in this class are written by delegation to the existing BST methods.
 */
public class BSTSet<E extends Comparable<E>>
{
    private BinarySearchTree<E> data = new BinarySearchTree<>();	// where the set data is kept
    
    public BSTSet<E> union(BSTSet<E> s)
    {
    	BSTSet<E> setReturn = new BSTSet<E>();
    	for (E item : this.data)
    		setReturn.add(item);
    	for (E item : s.data)
    		setReturn.add(item);
    	return setReturn;
    }
    
    public BSTSet<E> intersection(BSTSet<E> s)
    {
    	BSTSet<E> setReturn = new BSTSet<E>();
    	for (E item: this.data){
    		if (s.contains(item))
    			setReturn.add(item);
    	}
    	return setReturn;
    }
    
    // Adds the specified newItem to the set.  No need to explicitly check for
    //  duplicate elements, since BST's add method already handles this.
    public void add(E newItem)
    {
	data.add(newItem);
    }

    // Removes and returns the specified someItem from the set.  Returns null
    //  if someItem doesn't exist in the set.
    public E remove(E someItem)
    {
	return data.delete(someItem);
    }

    // Returns whether the set contains the specified someItem.
    public boolean contains(E someItem)
    {
	return data.find(someItem) != null;
    }
    
    public String toString()
    {
	String r = "Set object containing these elements: {";
	for (E item : data)			//*MC* this will call the next() method in InorderIterator from BST class.
	    r += item + ", ";
	r = r.substring(0, r.length() - 2);	// cut off the last comma and space
	r += "}";
	return r;
    }
    
    public static void main(String[] args)
    {
	BSTSet<String> s = new BSTSet<>();
	String[] stuff = {"Piper Cub", "Paper airplane", "B-52", "AC-130", "F-22", "Flying shark with laser", "X-wing starfighter", "Death Star"};
	for (String item : stuff)
	    s.add(item);
	System.out.println("Set s:\n" + s);
	
	BSTSet<String> s2 = new BSTSet<>();
	String[] stuff2 = {"Piper Cub", "Paper airplane", "B-52", "rocket", "RPG"};
	for (String item : stuff2)
		s2.add(item);
	System.out.println("Set s2:\n" + s2);
	
	BSTSet<String> union = s.union(s2);
	System.out.println("Set union of s and s2:\n" + union);
	
	BSTSet<String> intersect = s.intersection(s2);
	System.out.println("Set intersect of s and s2:\n" + intersect);
    }
}
