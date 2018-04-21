import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: milk
*/
public class milk 
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
	    
	    String[] mmmInfo = f.readLine().split(" ");
	    int amountNeeded = Integer.parseInt(mmmInfo[0]);
	    int numFarmers = Integer.parseInt(mmmInfo[1]);
	    int totalPrice = 0;
	    Map<Integer, Integer> priceToQuantity = new TreeMap<>();
	    
	    for (int i = 0; i < numFarmers; i++)
	    {
	    	String[] farmerInfo = f.readLine().split(" ");
	    	int price = Integer.parseInt(farmerInfo[0]);
	    	int quantity = Integer.parseInt(farmerInfo[1]);
	    	if (priceToQuantity.containsKey(price))
	    	{
	    		priceToQuantity.put(price, priceToQuantity.get(price) + quantity);
	    	} else
	    	{
	    		priceToQuantity.put(price, quantity);
	    	}
	    }
	    
	    for (int num : priceToQuantity.keySet())
	    {
	    	System.out.println(num);
	    }
	    while (amountNeeded > 0)
	    {
	    	int price = (int) priceToQuantity.keySet().toArray()[0];
		    int amountHad = priceToQuantity.get(price);
		    int amountBought = amountHad;
		    if (amountHad > amountNeeded)
		    {
		    	amountBought = amountNeeded;
		    	amountNeeded = 0;
		    } 
		    amountNeeded -= amountBought;
		    totalPrice += price * amountBought;
		    priceToQuantity.remove(price);
	    }
	    out.println(totalPrice);
	    
	    out.close();
		
	}

}
