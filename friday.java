/*
ID: hungwes1
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;


public class friday 
{
	
	private static void printDay(int num)
    {
    	switch (num)
    	{
    		case 0:
    			System.out.println("Sunday");
    			break;
    		case 1:
    			System.out.println("Monday");
    			break;

    		case 2:
    			System.out.println("Tuesday");
    			break;

    		case 3:
    			System.out.println("Wednesday");
    			break;

    		case 4: 
    			System.out.println("Thursday");
    			break;

    		case 5:
    			System.out.println("Friday");
    			break;

    		case 6:
    			System.out.println("Saturday");
    			break;

    		default:
    			System.out.println("REEEEEEEEEEEE");
    			break;
    		
    	}
    }
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
	    
	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
	    
	    int periodLength = Integer.parseInt(f.readLine());
	    
		
	    int startPeriod = 1900;
	    
	    int endPeriod = 1900 + periodLength;
	    	    
	    Map<Integer, Integer> occurrences = new HashMap<>();
	    
	    for (int i = 0; i < 7; i++)
	    {
	    	occurrences.put(i, 0);
	    }
	    
	    int dayNumber = 1;
	    
	    /*
	     * Sunday is 0
	     * Monday is 1
	     * Tuesday is 2
	     * Wednesday is 3
	     * Thursday is 4
	     * Friday is 5
	     * Saturday is 6
	     * */
	    
	    
	    
	    for (int i = startPeriod; i < endPeriod; i++)
	    {
	    	//January 13
    		dayNumber += 12;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		
    		
    		//February 13
    		dayNumber += 31;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		
    		
	    	if (i % 4 == 0 && (i % 100 != 0 || i % 400 == 0))
	    	{
	    		//March 13
	    		dayNumber += 29;
	    	} else
	    	{
	    		dayNumber += 28;
	    	}
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		

    		//April 13
    		dayNumber += 31;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		

    		//May 13
    		dayNumber += 30;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		

    		//June 13
    		dayNumber += 31;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		
    		
    		//July 13
    		dayNumber += 30;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		
    		
    		//August 13
    		dayNumber += 31;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		
    		
    		//September 13
    		dayNumber += 31;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		

    		
    		//October 13
    		dayNumber += 30;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		
    		
    		//November 13
    		dayNumber += 31;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		
    		
    		//December 13
    		dayNumber += 30;
    		occurrences.put(dayNumber % 7, occurrences.get(dayNumber % 7) + 1);
    		

    		
    		dayNumber += 19;
    		
	    }
	    
	    out.print(occurrences.get(6) + " ");
	    for (int i = 0; i < occurrences.size() - 2; i++)
	    {
	    	out.print(occurrences.get(i) + " ");
	    }
	    out.print(occurrences.get(5));
	    out.println();
	    
	    out.close();
	}


}