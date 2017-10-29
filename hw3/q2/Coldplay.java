import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//driver class for HW3 Q2
public class Coldplay {

    public static void main(String[] args) {
	final String FILENAME = "in2.txt";
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);
	    int M = sc.nextInt(); //num rows
	    int N = Integer.parseInt( sc.nextLine().trim() ); //num fans

	    String[] SEATS_str = sc.nextLine().split(" ");
	    MyPriorityQueue SEATS = new MyPriorityQueue(M);
	    for (String seat : SEATS_str) {
		SEATS.enqueue( Integer.parseInt(seat) );
	    }


	    //algorithm starts here:
	    int sumMoney = 0;
	    while ( N > 0 ) {
		int numSeatsInRow = SEATS.dequeue();
		sumMoney += numSeatsInRow;
		SEATS.enqueue( numSeatsInRow - 1 );
		N--;
	    }

	    //ANSWER:
	    System.out.println(sumMoney);	    
	}

	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }    
}
