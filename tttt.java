/*
ID: greenpig
LANG: JAVA
TASK: tttt
*/
//package USACO_CODE;


import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class tttt 
{
	private static int singleCowWin = 0;
	private static int doubleCowWin = 0;

	public static void main(String[] args) throws Exception 
	{
		BufferedReader f = new BufferedReader(new FileReader("tttt.in"));
	    
	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tttt.out")));	
	    char[][] cowBoard = new char[3][3];
	    
	    for (int i = 0; i < 3; i++)
	    {
	    	String cowRow = f.readLine();
	    	for (int j = 0; j < 3; j++)
	    	{
	    		cowBoard[i][j] = cowRow.charAt(j);
	    	}
	    }
	    
	    
	    
	    Set<Set<Character>> soloCombos = new HashSet<Set<Character>>();
	    Set<Set<Character>> duoCombos = new HashSet<Set<Character>>();
	    for (int rx = 0; rx < 3; rx++)
	    {
	    	Set<Character> rowSet = new HashSet<>();
	    	for (int ry = 0; ry < 3; ry++)
	    	{
	    		rowSet.add(cowBoard[rx][ry]);
	    	}
	    	if (rowSet.size() == 1)
		    {
		    	soloCombos.add(rowSet);
		    } else if (rowSet.size() == 2)
		    {
		    	duoCombos.add(rowSet);
		    }
	    }
	    
	    for (int cy = 0; cy < 3; cy++)
	    {
	    	Set<Character> columnSet = new HashSet<>();
	    	for (int cx = 0; cx < 3; cx++)
	    	{
	    		columnSet.add(cowBoard[cx][cy]);
	    	}
	    	if (columnSet.size() == 1)
		    {
		    	soloCombos.add(columnSet);
		    } else if (columnSet.size() == 2)
		    {
		    	duoCombos.add(columnSet);
		    }
	    }
	    
    	Set<Character> diag1Set = new HashSet<>();

	    for (int d = 0; d < 3; d++)
	    {
	    	diag1Set.add(cowBoard[d][d]);
	    }
	    if (diag1Set.size() == 1)
	    {
	    	soloCombos.add(diag1Set);
	    } else if (diag1Set.size() == 2)
	    {
	    	duoCombos.add(diag1Set);
	    }
	    
    	Set<Character> diag2Set = new HashSet<>();

    	for (int d = 0; d < 3; d++)
	    {
	    	diag2Set.add(cowBoard[d][2 - d]);
	    }
	    if (diag2Set.size() == 1)
	    {
	    	soloCombos.add(diag2Set);
	    } else if (diag2Set.size() == 2)
	    {
	    	duoCombos.add(diag2Set);
	    }
	    
	    
	    
	    out.println(soloCombos.size());
	    out.println(duoCombos.size());
	    
	    out.close();
	    
	}
}
