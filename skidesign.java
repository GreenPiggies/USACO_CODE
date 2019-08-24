import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: skidesign
*/
public class skidesign 
{

	public static int calculatePrice(List<Integer> heights, int min, int max)
	{
		int moneyCount = 0;
		for (Integer height : heights)
		{
			if (height < min)
			{
				moneyCount += (min - height) * (min - height);
			} else if (height > max)
			{
				moneyCount += (height - max) * (height - max);
			}
		}
		return moneyCount;
	}
	
	public static int min(List<Integer> list)
	{
		int min = 101;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) < min)
			{
				min = list.get(i);
			}
		}
		return min;
	}
	
	public static int max(List<Integer> list)
	{
		int max = -1;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) > max)
			{
				max = list.get(i);
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
	    
	    // Read in the number of hills
	    int numHills = Integer.parseInt(f.readLine());
	    
	    // Initialize the heights
		List<Integer> heights = new ArrayList<Integer>();
		heights = readHills(f, numHills);
		//Making an initial copy 
		List<Integer> originalHeights = new ArrayList<Integer>();
		for (Integer num : heights)
		{
			originalHeights.add(num);
		}
				
		// Heights are read correctly.
		
		/**
		 * New Method: 
		 * Try one by one and see if it works lmao
		 */
				
		int min = min(heights);
		int max = max(heights);
		
		int lowestPrice = Integer.MAX_VALUE;
		
		if (max - min <= 17)
		{
			out.println(0);
		} else
		{
			for (int i = min; i <= max; i++)
			{
				int currPrice = calculatePrice(heights, i, i + 17);
				if (currPrice < lowestPrice)
				{
					lowestPrice = currPrice;
				}
			}
			out.println(lowestPrice);
		}
		out.close();
		
		
		//Find the min, max
		/*List<List<Integer>> info = findMinMax(heights);
		
		List<Integer> minIndices = info.get(0);
		List<Integer> maxIndices = info.get(1);			
		
		int min = heights.get(minIndices.get(0));
		int max = heights.get(maxIndices.get(0));
		
		int counter = 0;
		
		while (max - min > 17)
		{			
			incrementList(heights, minIndices, maxIndices, counter);
			info = findMinMax(heights);
			
			minIndices = info.get(0);
			maxIndices = info.get(1);			
			
			min = heights.get(minIndices.get(0));
			max = heights.get(maxIndices.get(0));
			counter++;
		}
		
		System.out.println(heights);
		
		int sum =  0;
		for (int i = 0; i < heights.size(); i++)
		{
			sum += Math.pow(heights.get(i) - originalHeights.get(i), 2);
		}
		
		out.println(sum);
		out.close();
		*/
		
	}
	
	private static List<Integer> readHills(BufferedReader f, int numHills) throws IOException
	{
		List<Integer> hills = new ArrayList<>();
		for (int i = 0; i < numHills; i++)
		{
			hills.add(Integer.parseInt(f.readLine()));
		}
		return hills;
	}

	private static void incrementList(List<Integer> heights, List<Integer> minIndices, List<Integer> maxIndices, int counter) 
	{
		if (counter % 2 == 0)
		{
			for (Integer index : maxIndices)
			{
				heights.set(index, heights.get(index) - 1);
			}
		} else
		{
			for (Integer index : minIndices)
			{
				heights.set(index, heights.get(index) + 1);
			}
		}
	}

	private static List<List<Integer>> findMinMax(List<Integer> heights) 
	{
		List<List<Integer>> info = new ArrayList<List<Integer>>();
		List<Integer> minIndex = new ArrayList<>();
		List<Integer> maxIndex = new ArrayList<>();
		
		int min = 101;
		int max = -1;
		for (int i = 0; i < heights.size(); i++)
		{
			int curr = heights.get(i);
			if (curr <= min)
			{
				if (curr < min)
				{
					minIndex = new ArrayList<>();
					min = curr;
				}
				minIndex.add(i);
			}
			
			if (curr >= max)
			{
				if (curr > max)
				{
					maxIndex = new ArrayList<>();
					max = curr;
				}
				maxIndex.add(i);
			}
		}
		
		info.add(minIndex);
		info.add(maxIndex);
		return info;

	}

}
