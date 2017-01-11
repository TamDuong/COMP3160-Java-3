import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellCheckerClient {
	public static void main(String[] args)
	{
		Trie wordlist = new Trie();									// implement trie 
		BSTSpellChecker wordlist2 = new BSTSpellChecker(),			// implement bst in a better way
						wordlist3 = new BSTSpellChecker();			// implement bst in a bad way
		String[] test = {"attack", "Capture", "kill", "eat", "nomnom", "GO", "wild", ""};	// "nomnom", "Go", "Capture" should return false
																// since both BSTSpellChecker object should contain only lower case letter
		long startT, endT;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the name of the file to load data: ");
		String filename = input.nextLine();
		
		// read file
		try {
			wordlist.storeInfo(filename);				// store info to Trie
			// find words in string array test in worldlist
			startT = System.currentTimeMillis();
			for (String s : test)
				System.out.println(s + " spelled correct.\t\t" + wordlist.contains(s));
			endT = System.currentTimeMillis();
			System.out.println("Time took to find words from file for Trie: " + (endT - startT) + "\n");
		
			wordlist2.storeInfo(filename);				// store info to BSTSpellChecker in balanced manner
			// find words in string array test in worldlist2
			startT = System.currentTimeMillis();
			for (String s : test)
				System.out.println(s + " spelled correct.\t\t" + wordlist2.contains(s));
			endT = System.currentTimeMillis();
			System.out.println("Time took to find words from file in a balanced manner: " + (endT - startT) + "\n");
			
			wordlist3.storeInfoBW(filename);			// store info to BSTSpellChecker linearly
			// find words in string array test in worldlist2
			startT = System.currentTimeMillis();
			for (String s : test)
				System.out.println(s + " spelled correct.\t\t" + wordlist3.contains(s));
			endT = System.currentTimeMillis();
			System.out.println("Time took to find words from file linearly: " + (endT - startT) + "\n");
		} catch (FileNotFoundException e) {
			System.out.println("This file does not exit!");
		}
	}
}
