import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: holstein
*/
public class holstein 
{
	static class Feed implements Comparable
	{
		List<Integer> vitamins;
		int size;
		int index;
		
		public Feed(List<Integer> input, int index)
		{
			vitamins = input;
			size = 0;
			for (Integer vitamin : vitamins)
			{
				size += vitamin;
			}
			this.index = index;
		}
		
		public int compareTo(Object other)
		{
			Feed that = (Feed) other;
			if (this.index < that.index)
			{
				return -1;
			} else if (this.index > that.index)
			{
				return 1;
			} else
			{
				if (this.size < that.size)
				{
					return -1;
				} else
				{
					return 1;
				}
			}
		}
		
		public String toString()
		{
			return String.valueOf(index);
		}
	}
	
	static int numVitamins = 0;
	
	static int numFeeds = 0;
	
	static int[] vitaminAmounts = null;
	
	static List<Feed> feeds = null;
	
	public static void main(String[] args) throws Exception
	{
		// Reading stuff
		
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new FileWriter("holstein.out"));
		
		numVitamins = Integer.parseInt(f.readLine());
		vitaminAmounts = new int[numVitamins];
		
		String[] info1 = f.readLine().split(" ");
		for (int i = 0; i < numVitamins; i++)
		{
			vitaminAmounts[i] = Integer.parseInt(info1[i]);
		}
		
		numFeeds = Integer.parseInt(f.readLine());
		
		feeds = new ArrayList<Feed>();
		for (int i = 0; i < numFeeds; i++)
		{
			String[] info = f.readLine().split(" ");
			List<Integer> realInfo = new ArrayList<Integer>();
			for (int j = 0; j < numVitamins; j++)
			{
				realInfo.add(Integer.parseInt(info[j]));
			}
			feeds.add(new Feed(realInfo, i + 1));
		}
		
		// Cool, we can just bash this rn
		System.out.println(feeds);
		
		List<List<Feed>> possibleScoops = null;

		for (int numScoops = 1; numScoops <= numFeeds; numScoops++)
		{
			List<List<Feed>> allScoops = getAllScoops(numScoops);
			
			System.out.println(allScoops);
			
			possibleScoops = getWorkingScoops(allScoops);
			
			System.out.println(possibleScoops);
			
			if (!possibleScoops.isEmpty())
			{
				break;
			}
			
			
		}
		List<Feed> smallestScoops = getSmallest(possibleScoops);
			
		out.print((smallestScoops).size());
		
		smallestScoops.sort(null);
			
		for (Feed feed : smallestScoops)		
		{
			out.print(" " + feed.index);
		}
		
		out.println();
			
		out.close();
			
	}	
		

	

	private static List<List<Feed>> getAllScoops(int numScoops) 
	{
		List<List<Feed>> possibleScoops = new ArrayList<List<Feed>>();
		if (numScoops == 1)
		{
			for (Feed feed : feeds)
			{
				List<Feed> scoops = new ArrayList<Feed>();
				scoops.add(feed);
				possibleScoops.add(scoops);
			}
		} else
		{
			List<List<Feed>> oldScoops = getAllScoops(numScoops - 1);
			for (List<Feed> scoops : oldScoops) // For all the smaller scoops
			{
				
				int index = scoops.get(0).index;
				for (int i = index; i < feeds.size(); i++) // For all the feeds
				{
					Feed feed = feeds.get(i);
					List<Feed> newScoops = new ArrayList<Feed>();
					newScoops.add(feed);
					newScoops.addAll(scoops);
					possibleScoops.add(newScoops);
				}
			}
		}
		return possibleScoops;
	}
	
	private static List<List<Feed>> getWorkingScoops(List<List<Feed>> allScoops) 
	{
		List<List<Feed>> workingScoops = new ArrayList<List<Feed>>();
		for (List<Feed> scoops : allScoops)
		{
			int[] allVitamins = new int[numVitamins];
			for (Feed feed : scoops)
			{
				for (int i = 0; i < feed.vitamins.size(); i++)
				{
					allVitamins[i] += feed.vitamins.get(i);
				}
			}
			boolean works = true;
			for (int i = 0; i < vitaminAmounts.length; i++)
			{
				if (allVitamins[i] < vitaminAmounts[i])
				{
					works = false;
				}
			}
			if (works)
			{
				workingScoops.add(scoops);
			}
		}
		return workingScoops;
	}

	

	private static List<Feed> getSmallest(List<List<Feed>> possibleScoops) 
	{
		int smallestIndex = 0;
		int smallestScoops = value(possibleScoops.get(0)); 
		for (int i = 1; i < possibleScoops.size(); i++)
		{
			int curr = value(possibleScoops.get(i));
			if (curr < smallestScoops)
			{
				smallestScoops = curr;
				smallestIndex = i;
			}
		}
		return possibleScoops.get(smallestIndex);
	}

	private static int value(List<Feed> list) 
	{
		int sum = 0;
		for (Feed feed : list)
		{
			sum += feed.index;
		}
		return sum;
	}

}
