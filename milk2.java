import java.io.*;
import java.util.*;

/*
ID: hungwes1
LANG: JAVA
TASK: milk2
*/
public class milk2 
{
	public static void main(String[] args) throws Exception 
	{
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
	    
	    int numPeriods = Integer.parseInt(f.readLine());
	    
	    int startMilk = 1000001;
	    
	    int endMilk = -1;
	    
	    byte[] timeArray = new byte[1000000]; //Super bashy but that's ok!
	    
	    for (int i = 0; i < 1000000; i++)
	    {
	    	timeArray[i] = 0;
	    }
	    
	    for (int i = 0; i < numPeriods; i++)
	    {
	    	String[] info = f.readLine().split(" ");
	    	int start = Integer.parseInt(info[0]);
	    	int end = Integer.parseInt(info[1]);
	    	if (start < startMilk)
	    	{
	    		startMilk = start;
	    	}
	    	if (end > endMilk)
	    	{
	    		endMilk = end;
	    	}
	    	for (int j = start; j < end; j++)
	    	{
	    		timeArray[j] = 1;
	    	}
	    }
	    
	  
	    int timeMilked = 0;
	    int timeNotMilked = 0;
	    
	    int currTimeMilked = 0;
	    int currTimeNotMilked = 0;
	    
	    boolean currentlyMilking = false;
	    
	    for (int i = 0; i < timeArray.length; i++) 
	    {
	    	boolean milkingNow = timeArray[i] == 1;
	    	if (milkingNow != currentlyMilking) 
	    	{
	    		currentlyMilking = milkingNow;
	    		if (currTimeMilked > timeMilked) 
	    		{
	    			timeMilked = currTimeMilked;
	    		} 
	    		if (currTimeNotMilked > timeNotMilked) 
	    		{
	    			timeNotMilked = currTimeNotMilked;
	    		}
	    		currTimeMilked = 0;
	    		currTimeNotMilked = 0;
	    	}
	    	if (i >= startMilk && i < endMilk)
	    	{
	    		if (milkingNow)
		    	{
		    		currTimeMilked++;
		    	} else
		    	{
		    		currTimeNotMilked++;
		    	}
	    	}
	    }
	    out.print(timeMilked);
	    out.print(" ");
	    out.print(timeNotMilked);
	    out.println();
	    
	    out.close();
	}
}
