import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: runround
*/
public class runround 
{
	static String low;

	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new FileWriter("runround.out"));
		
		low = f.readLine();
		
		long runaround = getNextRunaround();
		
		out.println(runaround);
		out.close();

	}

	private static long getNextRunaround() 
	{
		boolean hasNextRunaround = false;
		long num = Long.parseLong(low);
		while (!hasNextRunaround) // A different number each time
		{
			num++;
			//System.out.println(num);
			String strNum = Long.toString(num);
			hasNextRunaround = isRunaround(strNum);
			
		}
		return num;
		
	}
	
	private static boolean isRunaround(String strNum)
	{
		if (strNum.contains("0") || strNum.length() == 1 || containsDuplicates(strNum))
		{
			return false;
		} else 
		{
			Set<Integer> visited = new HashSet<Integer>();
			int currIndex = 0;
			int curr = Integer.parseInt(strNum.substring(currIndex, currIndex + 1));
			//System.out.println("Length: " + strNum.length());
			while (!visited.contains(curr))
			{
				visited.add(curr);
				currIndex = (currIndex + curr) % strNum.length();
				curr = Integer.parseInt(strNum.substring(currIndex, currIndex + 1));
				//System.out.println("currIndex: " + currIndex);
				//System.out.println("curr: " + curr);
				//System.out.println(visited);
			}
			if (visited.size() == strNum.length() && curr == Integer.parseInt(strNum.substring(0, 1)))
			{
				return true;
			}
		}
		return false;
	}

	private static boolean containsDuplicates(String strNum) 
	{
		Set<Character> numbers = new HashSet<>();
		char[] arr = strNum.toCharArray();
		for (int i = 0; i < arr.length; i++)
		{
			if (numbers.contains(arr[i]))
			{
				return true;
			} else
			{
				numbers.add(arr[i]);
			}
		}
		return false;
	}

	

}
