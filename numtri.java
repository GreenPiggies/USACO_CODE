import java.io.*;
import java.util.*;

/*
ID: hungwes1
LANG: JAVA
TASK: numtri
*/
public class numtri 
{
	
	static int[] values = null;
	
	static int[] sums = null;
	
	static int size = 0;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
	    
	    size = Integer.parseInt(f.readLine());
	    
	    getValues(f);
	    
	    out.println(sums());
	    out.close();
	    	    
	}

	private static int getIndex(int i, int j)
	{
		if (j < 0 || j > i)
		{
			return -1;
		}
		return (i * (i + 1) / 2) + j;
	}
	
	private static int max(int a, int b)
	{
		if (a > b)
		{
			return a;
		} else
		{
			return b;
		}
	}
	
	private static int sums()
	{
		sums = new int[values.length];
		
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j <= i; j++)
			{
				int currIndex = getIndex(i, j);
				int leftParentIndex = getIndex(i - 1, j - 1);
				int rightParentIndex = getIndex(i - 1, j);
				int prev = 0;
				if (leftParentIndex == -1 && rightParentIndex == -1)
				{
					prev = 0;
				} else if (rightParentIndex == -1)
				{
					prev = sums[leftParentIndex];
				} else if (leftParentIndex == -1)
				{
					prev = sums[rightParentIndex];
				} else
				{
					prev = max(sums[leftParentIndex], sums[rightParentIndex]);
				}
				sums[currIndex] = prev + values[currIndex];
			}
		}
		int biggest = sums[sums.length - 1];
		for (int i = sums.length - 2; i > sums.length - size; i--)
		{
			if (sums[i] > biggest)
			{
				biggest = sums[i];
			}
		}
		return biggest;
	}
	
	public static int max(int[] arr)
	{
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i] > max)
			{
				max = arr[i];
			}
		}
		return max;
	}
	
	private static int sum(int index)
	{
		if (index == 0)
		{
			return values[0];
		} else
		{
			return sum(index - 1) + values[index];
		}
	}

	private static void getValues(BufferedReader f) throws Exception
	{
		values = new int[(size * (size + 1)) / 2];
		int counter = 0;
		for (int i = 0; i < size; i++)
		{
			String[] info = f.readLine().split(" ");
			for (int j = 0; j < info.length; j++)
			{
				int num = Integer.parseInt(info[j]);
				values[counter] = num;
				counter++;
			}
		}
	}

}
