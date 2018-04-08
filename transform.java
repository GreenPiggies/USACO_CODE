import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: transform
*/
public class transform 
{
	static char[][] before = null;
    static char[][] after = null;
    static int patternSize = 0;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
	    
	    Set<Integer> patternSet = new HashSet<>();
	    patternSize = Integer.parseInt(f.readLine()); 
	    before = new char[patternSize][patternSize];
	    after = new char[patternSize][patternSize];
	    char[][] before = new char[patternSize][patternSize];
	    char[][] after = new char[patternSize][patternSize];
	    
	    for (int i = 0; i < patternSize; i++)
	    {
	    	String row = f.readLine();
	    	// Should be "(symbol)_(symbol)_(symbol)"
	    	for (int j = 0; j < patternSize; j++)
	    	{
	    		before[i][j] = row.charAt(j); //First element goes to first place in row, same for the others
	    	}
	    }
	    
	    for (int i = 0; i < patternSize; i++)
	    {
	    	String row = f.readLine();
	    	for (int j = 0; j < patternSize; j++)
	    	{
	    		after[i][j] = row.charAt(j); //First element goes to first place in row, same for the others
	    	}
	    }
	    
	    char[][] newArray = before.clone();
	    
	    if (equals(newArray, after))
	    {
	    	patternSet.add(6);
	    }
	    
	    for (int i = 0; i < 3; i++)
	    {
	    	newArray = rotation(newArray);
	    	if (equals(newArray, after))
	    	{
	    		patternSet.add(i + 1);
	    	}
	    }
	    
	    newArray = reflection(before.clone());
	    
	    if (equals(newArray, after))
	    {
	    	patternSet.add(4);
	    }
	    
	    for (int i = 0; i < 3; i++)
	    {
	    	newArray = rotation(newArray);
	    	if (equals(newArray, after))
	    	{
	    		patternSet.add(5);
	    	}
	    }

	    
	    
	    int smallest = 7;
	    for (int pattern : patternSet)
	    {
	    	if (smallest > pattern)
	    	{
	    		smallest = pattern;
	    	}
	    }
	   	out.print(smallest);
	    
	    
	    out.println();
	    out.close();
	}
	
	private static void printArray(char[][] array)
	{
		for (int i = 0; i < patternSize; i++)
		{
			for (int j = 0; j < patternSize; j++)
			{
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}
	
	private static char[][] rotation(char[][] original)
	{
		char[][] newArray = new char[patternSize][patternSize];
		
		for (int j = newArray.length - 1; j > -1; j--)
		{
			for (int i = 0; i < newArray.length; i++)
			{
				newArray[i][j] = original[patternSize - 1 - j][i];
			}
		}
		return newArray;
	}
	
	private static char[][] reflection(char[][] original)
	{
		char[][] newArray = new char[patternSize][patternSize];
		
		for (int i = 0; i < patternSize; i++)
		{
			for (int j = 0; j < patternSize; j++)
			{
				newArray[i][j] = original[i][patternSize - 1 - j];
			}
		}
		return newArray;
	}
	
	private static boolean equals(char[][] oldArray, char[][] newArray)
	{
		for (int i = 0; i < patternSize; i++)
		{
			for (int j = 0; j < patternSize; j++)
			{
				if (oldArray[i][j] != newArray[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

}
