import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: preface
*/
public class preface 
{
	static int size;
	static int i = 0;
	static int v = 0;
	static int x = 0;
	static int l = 0;
	static int c = 0;
	static int d = 0;
	static int m = 0;
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
	    
	    size = Integer.parseInt(f.readLine());
	    for (int number = 1; number <= size; number++)
	    {
	    	String romanNumeral = toRomanNumeral(number);
	    	processRomanNumeral(romanNumeral);
	    }
	    if (i != 0)
		    out.println("I " + i);
	    if (v != 0)
		    out.println("V " + v);
	    if (x != 0)
	    	out.println("X " + x);
	    if (l != 0)
	    	out.println("L " + l);
	    if (c != 0)
	    	out.println("C " + c);
	    if (d != 0)
	    	out.println("D " + d);
	    if (m != 0)
	    	out.println("M " + m);
	    out.close();
	    
	}
	
	private static String toRomanNumeral(int input) 
	{
		if (input < 1 || input > 3999)
	        return "Invalid Roman Number Value";
	    String s = "";
	    while (input >= 1000) 
	    {
	        s += "M";
	        input -= 1000;        
	       }
	    while (input >= 900) 
	    {
	        s += "CM";
	        input -= 900;
	    }
	    while (input >= 500) 
	    {
	        s += "D";
	        input -= 500;
	    }
	    while (input >= 400) 
	    {
	        s += "CD";
	        input -= 400;
	    }
	    while (input >= 100) 
	    {
	        s += "C";
	        input -= 100;
	    }
	    while (input >= 90) 
	    {
	        s += "XC";
	        input -= 90;
	    }
	    while (input >= 50) 
	    {
	        s += "L";
	        input -= 50;
	    }
	    while (input >= 40) 
	    {
	        s += "XL";
	        input -= 40;
	    }
	    while (input >= 10) 
	    {
	        s += "X";
	        input -= 10;
	    }
	    while (input >= 9) 
	    {
	        s += "IX";
	        input -= 9;
	    }
	    while (input >= 5) 
	    {
	        s += "V";
	        input -= 5;
	    }
	    while (input >= 4) 
	    {
	        s += "IV";
	        input -= 4;
	    }
	    while (input >= 1) 
	    {
	        s += "I";
	        input -= 1;
	    }    
	    return s;
	}
	
	private static void processRomanNumeral(String romanNumeral) 
	{
		for (int index = 0; index < romanNumeral.length(); index++)
		{
			String letter = romanNumeral.substring(index, index + 1);
			if (letter.equals("I"))
			{
				i++;
			} else if (letter.equals("V"))
			{
				v++;
			} else if (letter.equals("X"))
			{
				x++;
			} else if (letter.equals("L"))
			{
				l++;
			} else if (letter.equals("C"))
			{
				c++;
			} else if (letter.equals("D"))
			{
				d++;
			} else if (letter.equals("M"))
			{
				m++;
			}	
		}
	}

}
