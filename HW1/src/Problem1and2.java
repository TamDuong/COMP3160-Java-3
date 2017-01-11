//#1 GRADE: 3/5 (iterative works fine but 2nd half of question not attempted)
//#2 GRADE: 5/5
// Iterative version of the Fibonacci method
// Compare 2 String Recursive method
public class Problem1and2 
{
	// Returns the nth Fibonacci number. (non recursive)
	public static long fibo(int n)
	{
		if (n == 0)
			return 0;

		long firstPre = 0, secondPre = 1;
		for (int i = 2; i < n; i++) {
			long temp = firstPre;
			firstPre = secondPre;
			secondPre = temp + firstPre;
		}
		return firstPre + secondPre;
	}
	
	// Using ASCII values
	// Return 0 if String s1 contain exactly the same char in same order
	// Return 1 if s1 > s2
	// Return -1 if s1 < s2
	public static int compareStrings(String s1, String s2)
	{
		if (s1.length() == 0 || s2.length() == 0) {					
			if(s1.length() == s2.length())
				return 0;											// base case - s1 = s2 = ""
			return (s2.length() == 0? 1:-1);						// base case - s1 = "" or s2 = ""
			
		} else if((int)s1.charAt(0) != (int)s2.charAt(0)) 
			return ((int)s1.charAt(0) > (int)s2.charAt(0)? 1:-1);	// base case - s1 > s2 or s1 < s2
		
			else
				return compareStrings(s1.substring(1), s2.substring(1));	// recursive call - s1.charAt(0) equal to s2.charAt(0)
	}

	// Test main
	public static void main(String[] args)
	{
		for (int i = 0; i <= 10; i++)
			System.out.println(fibo(i));

		System.out.println("Enterprise vs. defiant" + "\t" + compareStrings("Enterprise", "defiant"));
		System.out.println("enterprise vs. enter" + "\t" + compareStrings("enterprise", "enter"));
		System.out.println("Excelsior vs. \"\"" + "\t" + compareStrings("Excelsior", ""));
		System.out.println("\"\" vs. Excelsior" + "\t" + compareStrings("", "Excelsior"));
		
	}
}