import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Practice1 {

    public static void printHeap( MinHeap heap ) {
	MinHeap heap2 = new MinHeap( heap.size() );
	while (!heap.isEmpty()) {
	    int deq = heap.delete();
	    System.out.print( deq + " " );
	    heap2.insert( deq );
	}
	System.out.println();
	while (!heap2.isEmpty()) {
	    heap.insert( heap2.delete() );
	}
    }
    
    
    //priority queue implementation
    public static void main( String[] args ) {
	final String FILENAME = "practice1.txt";
	final int NUM_QUERIES;
	MinHeap heap = new MinHeap();
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);
	    NUM_QUERIES = Integer.parseInt(sc.nextLine());
	    for (int i = 0; i < NUM_QUERIES; i++) {
		String[] query = sc.nextLine().split(" ");
		switch (query[0]) {
		case "e":
		    heap.insert( Integer.parseInt(query[1]) );
		    break;
		case "d":
		    heap.delete();
		    break;
		case "p":
		    printHeap(heap);
		    break;
		default:
		    break;
		}
	    }
	}

	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
