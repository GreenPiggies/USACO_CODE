import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: palsquare
*/
public class palsquare 
{
	private static int convertFromBase(int num, int base)
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
	
	private static String convertToBase(int num, int base)
	{
		int quotient = num;
		String numStr = "";
		while (quotient > base)
		{
			char c = Character.forDigit(quotient % base, base);
			numStr = c + numStr;
			System.out.println(numStr);
			quotient = quotient / base;
		}
		numStr = quotient + numStr;
		
		return numStr;
		
	}
	
	private static int isSquarePalindrome(int square)
	{
		
	}
	public static void main(String[] args) 
	{
		//System.out.println(convertFromBase(555, 19));
		
		System.out.println(convertToBase(1000, 19));
	}
}
