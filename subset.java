import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;
public class subset 
{
    static long[][] cache = new long[8000][40];
    public static void main(String[] args) throws Exception 
    {
        BufferedReader f = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new FileWriter("subset.out"));
        init();
        int n = Integer.parseInt(f.readLine());
        int sum = n * (n + 1) / 2;
        if (sum % 2 != 0) 
        {
        	out.println("0");
        } else 
        {
        	out.println(numberOfWays(sum / 2, n) / 2);
        }
        out.close();
    }
    
    public static void init() 
    {
        for(int i = 0; i < cache.length; ++i)
            Arrays.fill(cache[i], -1);
    }
    
    public static long numberOfWays(int n, int k) 
    {
        if(n < 0 || k < 0) 
        {
        	return 0;
        }
        
        if (cache[n][k] != -1)
        {
        	return cache[n][k];
        }
        if (n == 0 && k == 0) 
        {
        	return cache[n][k] = 1;
        }
        return cache[n][k] = numberOfWays(n, k - 1) + numberOfWays(n - k, k - 1);
    }
}