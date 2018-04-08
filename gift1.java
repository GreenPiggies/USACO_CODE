

import java.io.*;
import java.util.*;


public class gift1 
{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
	    
	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
	    
	    Map<String, Integer> peopleBalances = new HashMap<>();
	    int numPeople = Integer.parseInt(f.readLine());
	    String[] names = new String[numPeople];
	    for (int i = 0; i < numPeople; i++)
	    {
	    	String name = f.readLine();
	    	peopleBalances.put(name, 0);
	    	names[i] = name;
	    }
	    
	    for (int i = 0; i < numPeople; i++)
	    {
	    	String giver = f.readLine();
	    	String[] giverInfoString = f.readLine().split(" ");
	    	int giveAmount = Integer.parseInt(giverInfoString[0]);
	    	int splitNum = Integer.parseInt(giverInfoString[1]);
	    	
	    	if (splitNum != 0)
	    	{
	    		peopleBalances.replace(giver, peopleBalances.get(giver) - giveAmount + (giveAmount % splitNum));
		    	
		    	for (int j = 0; j < splitNum; j++)
		    	{
		    		String recipient = f.readLine();
		    		peopleBalances.replace(recipient, peopleBalances.get(recipient) + giveAmount / splitNum);
		    	}
	    	}
	    	
	    	
	    	
	    }
	   
	    for (int i = 0; i < numPeople; i++)
	    {
	    	out.println(names[i] + " " + peopleBalances.get(names[i]));
	    }
	    out.close();                                  // close the output file
	}


}
