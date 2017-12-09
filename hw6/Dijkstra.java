import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashSet;

public class Dijkstra {
    public static int[][] ADJ_MATRIX;
    public static int[] DISTANCE_ARR;
    public static int[] DISTANCE_PARENTS; //parent of each dist

    //initializes static vars for Dijkstra's algo
    public static void initVars(int N) {
	ADJ_MATRIX = new int[N][N];
	DISTANCE_ARR = new int[N];
	for (int i = 0; i < N; i++) {
	    DISTANCE_ARR[i] = Integer.MAX_VALUE; //basically infinity
	}
	DISTANCE_PARENTS = new int[N];
    }

    //helper fxn
    public static LinkedList<Integer> getNeighbors(int src) {
	LinkedList<Integer> neighbors = new LinkedList<Integer>();
	for (int c = 0; c < ADJ_MATRIX[0].length; c++) {
	    if (ADJ_MATRIX[src][c] > 0)
		neighbors.add(c);
	}
	return neighbors;
    }

    //helper fxn to get min value in V-S (unvisited nodes)
    public static int getMinUnvisited(HashSet<Integer> visited) {
	//	if (arr.length == 0) throw new NoSuchElementException();
	int min = Integer.MAX_VALUE;
	int minInd = -1;
	for (int i = 0; i < DISTANCE_ARR.length; i++) {
	    if (DISTANCE_ARR[i] < min && !visited.contains(i)) {
		min = DISTANCE_ARR[i];
		minInd = i;
	    }
	}
	return minInd;
    }
	
    
    //here is actual Dijkstra's algo
    public static void getShortestPathsFromSrc(int src) throws InterruptedException {
	HashSet<Integer> visited = new HashSet<Integer>();
	DISTANCE_ARR[src] = 0;
	for (int i = 1; i < DISTANCE_ARR.length; i++) {
	    int next = getMinUnvisited(visited);
	    visited.add(next);
	    LinkedList<Integer> neighbors = getNeighbors(next);
	    ListIterator<Integer> iter = neighbors.listIterator(0);
	    while (iter.hasNext()) {
		int nodeInd = iter.next();
		if (!visited.contains(nodeInd)) {
		    int old_dist = DISTANCE_ARR[nodeInd];
		    int new_dist = DISTANCE_ARR[next] + ADJ_MATRIX[next][nodeInd];
		    if (new_dist < old_dist) {
			//replace old dist with new dist in distance arr
			DISTANCE_ARR[nodeInd] = new_dist;
			//and also replace parent
			DISTANCE_PARENTS[nodeInd] = next;
		    }
		}
	    }
	}
	return;
    }
    
    public static void main(String[] args) throws InterruptedException {
	final String FILENAME = "in1.txt";
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);
	    int T = Integer.parseInt(sc.nextLine()); //num test cases
	    for (int t = 0; t < T; t++) {
		int N = Integer.parseInt(sc.nextLine()); //num of vertices
		int S = sc.nextInt(); //source index
		int D = Integer.parseInt(sc.nextLine().trim());

		initVars(N);
		for (int l = 0; l < N; l++) {
		    String[] unparsed = sc.nextLine().split(" ");
		    //convert to ints
		    for (int p = 0; p < unparsed.length; p++) {
			ADJ_MATRIX[l][p] = Integer.parseInt(unparsed[p]);
		    }
		}

		//Dijkstra's algo run here:
		getShortestPathsFromSrc(S);
		System.out.println(DISTANCE_ARR[D]); //shortest dist to dest

		//use dist parents arr to get exact path
		LinkedList<Integer> path = new LinkedList<Integer>();
		path.add(D);
		int curNodeInd = D;
		int curNodeParentInd = DISTANCE_PARENTS[curNodeInd];
		while (curNodeParentInd != S) {
		    path.addFirst(curNodeParentInd);
		    curNodeInd = curNodeParentInd;
		    curNodeParentInd = DISTANCE_PARENTS[curNodeInd];
		}

		//finally print the path
		ListIterator<Integer> iter = path.listIterator();
		String s = S + " -> ";
		while (iter.hasNext()) {
		    s += iter.next() + " -> ";
		}
		System.out.println(s.substring(0, s.length()-4));
	    }
	}
	    
	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
