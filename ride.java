package USACO_CODE;
/*
ID: hungwes1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
  public static void main (String [] args) throws IOException {
    
						  // Get line, break into tokens
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String groupName = "STARAB";
    String randomName = "USACO";
    int groupValue = 1;
    int randomValue = 1;
    for (char letter : groupName.toCharArray())
    {
    	groupValue *= (alphabet.indexOf(letter) + 1);
    }
    for (char letter : randomName.toCharArray())
    {
    	randomValue *= (alphabet.indexOf(letter) + 1);
    }
    System.out.println((groupValue % 47 == randomValue % 47) ? "GO" : "STAY");
    System.out.close();                                  // close the output file
  }
}