import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: money
*/
public class money 
{
	static int numCoins;
	static int value;
	static int[] coins;
	
	static long[] cost;
	
	public static void main(String[] args) throws Exception
	{
		String prob = "money";
		Scanner f = new Scanner(new FileReader(prob + ".in"));
		PrintWriter out = new PrintWriter(new FileWriter(prob + ".out"));
		
		init(f);
		
		//int ways = ways(0, value);
		ways();
		
		out.print(Long.toString(cost[value]) + "\n");
		out.close();
		
	}

	private static int ways(int index, int value) 
	{
		if (index >= numCoins) return 0;
		if (value < 0) return 0;
		if (value == 0) return 1;
		if (coins[index] > value) return 0;
		return ways(index + 1, value) + ways(index, value - coins[index]);
	}
	
	private static void ways()
	{
		cost = new long[value + 1];
        cost[0] = 1L;

        for (int i = 0; i < numCoins; i++) 
        {
            int m = coins[i];
            for (int j = m; j <= value; j++) 
            {
                cost[j] += cost[j - m];
            }
        }

	}
	
	

	private static void init(Scanner f) throws Exception
	{
		numCoins = f.nextInt();
		value = f.nextInt();
		
		coins = new int[numCoins];
		
		for (int i = 0; i < numCoins; i++)
		{
			coins[i] = f.nextInt();
		}
	}

}
