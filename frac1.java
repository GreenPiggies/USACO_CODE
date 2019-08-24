import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: frac1
*/
public class frac1 
{
	static class Fraction implements Comparable
	{

		@Override
		public String toString() {
			return (int) numerator + "/" + (int) denominator;
		}

		double numerator;
		double denominator;
		double value;
		
		public Fraction(double numerator, double denominator)
		{
			this.numerator = numerator;
			this.denominator = denominator;
			
			value = numerator / denominator;
		}
		
		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(denominator);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(numerator);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(value);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) 
		{
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Fraction other = (Fraction) obj;
			if (Double.doubleToLongBits(denominator) != Double.doubleToLongBits(other.denominator))
				return false;
			if (Double.doubleToLongBits(numerator) != Double.doubleToLongBits(other.numerator))
				return false;
			if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
				return false;
			return true;
		}
				
		
		public int compareTo(Object obj)
		{
			Fraction that = (Fraction) obj;
			
			if (this.value < that.value)
			{
				return -1;
			} else
			{
				return 1;
			}
		}
	}
	
	public static boolean isSimplified(int numerator, int denominator)
	{
		if (numerator == 1)
		{
			return true;
		}
		
		int division = 2;
		while (division <= numerator)
		{
			if (numerator % division == 0 && denominator % division == 0) // If both are divisible by some divisor
			{
				return false; // It's not simplified
			}
			division++;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
	    
	    int denominatorLength = Integer.parseInt(f.readLine());
	    
	    List<Fraction> fractions = new ArrayList<>();
	    
	    fractions.add(new Fraction(0, 1));
	    
	    for (int denominator = 1; denominator <= denominatorLength; denominator++)
	    {
	    	for (int numerator = 1; numerator < denominator; numerator++)
	    	{
	    		
	    		if (isSimplified(numerator, denominator))
	    		{
	    			fractions.add(new Fraction(numerator, denominator));
	    		}
	    	}
	    }
	    
	    fractions.add(new Fraction(1, 1));
	    
	    fractions.sort(null);
	    
	    for (Fraction fraction : fractions)
	    {
	    	out.println(fraction);
	    }
	    out.close();
	}

}
