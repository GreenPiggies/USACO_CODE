import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: sort3
*/
public class sort3 
{
	static int size = 0;
	
	static List<Integer> oldSequence = new ArrayList<Integer>();
	
	static List<Integer> newSequence = new ArrayList<Integer>();

	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
	    
	    size = Integer.parseInt(f.readLine().trim());
	    
	    int num1 = 0;
	    int num2 = 0;
	    int num3 = 0;
	    
	    for (int i = 0; i < size; i++)
	    {
	    	int num = Integer.parseInt(f.readLine().trim());
	    	if (num == 1)
	    	{
	    		num1++;
	    	} else if (num == 2)
	    	{
	    		num2++;
	    	} else
	    	{
	    		num3++;
	    	}
	    	oldSequence.add(num);
	    	newSequence.add(num);
	    }
	    
	    System.out.println(num1 + ", " + num2 + ", " + num3);
	    
	    newSequence.sort(null);
	    
	    System.out.println(newSequence);
	    System.out.println(oldSequence);
	    
	    int numCorrect = 0;
	    
	    int num2In1 = 0;
	    int num3In1 = 0;
	    int num1In2 = 0;
	    int num3In2 = 0;
	    int num1In3 = 0;
	    int num2In3 = 0;
	    
	    
	    for (int i = 0; i < size; i++)
	    {
	    	System.out.println(i);
    		int num = oldSequence.get(i);
	    	if (i < num1)
	    	{
	    		if (num == 2)
	    		{
	    			num2In1++;
	    		} else if (num == 3)
	    		{
	    			num3In1++;
	    		}
	    	} else if (i < num2 + num1)
	    	{
	    		if (num == 1)
	    		{
	    			num1In2++;
	    		} else if (num == 3)
	    		{
	    			num3In2++;
	    		}
	    	} else
	    	{
	    		if (num == 1)
	    		{
	    			num1In3++;
	    		} else if (num == 2)
	    		{
	    			num2In3++;
	    		}
	    	}
	    	
	    }
	    
	    System.out.println("Num 2 in 1: " + num2In1);
	    System.out.println("Num 3 in 1: " + num3In1);
	    System.out.println("Num 1 in 2: " + num1In2);
	    System.out.println("Num 3 in 2: " + num3In2);
	    System.out.println("Num 1 in 3: " + num1In3);
	    System.out.println("Num 2 in 3: " + num2In3);

	    
	    int swaps = 0;
	    
	    while (num1In2 > 0 && num2In1 > 0)
	    {
	    	num1In2--;
	    	num2In1--;
	    	swaps++;
	    }
	    
	    while (num2In3 > 0 && num3In2 > 0)
	    {
	    	num2In3--;
	    	num3In2--;
	    	swaps++;
	    }
	    
	    while (num3In1 > 0 && num1In3 > 0)
	    {
	    	num3In1--;
	    	num1In3--;
	    	swaps++;
	    }
	    System.out.println(swaps);
	    
	    int unswapped = num1In2 + num1In3 + num2In1 + num2In3 + num3In1 + num3In2;
	    	    
	    unswapped *= 2;
	    
	    unswapped /= 3;
	    
	    swaps += unswapped;
	    
	    System.out.println(swaps);
	    	    
	    out.println(swaps);
	    out.close();
	    
	    
	}
}
