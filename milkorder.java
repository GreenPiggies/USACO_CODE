

import java.io.*;
import java.util.*;
public class milkorder {

	public static void main(String[] args) throws Exception 
	{
		BufferedReader f = new BufferedReader(new FileReader("milkorder.in"));
	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
	    
	    String[] firstRow = f.readLine().split(" ");
	    	    
	    int[] finalOrder = new int[Integer.parseInt(firstRow[0])];
	    
	    for (int i = 0; i < finalOrder.length; i++)
	    {
	    	finalOrder[i] = 0;
	    }
	    
	    Map<Integer, Integer> cowPositions = new HashMap<>();
	    
	    int hNum = Integer.parseInt(firstRow[1]);    
	    int pNum = Integer.parseInt(firstRow[2]);    
	    
	    String[] cowOrder = f.readLine().split(" ");
	    ArrayList<Integer> order = new ArrayList<Integer>();
	    for (int i = 0; i < hNum; i++)
	    {
	    	order.add(Integer.parseInt(cowOrder[i]));
	    }
	    
	    
	    String[] cowPosNum = f.readLine().split(" ");
	    
	    ArrayList<Integer> cows = new ArrayList<>();
	    for (int i = 0; i < cowPosNum.length; i++)
	    {
	    	cows.add(Integer.parseInt(cowPosNum[i]));
	    }
	    
	    String[] cowPos = f.readLine().split(" ");
	    for (int i = 0; i < cowPos.length; i++)
	    {
	    	finalOrder[Integer.parseInt(cowPos[i]) - 1] = cows.get(i);
	    	cowPositions.put(cows.get(i), Integer.parseInt(cowPos[i]) - 1);
	    }
	    
	    
	    System.out.println(cowPositions);
	    for (int i = 0; i < finalOrder.length; i++)
	    {
	    	System.out.print(finalOrder[i] + " ");
	    }
	    
	    //System.out.println("What the final order looks like before the big stuff: ");
	    //printArray(finalOrder);
	    
	    int lastPosition = finalOrder.length - 1;
	    
	    for (int i = order.size() - 1; i > -1; i--)
	    {
	    	int cow = order.get(i);
	    	if (!cowPositions.containsKey(cow))
	    	{
	    		for (int j = lastPosition; j > -1; j--, lastPosition--)
	    		{
	    			if (finalOrder[j] == 0)
	    			{
	    				finalOrder[j] = cow;
	    				cowPositions.put(cow, j);
	    				break;
	    			}
	    		}
	    	} else
	    	{
	    		lastPosition = cowPositions.get(cow) - 1;
	    	}
	    }
	    
	    for (int i = 0; i < finalOrder.length; i++)
	    {
	    	if (finalOrder[i] == 0)
	    	{
	    		out.println(i + 1);
		    	break;
	    	}
	    }
	    
	    //System.out.println("What the final order looks like after the big stuff: ");
	    //printArray(finalOrder);
	    
	    out.close();
	    

	}

}
