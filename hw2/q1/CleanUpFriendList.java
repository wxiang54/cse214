import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import q1src.*;

public class CleanUpFriendList {
    
    //INNER CLASS FRIEND
    private static class Friend implements Comparable<Friend> {
	private String _name;
	private int _numMF; //num of mutual friends

	public Friend( String name, int numMF ) {
	    _name = name;
	    _numMF = numMF;
	}
	
	@Override
	public int compareTo(Friend other) { return _numMF - other._numMF; }
	public String toString() { return _name; }
    }

    
    //insertion sort by num of mutual friends
    public static void sortFriendsByMF(Friend[] arr) {
	for (int i = 1; i < arr.length; i++)
	    for (int j = i; j > 0; j--)
		if (arr[j].compareTo(arr[j-1]) < 0) {
		    Friend tmp = arr[j-1];
		    arr[j-1] = arr[j];
		    arr[j] = tmp;
		}
    }

    //insertion sort by name
    public static void sortFriendsByName(Friend[] arr) {
	for (int i = 1; i < arr.length; i++)
	    for (int j = i; j > 0; j--)
		if (arr[j]._name.compareTo(arr[j-1]._name) < 0) {
		    Friend tmp = arr[j-1];
		    arr[j-1] = arr[j];
		    arr[j] = tmp;
		}
    }

    
    public static void printFriends(Friend[] arr) {
	String retStr = "";
	for (int i = 0; i < arr.length; i++)
	    retStr += arr[i] + " ";
	System.out.println( retStr.substring(0, retStr.length()-1) );
    }

    
    
    public static void main(String[] args) {
	final String FILENAME = "in1.txt";
	final int NUM_CASES;
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);
	    NUM_CASES = Integer.parseInt(sc.nextLine());
	    for (int i = 0; i < NUM_CASES; i++) {
		int N = sc.nextInt(); //num of newly added friends
		int K = Integer.parseInt(sc.nextLine().trim()); //num of friends to keep
		String[] NAMES = sc.nextLine().split(" ");
		String[] NUM_MUTUAL_FRIENDS = sc.nextLine().split(" ");
		if (NAMES.length != NUM_MUTUAL_FRIENDS.length)
		    throw new IllegalArgumentException("num of names is mismatched with num of mutual friends.");
		
		
		//finding min and max
		Friend[] FRIENDS = new Friend[NAMES.length]; //for ease of handling
		int minMF = Integer.parseInt( NUM_MUTUAL_FRIENDS[0] ); //default first friend
		int maxMF = minMF; //default first friend
		for (int j = 0; j < NAMES.length; j++) {
		    Friend f = new Friend(NAMES[j], Integer.parseInt( NUM_MUTUAL_FRIENDS[j] ));
		    if (f._numMF < minMF) {
			minMF = f._numMF;
		    }
		    else if (f._numMF > maxMF) {
			maxMF = f._numMF;
		    }
		    FRIENDS[j] = f;
		}


		
		MyLinkedList<Friend> ANSWER = new MyLinkedList<Friend>();
		
		//bucketize and calculate friend left
		sortFriendsByMF(FRIENDS); //must insert in order
		int B = (int) Math.round((maxMF - minMF + 1) / (double) K); //default bucket size
		for (int mf = minMF; mf < maxMF; mf+=B) {
		    int bucket_min = mf;
		    int bucket_max = mf + B - 1;
		    if (mf == minMF + (K-1)*B) //if last bucket
			bucket_max = maxMF; //this accounts for both expanding/shrinking last bucket
		    
		    Queue<Friend> bucket = new LLQueue<Friend>();
		    for (Friend f : FRIENDS) {
			if (f._numMF >= bucket_min && f._numMF <= bucket_max)
			    bucket.enqueue(f);
		    }
		    
		    //start running josephus simulation
		    if (bucket.isEmpty()) break;
		    int cycleAmt = K % (bucket.size()+1); //so won't cycle thru entire list more than once
		    while ( bucket.size() > 1 ) {
			for (int z = 1; z < cycleAmt; z++) {
			    bucket.enqueue( bucket.dequeue() );
			}
			bucket.dequeue(); //"executed" friend
		    }
		    ANSWER.addLast( bucket.dequeue() ); //friend to keep
		}

		
		//and finally transfer friends to array, sort, and print answer
		Friend[] SORTED_ANSWER = new Friend[ ANSWER.size() ]; 
		for (int x = 0; x < SORTED_ANSWER.length; x++) {
		    SORTED_ANSWER[x] = ANSWER.removeFirst();
		}
		sortFriendsByName( SORTED_ANSWER );
		printFriends( SORTED_ANSWER );
		
	    }
	}

	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
