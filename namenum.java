import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: namenum
*/
public class namenum 
{
	
	public static void main(String[] args) throws Exception
	{
		long time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
	    BufferedReader buff = new BufferedReader(new FileReader("dict.txt"));
	    
	    Map<Long, LinkedHashSet<String>> dict = new HashMap<Long, LinkedHashSet<String>>();
	    
	    for (int i = 0; i < 4617; i++)
	    {
	    	String name = buff.readLine();
	    	long nameDigit = convert(name);
    		LinkedHashSet<String> currSet;
	    	if (dict.containsKey(nameDigit))
	    	{
	    		currSet = dict.get(nameDigit);
	    		currSet.add(name);
	    	} else
	    	{
	    		currSet = new LinkedHashSet<String>();
	    		currSet.add(name);
	    	}
    		dict.put(nameDigit, currSet);
	    }
	    long input = Long.parseLong(f.readLine());
	    if (dict.containsKey(input))
	    {
	    	for (String name : dict.get(input))
	    	{
	    		out.println(name);
	    	}
	    } else
	    {
	    	out.println("NONE");
	    }
	    
	    
	    out.close();
	}

	private static long convert(String name)
	{
		String str = "";
		for (char c : name.toCharArray())
		{
			switch (c) 
	    	{
				case 'A':
				case 'B':
				case 'C':
					str += "2";
					break;
				case 'D':
				case 'E':
				case 'F':
					str += "3";
					break; 
				case 'G':
				case 'H':
				case 'I':
					str += "4";
					break;
				case 'J':
				case 'K':
				case 'L':
					str += "5";
					break;
				case 'M':
				case 'N':
				case 'O':
					str += "6";
					break;
				case 'P':
				case 'R':
				case 'S':
					str += "7";
					break;
				case 'T':
				case 'U':
				case 'V':
					str += "8";
					break;
				case 'W':
				case 'X':
				case 'Y':
					str += "9";
					break;
				default:
					break;		
	    	}
		}
		return Long.parseLong(str);
	}
	
	

}
