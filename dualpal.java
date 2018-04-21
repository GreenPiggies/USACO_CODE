import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
/*
ID: hungwes1
LANG: JAVA
TASK: dualpal
*/
public class dualpal {

	private static boolean isPalindrome(String num)
	{
		boolean equal = true;
		int left = 0;
		int right = num.length() - 1;
		while (left <= right && equal)
		{
			if (num.charAt(left) != num.charAt(right))
			{
				equal = false;
			} else
			{
				left++;
				right--;
			}
		}
		return equal;
	}
	
	private static String convertFromBase10(int num, int base)
	{
		int quotient = num;
		String numStr = "";
		while (quotient >= base)
		{
			char c = Character.forDigit(quotient % base, base);
			numStr = Character.toUpperCase(c) + numStr;
			quotient = quotient / base;
		}
		numStr = Character.toUpperCase(Character.forDigit(quotient, base)) +numStr;
		
		return numStr;
		
	}
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
	    
	    String[] data = f.readLine().split(" ");
	    int numResults = Integer.parseInt(data[0]);
	    int startNum = Integer.parseInt(data[1]);
	    
	    int currResults = 0;
	    int currNum = startNum + 1;
	    while (currResults < numResults)
	    {
	    	int numPalindromes = 0;
	    	for (int i = 2; i < 11; i++)
	    	{
	    		if (isPalindrome(convertFromBase10(currNum, i)))
	    		{
	    			numPalindromes++;
	    		}
	    	}
	    	if (numPalindromes >= 2)
	    	{
	    		out.println(currNum);
	    		currResults++;

	    	}
	    	currNum++;
	    }
	    out.close();
	    
	}

}
