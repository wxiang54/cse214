package q4src;
import java.util.NoSuchElementException;

/***********************************************
 * LINKED LIST implementation of QUEUE
 * almost like a LinkedList "wrapper" class
 ***********************************************/
public class LLQueue<T> implements Queue<T> {
    private MyLinkedList<T> _LL;

    //constructors
    public LLQueue() {
	_LL = new MyLinkedList<T>();
    }

    @Override
    public void enqueue(T item) {
	_LL.addLast(item);
    }

    @Override
    public T dequeue() {
	if ( isEmpty() ) throw new NoSuchElementException();
	return _LL.removeFirst();
    }

    @Override
    public T peekFront() {
	if ( isEmpty() ) throw new NoSuchElementException();
	return _LL.getFirst();
    }

    @Override
    public boolean isEmpty() {
	return _LL.size() <= 0; //<= instead of =, for safety
    }

    @Override
    public int size() {
	return _LL.size();
    }
    
    @Override
    public String toString() {
	if ( isEmpty() ) return "";
	String retStr = "";
	T last = _LL.getLast();
	while (_LL.getFirst() != last) {
	    //cycle thru LL
	    T cur = _LL.removeFirst();
	    retStr += cur + " ";
	    _LL.addLast(cur);
	}
	
	//here's the last element
	_LL.addLast(_LL.removeFirst());
	retStr += last;
	return retStr;
    }


    
    public static void main(String[] args) {
	System.out.println("\ninitializing LLQueue llq...");
	Queue<Integer> llq = new LLQueue<Integer>();

	System.out.println("\nenqueueing 1-5");
	for (int i = 1; i <= 5; i++)
	    llq.enqueue(i);
	System.out.println(llq);


	System.out.println("\ndequeueing all");
	for (int i = 1; i <= 5; i++) {
	    System.out.println("dequeued: " + llq.dequeue());
	    System.out.println(llq);
	}
    }
}
