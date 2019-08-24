import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: nocows
*/
public class nocows 
{
	static int numNodes;
	static int levels;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new FileWriter("nocows.out"));
		
		init(f);
		
		int combos = solve(numNodes, levels);
		System.out.println(combos);
		out.println(combos);
		out.close();
	}
	
	
	
	private static void init(BufferedReader f) throws Exception
	{
		String[] info = f.readLine().split(" ");
		numNodes = Integer.parseInt(info[0]);
		levels = Integer.parseInt(info[1]);
		arr = new int[numNodes + 1][levels + 1];
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[i].length; j++)
			{
				arr[i][j] = -1;
			}
		}
	}
	
	public static int solve(int n, int k)
	{
		if (n % 2 == 0) return 0; // No evens
		if (2 * k - 1 > n) return 0; // Math
		if (k == 1) return n == 1 ? 1 : 0; // 1 level
		
		if (arr[n][k] != -1) return arr[n][k];
		arr[n][k] = 0;
		for (int i = 1; i < (n + 1) / 2; i+= 2) // i is always gonna be odd, also symmetry
		{
			int left = i;
			int right = n - i - 1;
			int solution = 0;
			
			for (int j = 1; j + 1 < k; j++) // Filling up the array
			{
				solution += solve(left, j) * solve(right, k - 1);
				solution += solve(left, k - 1) * solve(right, j);
				solution %= 9901;
			}
			
			solution += solve(left, k - 1) * solve (right, k - 1); // Actually calling the thing
			
			solution %= 9901;
			
			if (left != right) // Adding the solutions
			{
				arr[n][k] += 2 * solution;
			} else
			{
				arr[n][k] += solution;
			}
			arr[n][k] %= 9901;
		}
		return arr[n][k];
	}

}
