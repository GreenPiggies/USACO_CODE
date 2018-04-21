import java.util.*;
import java.io.*;
/*
ID: hungwes1
LANG: JAVA
TASK: crypt1
*/
public class crypt1 
{

	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
	    
	    int numDigits = Integer.parseInt(f.readLine());
	    List<Integer> digits = readDigits(f);
	    
	    List<List<Integer>> permutations = getPermutations(digits);
	    
	    int validPermutations = getValidPermutations(digits, permutations);
	    
	    out.println(validPermutations);
	    out.close();
	    
	}

	private static int getValidPermutations(List<Integer> digits, List<List<Integer>> permutations) 
	{
		int numValidPerm = 0;
		for (List<Integer> permutation : permutations)
		{
			int a = permutation.get(0);
			int b = permutation.get(1);
			int c = permutation.get(2);
			int topRow = a * 100 + b * 10 + c;
			int d = permutation.get(3); 
			int e = permutation.get(4); 
			if (isValidMultiplication(digits, topRow, d, true) 
				&& isValidMultiplication(digits, topRow, e, true) 
				&& isValidMultiplication(digits, topRow, d * 10 + e, false))
			{
				numValidPerm++;
			}
		}
		return numValidPerm;
	}

	
	
	private static boolean isValidMultiplication(List<Integer> permutation, int topRow, int multiplier, boolean isPartial) 
	{
		int product = topRow * multiplier;
		if (isPartial && product > 999)
		{
			return false;
		}
		List<Integer> digits = getDigits(product);
		for (Integer digit : digits)
		{
			if (!permutation.contains(digit))
			{
				return false;
			}
		}
		return true;
	}

	private static List<Integer> getDigits(int product) 
	{
		List<Integer> digits = new ArrayList<>();
		while (product > 0) 
		{
		    digits.add(product % 10);
		    product = product / 10;
		}
		return digits;

	}

	private static List<List<Integer>> getPermutations(List<Integer> digits) 
	{
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		for (int a = 0; a < digits.size(); a++)
		{
			for (int b = 0; b < digits.size(); b++)
			{
				for (int c = 0; c < digits.size(); c++)
				{
					for (int d = 0; d < digits.size(); d++)
					{
						for (int e = 0; e < digits.size(); e++)
						{
							List<Integer> permutation = new ArrayList<>();
							permutation.add(digits.get(a));
							permutation.add(digits.get(b));
							permutation.add(digits.get(c));
							permutation.add(digits.get(d));
							permutation.add(digits.get(e));
							permutations.add(permutation);
						}
					}
				}
			}
		}
		return permutations;
	}

	private static List<Integer> readDigits(BufferedReader f) throws Exception
	{
		List<Integer> digits = new ArrayList<>();
		String[] digitStr = f.readLine().split(" ");
		for (String digit : digitStr)
		{
			digits.add(Integer.parseInt(digit));
		}
		return digits;
	}
	

}
