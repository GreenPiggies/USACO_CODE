import java.io.*;
import java.util.*;
/*
ID: greenpig
LANG: JAVA
TASK: family
*/
public class family 
{
	
	static int numLines;
	static String a;
	static String b;
	static Map<String, String> map;
	

	public static void main(String[] args) throws Exception
	{
		String problem = "family";
		Scanner in = new Scanner(new FileReader(problem + ".in"));
		PrintWriter out = new PrintWriter(new FileWriter(problem + ".out"));
		
		init(in);
		
		out.println(solve());

	}

	private static String solve() 
	{
		if (map.get(a).equals(map.get(b)))
			return "SIBLINGS";
		List<String> aAncestors = getAncestors(a);
		List<String> bAncestors = getAncestors(b);
		int[] aDistance = new int[1];
		int[] bDistance = new int[1];
		String firstCommonAncestor = getFirstAncestor(aAncestors, bAncestors, aDistance, bDistance);
		if (firstCommonAncestor == null)
			return "NOT RELATED";
		
		if (firstCommonAncestor.equals(b))
		{
			String prefix = "mother";
			int l = aDistance[0];
			if (l > 1)
				prefix = "grand-" + prefix;
			if (l > 2)
			{
				for (int i = 2; i < l; i++)
				{
					prefix = "great-" + prefix;
				}
			}
			return a + " is the " + prefix + " of " + b;
		} else if (bDistance[0] == 1)
		{
			String prefix = "aunt";
			int l = aDistance[0];
			if (l > 1)
				prefix = "grand-" + prefix;
			if (l > 2)
			{
				for (int i = 2; i < l; i++)
				{
					prefix = "great-" + prefix;
				}
			}
			return a + " is the " + prefix + " of " + b;
		} else
		{
			return "COUSINS";
		}
	}

	private static String getFirstAncestor(List<String> aAncestors, List<String> bAncestors, int[] aDistance,
			int[] bDistance) 
	{
		
	}

	private static List<String> getAncestors(String cow) 
	{
		List<String> ancestors = new ArrayList<>();
		String curr = cow;
		while (map.keySet().contains(curr))
		{
			curr = map.get(curr);
			ancestors.add(curr);
		}
		return ancestors;
	}

	private static void init(Scanner in) 
	{
		numLines = in.nextInt();
		String[] relationInfo = in.nextLine().split(" ");
		a = relationInfo[0];
		b = relationInfo[1];
		
		map = new HashMap<>();
		
		for (int i = 0; i < numLines; i++)
		{
			String[] edgeInfo = in.nextLine().split(" ");
			map.put(edgeInfo[1], edgeInfo[0]);
		}
	}

}
