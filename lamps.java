import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: lamps
*/
public class lamps 
{
	static int numLamps;
	static String defaultLamps;
	static int count;
	static List<Integer> on;
	static List<Integer> off;	
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lamps.out"));
		
		init(f);
		List<String> testCases = generateTestCases();
		System.out.println(testCases);
		List<String> workingTestCases = findWorkingCases(testCases);
		workingTestCases.sort(null);
		System.out.println(workingTestCases);
		for (String testCase : workingTestCases)
		{
			out.println(testCase);
		}
		if (workingTestCases.isEmpty())
		{
			out.println("IMPOSSIBLE");
		}
		out.close();

	}
	
	
	
	private static List<String> findWorkingCases(List<String> testCases) 
	{
		List<String> workingCases = new ArrayList<String>();
		for (String testCase : testCases)
		{
			boolean valid = true;
			for (int index : on)
			{
				String digit = testCase.substring(index - 1, index);
				if (digit.equals("0"))
				{
					valid = false;
					break;
				}
			}
			for (int index : off)
			{
				String digit = testCase.substring(index - 1, index);
				if (digit.equals("1"))
				{
					valid = false;
					break;
				}
			}
			if (valid)
			{
				workingCases.add(testCase);
			}
		}
		return workingCases;
	}



	private static List<String> generateTestCases() 
	{
		List<String> testCases = new ArrayList<>();
		if (count == 0)
		{
			testCases.add(defaultLamps); // Get one only
		} else if (count == 1) 
		{
			for (int i = 1; i <= 4; i++)
			{
				testCases.add(getCombo(defaultLamps, i)); // 1, 2, 3, 4
			}
		} else
		{
			for (int i = 1; i <= 4; i++)
			{
				testCases.add(getCombo(defaultLamps, i)); // 1, 2, 3
			}
			List<String> secondTestCases = new ArrayList<>();
			for (String testCase : testCases)
			{
				secondTestCases.add(getCombo(testCase, 4)); // 1 + 4, 2 + 4, 3 + 4
			}
			testCases.addAll(secondTestCases);
		}
		return testCases;
	}



	private static String getCombo(String lampCombo, int button) 
	{
		if (button == 1)
		{
			for (int i = 0; i < lampCombo.length(); i++)
			{
				String digit = lampCombo.substring(i, i + 1);
				String prefix = lampCombo.substring(0, i);
				String affix = lampCombo.substring(i + 1);
				lampCombo = digit.equals("0") ? 
						prefix + "1" + affix : // If true
						prefix + "0" + affix; // If false
			}
		} else if (button == 2)
		{
			for (int i = 0; i < lampCombo.length(); i += 2)
			{
				String digit = lampCombo.substring(i, i + 1);
				String prefix = lampCombo.substring(0, i);
				String affix = lampCombo.substring(i + 1);
				lampCombo = digit.equals("0") ? 
						prefix + "1" + affix : // If true
						prefix + "0" + affix; // If false
			}
		} else if (button == 3)
		{
			for (int i = 1; i < lampCombo.length(); i += 2)
			{
				String digit = lampCombo.substring(i, i + 1);
				String prefix = lampCombo.substring(0, i);
				String affix = lampCombo.substring(i + 1);
				lampCombo = digit.equals("0") ? 
						prefix + "1" + affix : // If true
						prefix + "0" + affix; // If false
			}
		} else if (button == 4)
		{
			for (int i = 0; i < lampCombo.length(); i += 3)
			{
				String digit = lampCombo.substring(i, i + 1);
				String prefix = lampCombo.substring(0, i);
				String affix = lampCombo.substring(i + 1);
				lampCombo = digit.equals("0") ? 
						prefix + "1" + affix : // If true
						prefix + "0" + affix; // If false
			}
		}
		return lampCombo;
	}

	public static void init(BufferedReader f) throws Exception
	{
		numLamps = Integer.parseInt(f.readLine());
		
		defaultLamps = "";
		for (int i = 0; i < numLamps; i++)
		{
			defaultLamps += "1";
		}
		
		count = Integer.parseInt(f.readLine());
		
		on = new ArrayList<Integer>();
		String[] onInfo = f.readLine().split(" ");
		for (int i = 0; i < onInfo.length - 1; i++)
		{
			on.add(Integer.parseInt(onInfo[i]));
		}
		
		off = new ArrayList<Integer>();
		String[] offInfo = f.readLine().split(" ");
		for (int i = 0; i < offInfo.length - 1; i++)
		{
			off.add(Integer.parseInt(offInfo[i]));
		}
		
		System.out.println(defaultLamps);
		System.out.println(numLamps);
		System.out.println(count);
		System.out.println(on);
		System.out.println(off);
				
	}

}
