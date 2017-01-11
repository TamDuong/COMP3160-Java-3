import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Trie {
	// nested Node class for each single string (more like char but in type string)
	public static class Node<String>
	{
	private String data;
	private boolean aWord;
	private ArrayList<Node> next = new ArrayList<>();
	
	public Node(String data, boolean aWord){
		this.data = data;
		this.aWord = aWord;
	}
	}
	
	Node<String> root = new Node("", false);
	
	public void add(String s){
		if (s.length() == 0)
			root.aWord = true;
		else {
			Node<String> temp = root;
			for (int i = 0; i < s.length(); i++) {
			Node<String> newItem = new Node(s.substring(i, i+1), false);
			if (i == s.length() - 1)
				newItem.aWord = true;
			
			if (temp.next.size() == 0) {
				temp.next.add(newItem);
				temp = temp.next.get(0);
			} else
				for (int j = 0; j < temp.next.size(); j++)
					if (newItem.data.equals(temp.next.get(j).data)){
						temp = temp.next.get(j);
						break;
					} else if (j == temp.next.size() - 1) {
						temp.next.add(newItem);
						temp = temp.next.get(j);			}
			}	}	}
	
	public boolean contains(String s){
		boolean result = true;
		Node<String> temp = root;
		for (int i = 0; i < s.length() && result; i++){
			result = false;
			String split = s.substring(i, i+1);
				for (int j = 0; j < temp.next.size(); j++)
					if (i == s.length() - 1 && split.equals(temp.next.get(j).data))
						return temp.next.get(j).aWord;
					else if (split.equals(temp.next.get(j).data)) {
						result = true;
						temp = temp.next.get(j);
						break;	}
		}return result;
	}
	
	public Set<String> autocomplete(String s){
		Set<String> result = new TreeSet<String>();
		Node<String> temp = root;
		if (contains(s))
			result.add(s);
		
		for (int i = 0; i < s.length(); i++) {
			String split = s.substring(i, i+1);
			for (int j = 0; j < temp.next.size(); j++)
				if (split.equals(temp.next.get(j).data)){		//if data in temp at j == split
					temp = temp.next.get(j);
					break;
				} else if (j == temp.next.size() - 1) 			//if j has go through temp.next.size() and cannot find a match  
					return result;								//then it will just return an empty set
		}
		for (int i = 0; i < temp.next.size(); i++){
			String extendOfS = (String) temp.next.get(i).data;
			if (temp.next.get(i).aWord)
				result.add(s + extendOfS);
			}
		return result;
	}
	
	public Set<String> closeMatches(String s){
		Set<String> result = new TreeSet<String>();
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		Node<String> temp = root;
		ArrayList<String> matches = new ArrayList<String>();
		
		if (s.length() == 0)
			for (String i : letters)
				matches.add(i+s);
		else
			for (int i = 0; i < s.length(); i++){
				matches.add(s.substring(0, i) + s.substring(i));								//deletion
				for (String j : letters) {
					matches.add(s.substring(0, i) + j + s.substring(i));						//insertion
					matches.add(s.substring(0, i) + j + s.substring(i+1));						//replacement
				}
			}
		for (String j : matches)
			if (contains(j))
				result.add(j);
		return result;
	}
	
	public void storeInfo(String filename) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File(filename));
		while (input.hasNext()) {
			String lineInfo = input.nextLine();
			add(lineInfo);
		}
	}
	
	public static void main(String[] args)
	{
	}
}