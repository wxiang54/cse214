import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreeTester {

    public static void main(String[] args) {
	final String FILENAME = "in3.txt";
	File file = new File(FILENAME);
	
	try {
	    Scanner sc = new Scanner(file);
	    int Q = Integer.parseInt( sc.nextLine() ); //num ops

	    //this is the tree!
	    TwoThreeFourTree tree = new TwoThreeFourTree();
	    
	    for (int i = 0; i < Q; i++) {
		String[] op = sc.nextLine().split(" ");
		try {
		    switch( Integer.parseInt(op[0]) ) {
		    case 1:
			//insert k
			tree.insert( Integer.parseInt(op[1]) );
			break;
		    case 2:
			//delete k
			if ( tree.delete(Integer.parseInt(op[1])) )
			    System.out.println("successful");
			else
			    System.out.println("failed");
			break;
		    case 3:
			//search k
			if ( tree.search(Integer.parseInt(op[1])) )
			    System.out.println("successful");
			else
			    System.out.println("failed");
			break;
		    case 4:
			//inorder traversal
			System.out.println( tree.inorder() );
			break;
		    case 5:
			//preorder traversal
			System.out.println( tree.preorder() );
			break;
		    case 6:
			//postorder traversal
			System.out.println( tree.postorder() );
			break;
		    default:
			System.out.println("Operation not supported: " + op[0]);
			break;
		    }
		}

		catch (UnsupportedOperationException e) {
		    System.out.println("error: operation implemented");
		}


		
	    }
	}

	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }
}
