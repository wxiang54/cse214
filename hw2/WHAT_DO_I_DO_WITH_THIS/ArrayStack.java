package q3src;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {
    private T[] _data;
    private int _size;

    public ArrayStack() {
	//starting at size 10 array prevents overhead from
	//expanding 1 to 2 to 4 to 8, which seems a waste
	_data = (T[]) new Object[10];
	_size = 0;
    }

    @Override
    public void push(T n) {
	if (_size >= _data.length) { //arr is full -> expand
	    T[] newArr = (T[]) new Object[_data.length * 2]; //double size
	    //deep copy entries
	    for (int i = 0; i < _data.length; i++) newArr[i] = _data[i];
	    _data = newArr;
	}

	_data[_size] = n;
	_size++;
    }

    @Override
    public T pop() {
	if (isEmpty()) throw new EmptyStackException();
	_size--;
	T popped = _data[_size];
	if (_size <= _data.length / 4.0) { //less than quarter full -> shrink
	    T[] newArr = (T[]) new Object[_data.length/2]; //half size
	    //deep copy entries
	    for (int i = 0; i < newArr.length; i++) newArr[i] = _data[i];
	    _data = newArr;
	}
	return popped;
    }

    @Override
    public T peek() {
	if (isEmpty()) throw new EmptyStackException();
	return _data[_size-1];
    }

    @Override
    public boolean isEmpty() {
	return _size == 0;
    }


    
    public static void main(String[] args) {
	System.out.println("initializing Stack s...");
	Stack s = new ArrayStack();

	System.out.println("TESTING EXCEPTIONS...");
	try { s.peek(); } //should throw error
	catch (EmptyStackException e) {
	    System.out.println("s.peek() empty stack exception thrown");
	};
	try { s.pop(); } //should throw error
	catch (EmptyStackException e) {
	    System.out.println("s.pop() empty stack exception thrown");
	};
	
	System.out.println("\nPUSHING 1 TO 11...");
	for (int i = 1; i <= 11; i++)
	    s.push(i);
	
	System.out.println("s.peek() -> " + s.peek());
	System.out.println("s.pop() -> " + s.pop());
	System.out.println("s.peek() -> " + s.peek());
	
	System.out.println("\nPOPPING ALL...");
	while (!s.isEmpty()) {
	    System.out.println("s.pop() -> " + s.pop());
	}

    }
}
