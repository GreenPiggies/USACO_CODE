import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: ariprog
*/
public class ariprog 
{
	static long startTime = 0;
	static class Pair implements Comparable
	{
		int a;
		int b;
		
		public Pair(int x, int y)
		{
			a = x;
			b = y;
		}

		public int compareTo(Object arg)
		{
			Pair other = (Pair) arg;
			if (this.b < other.b)
			{
				return -1;
			} else if (this.b == other.b)
			{
				if (this.a < other.a)
				{
					return -1;
				} else
				{
					return 1;
				}
			} else
			{
				return 1;
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int progLength = Integer.parseInt(f.readLine());
		int bisquareSize = Integer.parseInt(f.readLine());
		printTime();
		Set<Integer> bisquareSet = createBisquares(bisquareSize);
		printTime();
		System.out.println("size=" + bisquareSet.size());
				
		List<Pair> arithmeticProgList = findArithmeticProgs(progLength, bisquareSet, bisquareSize);
		
		if (arithmeticProgList.isEmpty())
		{
			out.println("NONE");
		}
		
		for (Pair arithmeticProg : arithmeticProgList)
		{
			out.println(arithmeticProg.a + " " + arithmeticProg.b);
		}
		
		out.close();
		
		
	}
	private static void printTime() {
		if (startTime == 0) {
			startTime = System.currentTimeMillis();
			System.out.println(0);
		} else 
			System.out.println((System.currentTimeMillis() - startTime) + "ms");
	}

	private static List<Pair> findArithmeticProgs(int progLength, Set<Integer> bisquareSet, int bisquareSize) 
	{
		int biggestNum = 2 * bisquareSize * bisquareSize;
		List<Pair> arithmeticProgsList = new ArrayList<Pair>();
		boolean[] bisquareNums = new boolean[biggestNum+1];
		bisquareSet.forEach((i) -> { bisquareNums[i] = true; });
		
		bisquareSet.forEach((i) ->
		{
			// System.out.print("i:" + i + " "); printTime(); 
			int maxJ = (biggestNum - i) / (progLength - 1);
			for (int j = 1; j <= maxJ; j++)
			{
				boolean isBisquare = true;
				for (int k = 0; k < progLength; k++)
				// for (int k = progLength - 1; k >= 0; k--)
				{
					// if (!bisquareSet.contains(i + j * k))
					if (!bisquareNums[i+j*k])
					{
						isBisquare = false;
						break;
					}
				}
				if (isBisquare)
				{
					Pair arithmeticProg = new Pair(i, j);
					arithmeticProgsList.add(arithmeticProg);
				}
			}
		});
		printTime();
		arithmeticProgsList.sort(null);
		return arithmeticProgsList;
	}

	private static Set<Integer> createBisquares(int bisquareSize) 
	{
		Set<Integer> bisquareSet = new HashSet<>(30000);
		for (int i = 0; i <= bisquareSize; i++)
		{
			for (int j = 0; j <= bisquareSize; j++)
			{
				bisquareSet.add(i * i + j * j); //bisquare
			}
		}
		return bisquareSet;
	}

}
