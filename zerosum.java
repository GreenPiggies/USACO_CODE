import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: zerosum
*/
public class zerosum 
{
	static int n;
	static ArrayList<String> solutions;
	static StreamTokenizer input;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
			
		PrintWriter out = new PrintWriter(new FileWriter("zerosum.out"));
		
		input = new StreamTokenizer(f);
		
		n = nextInt();
		
		solutions = new ArrayList<String>();
		getValues(1, 1, "1", false);

		solutions.sort(null);
		
		for (String sol : solutions)
		{
			out.println(sol);
		}
		out.close();
	}

	static void getValues(int index, int currentValue, String currStr, boolean a) 
	{
        if (index >= n) 
        {
            if (currentValue == 0)
            {
            	if (!solutions.contains(currStr))
            	{
                    solutions.add(currStr); // Add the solution
            	}
            }
            return; // EXIT
        }
        getValues(index + 1, currentValue + (index + 1), currStr + "+" + (index + 1), false); // ONE with plus
        getValues(index + 1, currentValue - (index + 1), currStr + "-" + (index + 1), false); // ONE with minus
        
        if (a) return;
        
        if (index==1)
        {
            getValues(index + 1, index * 10 + (index + 1), currStr + " " + (index + 1), true); // ONE with SPACE
            return;
        }
        
        if (currStr.lastIndexOf("+") > currStr.lastIndexOf("-"))
        {
            getValues(index + 1, currentValue - index + index * 10 + (index + 1), currStr + " " + (index + 1), true); // ONE with ADDITION
        }
        else
        {
            getValues(index + 1, currentValue + index - (index * 10 + (index + 1)), currStr + " " + (index + 1),true); // ONE with SUBTRACTION
        }
    }
	
    static int nextInt() throws Exception 
    {
        input.nextToken();
        return (int) input.nval;
    }

	

}
