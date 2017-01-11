import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class BSTSpellChecker 
{
	private BinarySearchTree<String> words = new BinarySearchTree<>();
	
	public void add(String s)
	{
		words.addIterator(s);
	}
	
	public boolean contains(String s)
	{
		if (words.findIterator(s) != null)
			return true;
		else
			return false;
	}
	
	// bad way(BW) of implement method for adding all words from a file
	public void storeInfoBW(String fileName) throws FileNotFoundException
	{	
		Scanner input = new Scanner(new File(fileName));
		while (input.hasNext()) {
			String lineInfo = input.nextLine();
			add(lineInfo);
		}
	}
	
	// better way of implement method for adding all words from a file
	// start from adding the item of mid index of array then recursively adding the mid of left and right of array
	public void storeInfo(String fileName) throws FileNotFoundException
	{
		ArrayList<String> s = new ArrayList<String>();
		Scanner input = new Scanner(new File(fileName));
		while (input.hasNext())
			s.add(input.nextLine());
		
		// Use string indicate the item has been added to replace the element of the index has been added to the bst
		int WordNum = 0, 	startIndex = 0,		endIndex;
		while (WordNum < s.size()){
			// find the next available startIndex
			while (("This item has been added").equals(s.get(startIndex)))		
				startIndex++;
			
			endIndex = startIndex;
			// take the index infront of added item or the last index if it get to the end of the array as an endIndex
			while (endIndex + 1 < s.size() && !("This item has been added").equals(s.get(endIndex+1)))	
				endIndex++;
			
			int temp = (startIndex + endIndex) / 2;
			add(s.get(temp));
			s.set(temp, "This item has been added");
			WordNum++;			
		}
	}
}
