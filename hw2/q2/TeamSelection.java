import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import questionTwo.*;

public class TeamSelection {
    private static class Player implements Comparable<Player> {
	private int _jersey;
	private double _height;

	public Player(int jersey, double height) {
	    _jersey = jersey;
	    _height = height;
	}

	public int compareTo(Player other) {
	    if (_height > other._height)
		return 1;
	    if (_height < other._height)
		return -1;
	    return 0;
	}

	public String toString() {
	    return Integer.toString(_jersey);
	}
	  
    }

    
    public static void main(String[] args) {
	final String FILENAME = "in2.txt";
	final int NUM_CASES;
	File file = new File(FILENAME);

	try {	    
	    Scanner sc = new Scanner(file);
	    NUM_CASES = Integer.parseInt(sc.nextLine());
	    for (int i = 0; i < NUM_CASES; i++) {
		String[] JERSEYS = sc.nextLine().split(" ");
		String[] HEIGHTS = sc.nextLine().split(" ");
		DLL<Player> playerDLL = new DLL<Player>();
		
		//inserting in reverse bc adding at front each time for simplicity
		//also assume JERSEYS.length = HEIGHTS.length
		for (int j = JERSEYS.length-1; j >= 0; j--) {
		    Player p = new Player( Integer.parseInt(JERSEYS[j]),
					   Double.parseDouble(HEIGHTS[j]) );
		    playerDLL.add(p);
		}

		MyIterator<Player> iter = playerDLL.iterator();
		while (iter.hasNext()) {
		    Player p1 = iter.next();
		    //rightmost person doesn't need comparing
		    if (!iter.hasNext())
			break;
		    if (p1.compareTo(iter.peekNext()) < 0)
			iter.remove();
		}
		System.out.println(playerDLL);
	    }
	    sc.close();
	} 
	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
