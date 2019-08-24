import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: wormhole
*/
public class wormhole 
{
	static class Hole
	{
		long x;
		long y;
		
		public Hole(long x, long y)
		{
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return ("(" + x + ", " + y + ")");
		}
		
		public boolean equals(Object o)
		{
			Hole that = (Hole) o;
			return (this.x == that.x && this.y == that.y);
		}
		
		public int hashCode()
		{
			return (int) (17 * x + y);
		}
	}
	
	static class HolePair
	{
		
		Hole a;
		Hole b;
		public HolePair(Hole h1, Hole h2) {
			a = h1;
			b = h2;
		}
		
		public String toString() {
			return (a + "," + b);
		}
		
		public boolean equals(Object o)
		{
			HolePair that = (HolePair) o;
			return (this.a.equals(that.a) && this.b.equals(that.b) ||
					this.a.equals(that.b) && this.b.equals(that.a));
			
		}
		
		public int hashCode()
		{
			return a.hashCode() + b.hashCode();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
	    
	    long numHoles = Long.parseLong(f.readLine());
	    
	    List<Hole> holes = readHoles(f, numHoles);
	    	    	    	    
	    List<List<HolePair>> allCombos = getAllCombos(holes);
	    
	    Set<List<HolePair>> loopCombos = new HashSet<List<HolePair>>();
	    
	    //So far, these are correct
	    
	    //What could be wrong:
	    //hasLoop
	    //containsList
	    	    
		for (List<HolePair> combo : allCombos)
	    {
	    	for (Hole start : holes)
			{
	    		if (hasLoop(combo, start, holes) != null)
	    		{
		    		System.out.println(loopCombos.add(combo)); 
		    		break;
	    		}
			}
	    }
		
		out.println(loopCombos.size());
		out.close();
		
	   
	}
	
	private static List<Hole> readHoles(BufferedReader f, long numHoles) throws Exception
	{
		List<Hole> holes = new ArrayList<>();
		for (int i = 0; i < numHoles; i++)
		{
			String[] coordinates = f.readLine().split(" ");
			long x = Integer.parseInt(coordinates[0]);
			long y = Integer.parseInt(coordinates[1]);
			holes.add(new Hole(x, y));
		}
		return holes;
	}

	private static List<List<HolePair>> getAllCombos(List<Hole> holes) 
	{
		if (holes.size() == 2)
		{
			List<HolePair> appendage = new ArrayList<HolePair>();
			appendage.add(new HolePair(holes.get(0), holes.get(1)));
			List<List<HolePair>> appendageList = new ArrayList<List<HolePair>>();
			appendageList.add(appendage);
			return appendageList;
		} else
		{
			
			List<List<HolePair>> pairs = new ArrayList<List<HolePair>>();
			Hole h1 = holes.get(0);
			for (int i = 1; i < holes.size(); i++)
			{
				List<Hole> copyHoles = new ArrayList<Hole>(holes);
				Hole h2 = copyHoles.get(i);
				HolePair pair = new HolePair(h1, h2);	
				copyHoles.remove(h1);
				copyHoles.remove(h2);
				addNewCombosInto(pairs, pair, getAllCombos(copyHoles));
			}
			return pairs;
		}
	}

	private static void addNewCombosInto(List<List<HolePair>> pairs, HolePair pair, List<List<HolePair>> subCombos) 
	{
		for (List<HolePair> combo : subCombos)
		{
			combo.add(pair);
			pairs.add(combo);
		}
	}

	private static List<Hole> hasLoop(List<HolePair> combo, Hole start, List<Hole> holes) 
	{
		List<Hole> visited = new ArrayList<>();
		Hole curr = start;
		while (curr != null)
		{
			visited.add(curr);
			Hole otherHole = enterWormHole(combo, curr);
			Hole nextWormhole = findNext(otherHole, holes);
			if (visited.contains(nextWormhole))
			{
				return visited;
			}
			curr = nextWormhole;
		}
		return null;
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
		long sX = startHole.x;
		long sY = startHole.y;
		long smallestX = Long.MAX_VALUE;
		Hole smallestHole = null;
		for (Hole hole : holes)
		{
			//Get x and y
			long hX = hole.x;
			long hY = hole.y;
			//If on the same y
			//and sX < hX
			//and hX < smallestX
			if (sY == hY && sX < hX && hX < smallestX)
			{
				smallestX = hX;
				smallestHole = hole;
			}
		}
		return smallestHole;
	}

}
