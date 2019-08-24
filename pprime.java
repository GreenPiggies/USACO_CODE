import java.io.*;
import java.util.*;

/*
ID: hungwes1
LANG: JAVA
TASK: pprime
*/
public class pprime 
{
	static int min = 0;
	static int max = 0;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
	    String[] info = f.readLine().split(" ");
	    min = Integer.parseInt(info[0]);
	    max = Integer.parseInt(info[1]);
	    
	    //int[] palindromes = generatePalidrones(min, max);
	    
	    List<Integer> palindromes = allPalindromic(min, max);
	    System.out.println(palindromes);
	    
	    List<Integer> primePalindromes = getPrimes(palindromes);
	    
	    System.out.println(primePalindromes);
	    
	    for (Integer pprime : primePalindromes)
	    {
	    	out.println(pprime);
	    }
	    if (primePalindromes.isEmpty())
	    {
	    	out.println(-1);
	    }
	    out.close();
	    
	    
	}

	public static List<Integer> allPalindromic(int lowerLimit, int upperLimit) 
	{

	    List<Integer> result = new ArrayList<Integer>();

	    for (int i = lowerLimit; i <= 9 && i <= upperLimit; i++)
	    {
	        result.add(i);
	    }
	    
	    boolean cont = true;
	    String lowerLimitStr = Integer.toString(lowerLimit);
	    String upperLimitStr = Integer.toString(upperLimit);
	    int lowerSegmentLength = (lowerLimitStr.length() - 1) / 2;
	    int upperSegmentLength = (upperLimitStr.length() / 2);
	    String lowerSeg = lowerLimitStr.substring(0, lowerSegmentLength);
	    String upperSeg = upperLimitStr.substring(0, upperSegmentLength);
	    
	    System.out.println("Lower seg: " + lowerSeg);
	    System.out.println("Upper seg: " + upperSeg);
	    int lowerLimitHalf = -1;
	    if (lowerSeg.equals(""))
	    {
	    	lowerLimitHalf = 0;
	    } else
	    {
	    	lowerLimitHalf = Integer.parseInt(lowerSeg);
	    }
	    int upperLimitHalf = -1;
	    if (upperSeg.equals(""))
	    {
	    	upperLimitHalf = 9;
	    } else
	    {
	    	upperLimitHalf = Integer.parseInt(upperSeg);
	    }
	    
	   
	    
	    // System.out.println(lowerLimitHalf);
	    	    
	    for (int i = lowerLimitHalf; i <= upperLimitHalf; i++) 
	    {
	    	System.out.println(i);
	        StringBuffer rev = new StringBuffer("" + i).reverse();
	        String[] ds = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	        for (String d : ds) 
	        {
	            int n = Integer.parseInt("" + i + d + rev);
	           
	            //System.out.println("Number: " + n);
	            if (n <= upperLimit && n >= lowerLimit) 
	            {
	            	//System.out.println("Added to result");
	                result.add(n);
	            }
	        }
	    }

	    return result;
	}

	private static List<Integer> getPrimes(List<Integer> palindromes) 
	{
		List<Integer> primes = new ArrayList<>();
		for (int palindrome : palindromes)
		{
			if (isPrime(palindrome))
			{
				primes.add(palindrome);
			}
		}
		return primes;
	}

	private static boolean isPrime(int n) 
	{
		if (n <= 1)
		{
			return false;
		} else if (n <= 3)
		{
			return true;
		} else if (n % 2 == 0 || n % 3 == 0)
		{
			return false;
		}
		int i = 5;
		while (i * i <= n)
		{
			if (n % i == 0 || n % (i + 2) == 0)
			{
				return false;
			}
			i += 6;
		}
		return true;
	}
}
