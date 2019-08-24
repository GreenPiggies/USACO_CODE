import java.io.*;
import java.util.*;

/*
ID: hungwes1
LANG: JAVA
TASK: milk3
*/
public class milk3 
{
	static enum Bucket {
		A, B, C
	};
	
	static class Buckets
	{
		@Override
		public boolean equals(Object obj) 
		{
			Buckets that = (Buckets) obj;
			return (this.a == that.a
					&& this.b == that.b
					&& this.c == that.c);
		}

		@Override
		public int hashCode() 
		{
			return a * 17 + b * 13 + c;
		}

		int a;
		int b;
		int c;
		
		public Buckets(int a, int b, int c)
		{
			this.a = a;
			this.b = b;
			this.c = c;
		}	
	}
	
	static int SIZE_A = 0;
	static int SIZE_B = 0;
	static int SIZE_C = 0;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
	    
	    String[] info = f.readLine().split(" ");
	    
	    SIZE_A = Integer.parseInt(info[0]);
	    SIZE_B = Integer.parseInt(info[1]);
	    SIZE_C = Integer.parseInt(info[2]);
	    
	    Set<Integer> milkAmountsInC = findMilkPossibilities();
	    
	    List<Integer> milkAmounts = new ArrayList<>();
	    milkAmounts.addAll(milkAmountsInC);
	    milkAmounts.sort(null);
	    
	    for (int i = 0; i < milkAmounts.size() - 1; i++)
	    {
	    	out.print(milkAmounts.get(i) + " ");
	    }
	    out.print(milkAmounts.get(milkAmounts.size() - 1));
	    out.println();
	    out.close();
	    
	}

	
	
	private static Set<Integer> findMilkPossibilities() 
	{
		Set<Integer> possibilities = new HashSet<>();
		// Initializing the queue...
		Set<Buckets> visited = new HashSet<Buckets>();
		Queue<Buckets> queue = new LinkedList<Buckets>();
		queue.add(new Buckets(0, 0, SIZE_C));
		while (!queue.isEmpty())
		{
			Buckets combo = queue.remove();
			if (!visited.contains(combo))
			{
				if (combo.a == 0)
				{
					possibilities.add(combo.c);
				}
				queue.addAll(generateCombo(combo));
				visited.add(combo);
			}
		}
		return possibilities;
	}

	private static Set<Buckets> generateCombo(Buckets combo) 
	{
		Set<Buckets> combos = new HashSet<Buckets>();
		combos.add(pourInto(combo, Bucket.A, Bucket.B));
		combos.add(pourInto(combo, Bucket.A, Bucket.C));
		combos.add(pourInto(combo, Bucket.B, Bucket.A));
		combos.add(pourInto(combo, Bucket.B, Bucket.C));
		combos.add(pourInto(combo, Bucket.C, Bucket.A));
		combos.add(pourInto(combo, Bucket.C, Bucket.B));
		return combos;
	}

	private static Buckets pourInto(Buckets combo, Bucket from, Bucket to) 
	{		
		int startAmount, endAmount;
		int endSize;
		switch (from)
		{
			case A:
				startAmount = combo.a; 
				break;
			case B:
				startAmount = combo.b;
				break;
			case C: 
				startAmount = combo.c;
				break;
			default:
				assert false;
				startAmount = 0;
				break;
		}
		switch (to)
		{
			case A:
				endAmount = combo.a;
				endSize = SIZE_A;
				break;
			case B:
				endAmount = combo.b;
				endSize = SIZE_B;
				break;
			case C: 
				endAmount = combo.c;
				endSize = SIZE_C;
				break;
			default:
				assert false;
				endAmount = 0;
				endSize = 0;
				break;
		}		
		int endCapacity = endSize - endAmount;
		int newFrom = 0;
		int newTo = 0;
		if (endCapacity < startAmount) // Not enough space
		{
			newTo = endSize;
			newFrom = startAmount - endCapacity;
		} else // Enough space
		{
			newTo += startAmount + endAmount;
			newFrom = 0;
		}
		
		Buckets newCombo = new Buckets(combo.a, combo.b, combo.c);
		
		if (from == Bucket.A && to == Bucket.B)
		{
			newCombo.a = newFrom;
			newCombo.b = newTo;
		} else if (from == Bucket.A && to == Bucket.C)
		{
			newCombo.a = newFrom;
			newCombo.c = newTo;
		} else if (from == Bucket.B && to == Bucket.A)
		{
			newCombo.b = newFrom;
			newCombo.a = newTo;
		} else if (from == Bucket.B && to == Bucket.C)
		{
			newCombo.b = newFrom;
			newCombo.c = newTo;
		} else if (from == Bucket.C && to == Bucket.A)
		{
			newCombo.c = newFrom;
			newCombo.a = newTo;
		} else if (from == Bucket.C && to == Bucket.B)
		{
			newCombo.c = newFrom;
			newCombo.b = newTo;
		} else 
			assert false;
		
		return newCombo;
	}
}
