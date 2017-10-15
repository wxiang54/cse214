import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import q4src.*;

public class NightKing {

    //PRIVATE INNER CLASS WHITEWALKER
    private static class WhiteWalker {
	private static int numWW = 0; //helps give index of WW in initial queue
	private int _power;
	private int _index;

	public WhiteWalker(int power) {
	    _power = power;
	    _index = numWW;
	    numWW++;
	}

	public String toString() { return Integer.toString(_index); }
    }
    
    public static void main(String[] args) {
	final String FILENAME = "in4.txt";
	final int NUM_CASES;
	
	File file = new File(FILENAME);
	try {
	    Scanner sc = new Scanner(file);
	    NUM_CASES = Integer.parseInt(sc.nextLine());
	    for (int i = 0; i < NUM_CASES; i++) {
		int N = sc.nextInt();
		int M = Integer.parseInt(sc.nextLine().trim());
		String[] POWERS = sc.nextLine().split(" ");
		Queue<WhiteWalker> WHITEWALKERS = new LLQueue<WhiteWalker>();
		Queue<WhiteWalker> WW_SELECTED = new LLQueue<WhiteWalker>(); //"selected" WW go in here
		
		for (String s : POWERS) { //create a WW for each power
		    WhiteWalker ww = new WhiteWalker( Integer.parseInt(s) );
		    WHITEWALKERS.enqueue(ww);
		}

		//M iterations of algo
		for (int j = 0; j < M; j++) {
		    WhiteWalker[] dequeued_ww = new WhiteWalker[ Math.min(M, WHITEWALKERS.size()) ];
		    int maxpower = -1; //this is max power value of whitewalkers
		    int ind_maxpower = 0; //this is index of white walker with max power

		    for (int k = 0; k < dequeued_ww.length; k++) {
			//if M > queue length, all items should be dequeued
			WhiteWalker ww = WHITEWALKERS.dequeue();
			dequeued_ww[k] = ww;
			if (ww._power > maxpower) {
			    maxpower = ww._power;
			    ind_maxpower = k;
			}
		    }

		    for (int k = 0; k < dequeued_ww.length; k++) {
			if (k == ind_maxpower)
			    continue; //WW with max power shouldn't be enqueued back
			WhiteWalker ww = dequeued_ww[k];
			if (ww._power > 0)
			    ww._power--; //only decrement if power not already 0
			WHITEWALKERS.enqueue(ww);
		    }

		    WW_SELECTED.enqueue( dequeued_ww[ind_maxpower] );
		}

		//THIS IS THE ANSWER
		System.out.println(WW_SELECTED);
	    }
	}

	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
