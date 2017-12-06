import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrisonBreak {

    public static final String MARKED_CHAR = "😂";
    public static final String FREE_CHAR = "👌🏽";
    public static final String WALL_CHAR = "👀";
    
    public static int NUM_PATHS = 0;
    
    //helper method for testing
    public static void print2D(Object[][] arr) {
	String ret = "";
	for (int r = 0; r < arr.length; r++) {
	    for (int c = 0; c < arr[0].length; c++) {
		ret += arr[r][c] + " ";
	    }
	    ret += "\n";	
	}
	System.out.print("\033[H\033[2J");
	System.out.println(ret);
    }

    //helper method for findPathH
    public static boolean isValidSpace(Object[][] arr, int x, int y) {
	//check out of bounds, visited, or motion detector space
	return (x < arr[0].length) && (x >= 0)
	    && (y < arr.length) && (y >= 0)
	    && (!arr[x][y].equals(MARKED_CHAR))
	    && (!arr[x][y].equals(WALL_CHAR));
    }

    public static boolean findPaths(Object[][] arr, int x, int y) throws InterruptedException {
	if (!isValidSpace(arr, x, y))
	    return false;
	
	arr[x][y] = MARKED_CHAR; //set curr space to visited

	//	/****** TESTING ******
	   print2D(arr);
	   Thread.sleep(300);
	   //	**********************/
	
	//see if we've reached end
	if (x == arr[0].length-1 && y == arr.length-1) {
	    arr[x][y] = FREE_CHAR;
	    NUM_PATHS++;
	    return true;
	}
	
	//check all 4 adjacent spaces
	boolean found = false;
	if (findPaths(arr, x+1, y) | findPaths(arr, x-1, y)
	    | findPaths(arr, x, y+1) | findPaths(arr, x, y-1)) {
	    found = true;
	}
	
	//no valid spaces to go -> backtrack
	arr[x][y] = FREE_CHAR;
	return found;
    }
    

    public static int getNumPaths(Object[][] arr) throws InterruptedException {
	NUM_PATHS = 0;
	findPaths(arr, 0, 0); //start at 0, 0
	return NUM_PATHS;
    }
    
    public static void main(String[] args) throws InterruptedException {
	final String FILENAME = "in.txt";
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);
	    int T = Integer.parseInt(sc.nextLine()); //num test cases
	    for (int t = 0; t < T; t++) {
		int N = Integer.parseInt(sc.nextLine()); //size of sq matrix
		String[][] PRISON = new String[N][N];
		for (int l = 0; l < N; l++) {
		    PRISON[l] = sc.nextLine().trim().split(" ");
		    for (int x = 0; x < PRISON[l].length; x++) {
			System.out.println(PRISON[l][x]);
			if (PRISON[l][x].equals("0")) {
			    PRISON[l][x] = FREE_CHAR;
			}
			if (PRISON[l][x].equals("1")) {
			    PRISON[l][x] = WALL_CHAR;
			}
		    }
		}
		
		System.out.println("Case " + t + ": N=" + N);
		System.out.println(getNumPaths(PRISON));
		Thread.sleep(1000);
	    }
	}
	    
	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
