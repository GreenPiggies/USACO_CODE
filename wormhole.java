import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: wormhole
*/
public class wormhole 
{
	class Hole
	{
		long x;
		long y;
	}
	
	class HolePair
	{
		Hole a;
		Hole b;
	}
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
	    
	    int numHoles = Integer.parseInt(f.readLine());
	    
	    List<Hole> holes = readHoles(f, numHoles);
	    
	    List<List<HolePair>> allCombos = getAllCombos(holes);
	    
	    int found = 0;
	    
	    for (List<HolePair> combo : allCombos)
	    {
	    	for (Hole start : holes)
			{
				if (hasLoop(combo, start, holes))
				{
					found++;
				}
			}
	    }
	   
	}

	private static List<List<HolePair>> getAllCombos(List<Hole> holes) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static boolean hasLoop(List<HolePair> combo, Hole start, List<Hole> holes) 
	{
		Set<Hole> visited = new HashSet<>();
		Hole curr = start;
		while (curr != null)
		{
			visited.add(curr);
			Hole otherHole = enterWormHole(combo, curr);
			visited.add(otherHole);
			curr = findNext(otherHole, holes);
			if (visited.contains(curr))
			{
				return true;
			}
		}
		return false;
	}

	private static Hole enterWormHole(List<HolePair> combo, Hole curr) 
	{
		for (HolePair pair : combo)
		{
			if (pair.a.x == curr.x && pair.a.y == curr.y)
			{
				return pair.b;
			} else if (pair.b.x == curr.x && pair.b.y == curr.y)
			{
				return pair.a;
			}
		}
		return null;
	}

	private static Hole findNext(Hole startHole, List<Hole> holes) 
	{
		Map<Long, Hole> xToHole= new HashMap<>();
		long sX = startHole.x;
		long sY = startHole.y;
		long smallestX = Long.MAX_VALUE;
		for (Hole hole : holes)
		{
			long hX = hole.x;
			long hY = hole.y;
			if (sY == hY && sX < hX && hX < smallestX)
			{
				smallestX = hX;
				xToHole.put(smallestX, hole);
			}
		}
		return xToHole.containsKey(smallestX) ? xToHole.get(smallestX) : null;
		
	}

}
