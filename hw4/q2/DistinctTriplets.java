import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class DistinctTriplets {
    
    public static void main(String[] args) throws InterruptedException {
	final String FILENAME = "kek.txt";
	final int NUM_CASES = 1;
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);

	    for (int x = 0; x < NUM_CASES; x++) {
		int N = sc.nextInt(); //num elems
		int M = Integer.parseInt(sc.nextLine().trim()); //mythical const
		if (M == 0)
		    System.out.println("0"); //should always be 0
		else {
		    String[] NUMS_unparsed = sc.nextLine().trim().split(" ");


		    HashMap<Integer, HashMap> outer = new HashMap<Integer, HashMap>();

		    //STEP ONE:
		    //  * convert each element in input array to integer mod(%) counterpart
		    //  * purely for shorter and neater code
		    //  * runtime: O(n)
		    for (int i = 0; i < N; i++) {
			NUMS[i] = Integer.parseInt(NUMS_unparsed[i] % M);
		    }
	    
		    //STEP TWO:
		    // * fill up outer hashtable with inner hashtables
		    // * runtime: O(n)
		    for (int i = 0; i < N; i++) {
			HashMap<Integer, Integer> inner = new HashMap<Integer, Integer>();
			outer.put(i, inner);
			
		    }
	    
		    //STEP THREE:
		    //  * increment freq. of each modulo remainder key into hashtable
		    //  * for each hashtable with later index than self
		    //  * runtime: O(n^2)
		    for (int i = 0; i < N; i++) {
			for (int j = 1; j < i; j++) {
			    //		    System.out.println("i: " + i + ", j: " + j);
			    HashMap<Integer, Integer> inner = outer.get(j);
			    //if not in table, insert; if already in table, increment
			    if ( inner.putIfAbsent(NUMS[i], 1) != null ) {
				inner.replace(NUMS[i], inner.get(NUMS[i]) + 1);
			    }
			}
		    }

		    //	    System.out.println(brown);
	    
		    //STEP FOUR:
		    //  * calc. double sums and search for third element (only 1 poss. option per sum)
		    //  * runtime: O(n^2)
		    int numTriplets = 0; //ANSWER
		    for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
			    int double_sum = NUMS[i] + NUMS[j];
			    int findMe;
			    HashMap<Integer, Integer> inner = outer.get(j);
			    //		    System.out.println("\ti: " + i + ", j: " + j + " -- " + inner);
			    //		    Thread.sleep(1000);
			    if (double_sum < M && inner.containsKey(M - double_sum))
				findMe = M - double_sum;
			    else if (double_sum > M && inner.containsKey(2*M - double_sum))
				findMe = 2*M - double_sum;
			    else if ((double_sum == M || double_sum == 0) && inner.containsKey(0))
				findMe = 0;
			    else {
				findMe = -1;
				//			System.out.println("failed to find: " + NUMS[i] + ", " + NUMS[j] + ", [" + findMe + "]");
				//	Thread.sleep(1000);
			    }

			    if (findMe != -1) {
				//first add all options to triplets
				int test = 0;
				numTriplets += inner.get(findMe);
				test += inner.get(findMe);
				//			System.out.println(NUMS[i] + ", " + NUMS[j] + ", " + findMe);
				//			System.out.println("\tthenadded " + test);
			    }
			}
		    }

		    // ANSWER:
		    System.out.println(numTriplets);
		}
	    }
	}
	    
	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
