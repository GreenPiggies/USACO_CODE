import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: combo
*/

public class combo 
{
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
	    
	    int dialSize = Integer.parseInt(f.readLine());
	    
	    if (dialSize == 1)
	    {
	    	out.println(1);
	    } else
	    {
	    	int[] farmerCombo = readCombo(f);
		    
		    int[] masterCombo = readCombo(f);
		    System.out.println("Possible farmer digits: ");
		    Set<List<Integer>> validFarmerCombos = getValidCombos(farmerCombo, dialSize);
		    System.out.println("Possible master digits: ");
		    Set<List<Integer>> validMasterCombos = getValidCombos(masterCombo, dialSize);
		    
		    Set<List<Integer>> validCombos = new HashSet<List<Integer>>();
		    validCombos.addAll(validFarmerCombos);
		    validCombos.addAll(validMasterCombos);
		    	    
		    out.println(validCombos.size());
		    
		    
	    }
	    
	    out.close();
	}
	
	

	private static Set<List<Integer>> getValidCombos(int[] farmerCombo, int dialSize) 
	{
		Set<List<Integer>> validCombos = new HashSet<List<Integer>>();
		
		List<Integer> first = getPossibleDigits(farmerCombo[0], dialSize);
		List<Integer> second = getPossibleDigits(farmerCombo[1], dialSize);
		List<Integer> third = getPossibleDigits(farmerCombo[2], dialSize);
		
		for (Integer a : first)
		{
			for (Integer b : second)
			{
				for (Integer c : third)
				{
					List<Integer> combo = new ArrayList<Integer>();
					combo.add(a);
					combo.add(b);
					combo.add(c);
					validCombos.add(combo);
				}
			}
		}
		
		return validCombos;
	}



	private static List<Integer> getPossibleDigits(int digit, int dialSize) 
	{
		List<Integer> digits = new ArrayList<>();
		for (int i = digit - 2; i <= digit + 2; i++)
		{
			int validDigit = i;
			if (validDigit < 1)
			{
				validDigit += dialSize;
			} else if (validDigit > dialSize)
			{
				validDigit -= dialSize;
			}
			digits.add(validDigit);
		}		
		System.out.println("HELLO?");
		System.out.println(digits);
		return digits;
	}



	private static int[] readCombo(BufferedReader f) throws Exception
	{
		int[] combo = new int[3];
		String[] comboStr = f.readLine().split(" ");
		
		for (int i = 0; i < comboStr.length; i++)
		{
			combo[i] = Integer.parseInt(comboStr[i]);
		}
		
		return combo;
		
	}

}
