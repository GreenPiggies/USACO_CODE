/*
ID: greenpig
LANG: JAVA
TASK: milkorder
*/
import java.io.*;
import java.util.*;

public class milkorder 
{
	static int nCows;
	static int nOrdered;
	static int nFixed;
	
	static int[] hList;
	static int[] pList;
	//static boolean[] hInP;
	static int[] pListIndices;
	static int[] cowList;
	public static void main(String[] args) throws Exception
	{
		String problem = "milkorder";
		Scanner in = new Scanner(new FileReader(problem + ".in"));
		PrintWriter out = new PrintWriter(new FileWriter(problem + ".out"));
		
		init(in);
		System.out.print("[");
		for (int i = 0; i < nCows - 1; i++)
		{
			System.out.print(cowList[i] + " ");
		}
		System.out.print(cowList[nCows - 1] + "]");
		
		out.println(solve());
		System.out.print("[");
		for (int i = 0; i < nCows - 1; i++)
		{
			System.out.print(cowList[i] + " ");
		}
		System.out.print(cowList[nCows - 1] + "]");
		
		out.close();
	}
	
	private static int solve() 
	{
		// First fill in the ordered cows.
		
		System.out.print("[");
		for (int i = 0; i < nOrdered - 1; i++)
		{
			System.out.print(hList[i] + " ");
		}
		System.out.print(hList[nOrdered - 1] + "]");
		
		
		if (contains(hList, 1))
		{
			System.out.println("hi");
			int prevIndex = 0;
			for (int i = 0; i < nOrdered; i++)
			{
				int curr = hList[i];
				if (contains(pList, curr))
				{
					prevIndex = pListIndices[curr] + 1;
				} else
				{
					for (int j = prevIndex; j < nCows; j++)
					{
						if (cowList[j] == 0)
						{
							cowList[j] = curr;
							break;
						}
					}
				}
			}
			for (int i = 0; i < nCows; i++)
			{
				if (cowList[i] == 1)
				{
					return i + 1;
				}
			}
		} else
		{
			int prevIndex = nCows - 1;
			for (int i = nOrdered - 1; i >= 0; i--)
			{
				int curr = hList[i];
				if (contains(pList, curr))
				{
					prevIndex = pListIndices[curr] - 1;
				} else
				{
					for (int j = prevIndex; j >= 0; j--)
					{
						if (cowList[j] == 0)
						{
							cowList[j] = curr;
							break;
						}
					}
				}
			}
			for (int i = 0; i < nCows; i++)
			{
				if (cowList[i] == 0)
				{
					return i + 1;
				}
			}
		}
		
		
		// Should never get here.
		return -1;
	}

	private static boolean contains(int[] pList2, int hCow) 
	{
		for (int i = 0; i < pList2.length; i++)
		{
			System.out.println(pList2[i] + ", " + hCow);
			if (pList2[i] == hCow)
			{
				return true;
			}
		}
		return false;
	}

	private static void init(Scanner in) 
	{
		nCows = in.nextInt();
		nOrdered = in.nextInt();
		nFixed = in.nextInt();
		in.nextLine();
		
		cowList = new int[100];
		hList = new int[100];
		for (int i = 0; i < nOrdered; i++)
		{
			hList[i] = in.nextInt();
			System.out.println("xd " + hList[i]);
		}
		in.nextLine();
		
		pList = new int[100];
		pListIndices = new int[100];
		for (int i = 0; i < nFixed; i++)
		{
			int value = in.nextInt();
			int index = in.nextInt();
			pList[i] = value;
			pListIndices[pList[i]] = index - 1; 
			System.out.println(index - 1);
			cowList[index - 1] = value;
		}		
		
		
		
	}
}