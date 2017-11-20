import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
	final String FILENAME = "in1.txt";
	final int NUM_CASES = 1;
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);
	    for (int x = 0; x < NUM_CASES; x++) {
		int N = sc.nextInt(); //num elems
		int T = Integer.parseInt(sc.nextLine().trim()); //sum
		String[] NUMS_unparsed = sc.nextLine().trim().split(" ");

		//insert values into hashmap
		HashMap<Integer, Integer> brown = new HashMap<Integer, Integer>(N);
		for (int i = 0; i < N; i++) {
		    int n = Integer.parseInt( NUMS_unparsed[i] );
		    brown.put(n, i);
		}

		//check if the difference exists in hashmap
		boolean found = false;
		for (int i = 0; i < N; i++) {
		    int toFind = T - Integer.parseInt( NUMS_unparsed[i] );
		    if (brown.containsKey(toFind)) {
			int i2 = (int)(brown.get(toFind));
			if (i2 != i) { //so not the same element
			    System.out.println("[" + i + ", " + i2 + "]");
			    found = true;
			    break;
			}
		    }
		}
	    
		if (!found) //no pair found
		    System.out.println("[-1, -1]");
	    }
	}
	    
	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
