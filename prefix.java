import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: prefix
*/
public class prefix 
{
	static List<String> primitives;
	static StringBuffer sequence;
	static int[] memo;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new FileWriter("prefix.out"));
		
		init(f);
		//System.out.println("Primitives: " + primitives);
		//System.out.println("Sequence: " + sequence);
		
		int prefixLength = getLongestPrefix();
		//System.out.println(prefixLength);
		//print(memo);
		out.println(prefixLength);
		out.close();
	}
	
	private static void print(int[] arr)
	{
		System.out.print("[");
		for (int num : arr)
		{
			System.out.print(num + " ");
		}
		System.out.print("]");
	}

	private static int getLongestPrefix() 
	{
		StringBuffer buff = new StringBuffer(sequence);
		for (int i = sequence.length(); i > 0; i--)
		{
			getLongestPrefix(sequence, i);
		}
		return getLongestPrefix(sequence, 0);
	}
	
	private static int getLongestPrefix(StringBuffer str, int startIndex) 
	{	
		if (memo[startIndex] >= 0)
			return memo[startIndex];
		
		int biggest = 0;
		for (String primitive : primitives)
		{
			int index = str.indexOf(primitive, startIndex);
			if (index == startIndex)
			{
				int subLength = getLongestPrefix(str, startIndex + primitive.length());
				int length = primitive.length() + subLength;
				biggest = (length > biggest) ? length : biggest;
				memo[startIndex] = biggest;
			}
		}
		return biggest;
	}

	private static void init(BufferedReader f) throws Exception
	{
		primitives = new ArrayList<String>();
		String line;
		while (!(line = f.readLine()).equals("."))
		{
			String[] info = line.split(" ");
			for (String primitive : info)
			{
				primitives.add(primitive);
			}
		}
		
		sequence = new StringBuffer();
		while ((line = f.readLine()) != null)
		{
			sequence.append(line);
		}
		
		memo = new int[sequence.length() + 1];
		for (int index = 0; index < memo.length; index++)
		{
			memo[index] = -1;
		}
	}
	

}
