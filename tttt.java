/*
ID: greenpig
LANG: JAVA
TASK: tttt
*/

import java.io.*;
import java.util.*;

public class tttt  
{
	static char[][] board;
	static Set<Character> singleWinners;
	static Set<Set<Character>> doubleWinners;
	public static void main(String[] args) throws Exception
	{
		String problem = "tttt";
		Scanner in = new Scanner(new FileReader(problem + ".in"));
		PrintWriter out = new PrintWriter(new FileWriter(problem + ".out"));
		
		init(in);
		
		solve();
		
		System.out.println(singleWinners);
		System.out.println(doubleWinners);
		out.println(singleWinners.size());
		out.println(doubleWinners.size());
		
		out.close();
	}
	
	private static void solve()
	{
		computeSingleWinners();
		computeDoubleWinners();
	}
	
	private static void computeRows(int numCows)
	{
		for (int i = 0; i < 3; i++)
		{
			Set<Character> cows = new HashSet<Character>();
			for (int j = 0; j < 3; j++)
			{
				char curr = board[i][j];
				cows.add(curr);
			}
			if (cows.size() == numCows)
			{
				if (numCows == 1)
				{
					singleWinners.addAll(cows);
				} else if (numCows == 2)
				{
					doubleWinners.add(cows);
				}
			}
		}
	}
	
	private static void computeColumns(int numCows)
	{
		for (int j = 0; j < 3; j++)
		{
			Set<Character> cows = new HashSet<Character>();
			for (int i = 0; i < 3; i++)
			{
				char curr = board[i][j];
				cows.add(curr);
			}
			if (cows.size() == numCows)
			{
				if (numCows == 1)
				{
					singleWinners.addAll(cows);
				} else if (numCows == 2)
				{
					doubleWinners.add(cows);
				}
			}
		}
	}
	
	private static void computeDiagonals(int numCows)
	{
		Set<Character> diag1 = new HashSet<>();
		Set<Character> diag2 = new HashSet<>();
		for (int i = 0; i < 3; i++)
		{
			diag1.add(board[i][i]);
			diag2.add(board[i][2-i]);
		}
		if (diag1.size() == numCows)
		{
			if (numCows == 1)
			{
				singleWinners.addAll(diag1);
			} else if (numCows == 2)
			{
				doubleWinners.add(diag1);
			}
		}
		if (diag2.size() == numCows)
		{
			if (numCows == 1)
			{
				singleWinners.addAll(diag2);
			} else if (numCows == 2)
			{
				doubleWinners.add(diag2);
			}
		}
	}
	
	private static void computeSingleWinners()
	{
		computeRows(1);
		computeColumns(1);
		computeDiagonals(1);
	}
	
	private static void computeDoubleWinners()
	{
		computeRows(2);
		computeColumns(2);
		computeDiagonals(2);
	}

	private static void init(Scanner in) 
	{
		singleWinners = new HashSet<>();
		doubleWinners = new HashSet<>();
		board = new char[3][3];
		for (int i = 0; i < 3; i++)
		{
			char[] row = in.nextLine().toCharArray();
			board[i] = row;
		}
	}
	
	
}
