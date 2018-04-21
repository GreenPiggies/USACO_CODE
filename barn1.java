import java.util.*;
import java.io.*;

/*
ID: hungwes1
LANG: JAVA
TASK: barn1
*/
public class barn1 
{	
	private static int smallestNum(ArrayList<Integer> nums)
	{
		int smallestNum = 201;
		for (int num : nums)
		{
			if (num < smallestNum)
			{
				smallestNum = num;
			}
		}
		if (smallestNum == 201)
		{
			return -1;
		}
		return smallestNum;
	}
	
	public static void main(String[] args) throws Exception
	{
		// Reading
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
	    
	    String[] barnInfo = f.readLine().split(" ");
	    int maxBoard = Integer.parseInt(barnInfo[0]);
	    int stallNum = Integer.parseInt(barnInfo[1]);
	    int numCows = Integer.parseInt(barnInfo[2]);
	    
	    List<Integer> cows = readCowsInStalls(f, numCows);
	    List<Integer> gaps = findAllGaps(cows);
	    gaps.sort(null);

	    int firstCowPosition = cows.get(0);
	    int lastCowPosition = cows.get(cows.size() - 1);
	    
	    int minimumLength = lastCowPosition - firstCowPosition - topNGaps(gaps, maxBoard - 1) + 1;
	    
	    out.println(minimumLength);
	    out.close();
	    
	    
	}

	private static int topNGaps(List<Integer> gaps, int n) 
	{
		int totalGap = 0;
		int m = (n >= gaps.size()) ? gaps.size() : n;
		for (int i = 0; i < m; i++)
		{
			totalGap += gaps.get(gaps.size() - (i + 1));
		}
		return totalGap;
	}

	private static List<Integer> findAllGaps(List<Integer> cows) 
	{
		List<Integer> gaps = new ArrayList<>();
		for (int i = 1; i < cows.size(); i++)
		{
			int gap = cows.get(i) - cows.get(i - 1) - 1;
			if (gap > 0)
			{
				gaps.add(gap);
			}
		}
		gaps.sort(null);
		return gaps;
	}

	private static List<Integer> readCowsInStalls(BufferedReader f, int numCows) throws Exception
	{
		List<Integer> cows = new ArrayList<>(); 
		for (int i = 0; i < numCows; i++)
		{
			int cowStall = Integer.parseInt(f.readLine()) - 1;
			cows.add(cowStall);
		}
		cows.sort(null);
		return cows;
	}
}
