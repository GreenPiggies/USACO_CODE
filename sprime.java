import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: sprime
*/
public class sprime 
{
	static int sprimeLength = 0;
	
	static Set<Integer> singlePrimes = null;
	
	static List<Integer> nums = null;
	
	static Set<Integer> odds = null;
	public static void main(String[] args) throws Exception 
	{
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
	    
	    nums = new ArrayList<Integer>();
	    
	    singlePrimes = new HashSet<Integer>();
	    
	    odds = new HashSet<Integer>();
	    
	    odds.add(1);
	    odds.add(3);
	    odds.add(5);
	    odds.add(7);
	    odds.add(9);
	    
	    singlePrimes.add(2);
	    singlePrimes.add(3);
	    singlePrimes.add(5);
	    singlePrimes.add(7);
	    
	    
	    
	    sprimeLength = Integer.parseInt(f.readLine());
	    
	    
	    List<Integer> permutations = getSuperPrimes();
	    	    
	    for (Integer perm : permutations)
	    {
	    	out.println(perm);
	    }
	    out.close();
	}
	
	private static List<Integer> getSuperPrimes() 
	{
		//Set<Integer> superPrimes = new HashSet<Integer>();
		
		nums.addAll(singlePrimes);
		int counter = 1;
		while (counter < sprimeLength)
		{
			List<Integer> newNums = new ArrayList<Integer>();
			for (Integer num : nums)
			{
				for (int odd : odds)
				{
					int newInt = num * 10 + odd;
					if (isPrime(newInt))
					{
						newNums.add(newInt);
					}
				}
			}
			nums = newNums;
			counter++;
		}
		
		return nums;
		
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
