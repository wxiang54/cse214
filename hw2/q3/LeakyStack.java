import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.EmptyStackException;

/********************************************************
 * CLASS LEAKYSTACK for Q3 of HW2
 *
 * Implements Stack using circular array of size n
 *******************************************************/

public class LeakyStack<T> implements Stack<T> {
    private static final int DEFAULT_CAPACITY = 3;
    private T[] _data;
    private int _top; //index of next top elem space
    private int _bottom; //index of bottom elem

    public LeakyStack() {
	this(DEFAULT_CAPACITY);
    }
    
    public LeakyStack(int capacity) throws IllegalStateException {
	if (capacity <= 0)
	    throw new IllegalStateException("Stack must be at least length 1");
	_top = 0;
	_bottom = -1;
	_data = (T[]) new Object[capacity];
    }
    
    @Override
    public void push(T item) {
	//make sure top doesn't overtake bottom
	if (_top == _bottom || _bottom < 0)
	    _bottom++;
	if (_bottom >= _data.length)
	    _bottom = 0;
	_data[_top++] = item;
	if (_top >= _data.length)
	    _top = 0;
    }

    @Override
    public T pop() throws EmptyStackException {
	if (isEmpty()) throw new EmptyStackException();
 
	//decrement, then check for wrap
	if (--_top < 0) 
	    _top = _data.length - 1;
	T ret = _data[_top];
	_data[_top] = null; //avoid loitering
	
	if (_top == _bottom) { //this means stack was emptied -> reset stack
	    _bottom = -1;
	    _top = 0;
	}
	return ret;
    }

    @Override
    public T peek() throws EmptyStackException {
	if (isEmpty()) throw new EmptyStackException();
	if (_top == 0)
	    return _data[_data.length - 1];
	return _data[_top-1];
    }

    @Override
    public boolean isEmpty() {
	int size;
	if (_bottom < _top)
	    size = _top - _bottom;
	else
	    size = _top - _bottom + _data.length;
	return size == 0 || _bottom < 0;
    }

    @Override
    public String toString() {
	if (isEmpty()) return "";

	String retStr = "";
	int cur;
	if (_top == 0) cur = _data.length - 1;
	else cur = _top - 1;

	while (cur != _bottom) {
	    //decrement cur then check for wrap
	    retStr += _data[cur--] + " ";
	    if (cur < 0)
		cur = _data.length-1;
	}
	return retStr + _data[cur];
    }


    
    public static void main(String[] args) {
	if (args.length >= 1 && args[0].equals("debug")) {
	    debugStack();
	    System.exit(0);
	}
	
	final String FILENAME = "in3.txt";
	final int NUM_CASES;
	File file = new File(FILENAME);
	
	try {
	    Scanner sc = new Scanner(file);
	    NUM_CASES = Integer.parseInt(sc.nextLine());
	    for (int i = 0; i < NUM_CASES; i++) {
		int CAPACITY = Integer.parseInt(sc.nextLine());
		String[] OPS = sc.nextLine().split(" ");
		Stack<String> ls = new LeakyStack<String>(CAPACITY);
		for (String s : OPS) {
		    ls.push(s);
		}
		System.out.println(ls);
	    }
	}

	catch (FileNotFoundException e) {
	    System.out.println("File not found: " + FILENAME);
	}
    }






    //FOR TESTING/DEBUGGING PURPOSES ONLY
    public static void debugStack() {
	System.out.println("\ninitializing Stack s...");
	Stack<Integer> s = new LeakyStack<Integer>();
	System.out.println("[" + s + "]");
	
	System.out.println("\nTESTING EXCEPTIONS...");
	try { s.peek(); } //should throw error
	catch (EmptyStackException e) {
	    System.out.println("s.peek() EmptyStackException thrown.");
	};

	try { s.pop(); } //should throw error
	catch (EmptyStackException e) {
	    System.out.println("s.pop() EmptyStackException thrown.");
	};

	
	System.out.println("\nTESTING PUSH...");
	for (int i = 1; i <= 7; i++) {
	    s.push(i);
	    System.out.println("pushing " + i + ": [" + s + "]");
	}

	System.out.println("\nTESTING POP...");
	System.out.println("s.peek() -> " + s.peek());
	System.out.println("s.pop() -> " + s.pop());
	System.out.println("s.peek() -> " + s.peek());
	System.out.println("[" + s + "]");

	System.out.println("\nPOPPING ALL...");
	while (!s.isEmpty()) {
	    System.out.println("s.pop() -> " + s.pop());
	    System.out.println("[" + s + "]");
	}
    }
}
