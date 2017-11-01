import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MinSwaps {

    public static void main(String[] args) {
	final String FILENAME = "in1.txt";
	File file = new File(FILENAME);

	try {
	    Scanner sc = new Scanner(file);
	    int N = Integer.parseInt(sc.nextLine()); //num of nodes
	    String[] NODES_str = sc.nextLine().split(" ");
	    BinaryTree bt = new BinaryTree(N);
	    for (String val : NODES_str) {
		bt.add( Integer.parseInt(val) );
	    }

	    String[] INORDER_str = bt.inorder().split(" ");
	    int[] INORDER = new int[N];
	    for (int i = 0; i < N; i++) {
		INORDER[i] = Integer.parseInt( INORDER_str[i] );
	    }

	    //and now we selection sort the inorder traversal: O(n^2)
	    int swapCtr = 0;
	    for (int i = 0; i < N; i++) {

		//first find min element
		int minPos = i;
		int min = INORDER[minPos];
		for (int j = i+1; j < N; j++)
		    if (INORDER[j] < min) {
			minPos = j;
			min = INORDER[minPos];
		    }

		//then swap the elements and increment swap counter
		if (i != minPos) {
		    //System.out.println(swapCtr + ": " + "swap " + INORDER[i] + " with " + INORDER[minPos]);
		    INORDER[i] = INORDER[i] ^ INORDER[minPos];
		    INORDER[minPos] = INORDER[i] ^ INORDER[minPos];
		    INORDER[i] = INORDER[i] ^ INORDER[minPos];
		    swapCtr++;
		}
	    }

	    //ANSWER:
	    System.out.println(swapCtr);
	    
	}
	    
	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
