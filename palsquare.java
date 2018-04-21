import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: palsquare
*/
public class palsquare 
{
	private static int convertToBase10(int num, int base)
	{
		int base10num = 0;
		String strNum = String.valueOf(num);
		for (int i = 0; i < strNum.length(); i++)
		{
			int digit = strNum.charAt(i) - '0';
			base10num += digit * Math.pow(base, strNum.length() - 1 - i);
		}
		return base10num;
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
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
	    int base = Integer.parseInt(f.readLine());
		for (int i = 1; i < 301; i++)
		{
			String squareInBase = convertFromBase10(i * i, base);
			if (i == 120)
			{
				
			}
			if (isPalindrome(squareInBase))
			{
				out.print(convertFromBase10(i, base) + " " + convertFromBase10(i * i, base) + "\n");
			} else
			{
				//continue
			}
		}
		out.close();
	}
}
