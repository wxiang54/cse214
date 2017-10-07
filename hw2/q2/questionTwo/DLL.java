package questionTwo;
import java.util.NoSuchElementException;

/* DOUBLY LINKEDLIST CLASS FOR HW2 Q2
 *
 * Optimization: Iterator for linear accessing
EXPLANATION HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
 **********************************************************/


public class DLL<T> implements MyIterable {
    private Node<T> _head;
    private Node<T> _tail;
    private int _size;
        
    //INNER NODE CLASS
    private static class Node<T> {
	private T _data;
	private Node<T> _next;
	private Node<T> _prev;
	
	public Node(T data) {
	    _data = data;
	    _next = _prev = null;
	}
	
	public T getData() {return _data;}
	public void setData(T data) {_data = data;}
	public Node<T> getNext() {return _next;}
	public void setNext(Node<T> next) {_next = next;}
	public Node<T> getPrev() {return _prev;}
	public void setPrev(Node<T> prev) {_prev = prev;}
    } //end inner node class


    /*********** ITERABLE INTERFACE FUNCTION *********************/
    //miraculously this shoddy implementation covers all standards
    //and fxnality set forth by Java 7 API in clever ways

    @Override
    public MyIterator<T> iterator(){
	return new MyIterator<T>() {
	    private Node<T> _cur = _head;
	    private boolean _nextCalled = false;
	    
	    @Override
	    public boolean hasNext() {
		//if just removed, just check that cur node not null
		if (!_nextCalled)
		    return _cur != null;
		return _cur.getNext() != null;
	    }
	    
	    @Override
	    public T next() {
		if (!hasNext()) throw new NoSuchElementException();
		if (_nextCalled) //only get next if remove wasn't just called
		    _cur = _cur.getNext();
		_nextCalled = true;
		return _cur.getData();
	    }
	    
	    @Override
	    public void remove() {
		if (!_nextCalled || _cur == null) throw new IllegalStateException();
		if (_cur == _head) {
		    _cur = _cur.getNext();
		    _head = _cur;
		    if (_head != null) //new head
			_head.setPrev(null);
		}
		else {
		    _cur = _cur.getPrev();
		    _cur.setNext( _cur.getNext().getNext() );
		    if (_cur.getNext() != null) //new _cur
			_cur.getNext().setPrev(_cur);
		    _cur = _cur.getNext();
		}
		_size--;
		_nextCalled = false;
	    }

	    //OPTIMIZATION METHOD
	    @Override
	    public T peekNext() {
		if (!hasNext()) throw new NoSuchElementException();
		if (!_nextCalled) return _cur.getData(); //if remove just called
		return _cur.getNext().getData();
	    }
	};
    }
    
    //LL CONSTRUCTOR
    public DLL() {
	_head = _tail = null;
	_size = 0;
    }

    //GETTERS/SETTERS, nothing interesting here
    public Node<T> getHead() {return _head;}
    public void setHead(Node<T> head) {_head = head;}
    public int size() {return _size;}

    
    public T get(int index) {
	if (index < 0 || index >= _size)
	    throw new IndexOutOfBoundsException();
	if (index == 0) return _head.getData();
	Node<T> cur = _head;
	for (int i = 0; i < index; i++) {
	    cur = cur.getNext();
	}
	return cur.getData();
    }

    
    //default: add at head
    public void add(T item) {
	Node<T> newNode = new Node<T>(item);
	newNode.setNext(_head); //if list empty, next is null
	if (_head != null)
	    _head.setPrev(newNode);
	_head = newNode;
	_size++;
    }

    //overload add() when index given
    public void add(int index, T item) {
	if (index < 0 || _size < index)
	    throw new IndexOutOfBoundsException();
	if (index == 0)
	    add(item);
	
	//now shouldn't go out of bounds
	Node<T> cur = _head;
	for (int i = 0; i < index-1; i++)
	    cur = cur.getNext(); //stop at node before insertion pt

	Node<T> newNode = new Node<T>(item);
	newNode.setPrev(cur);
	newNode.setNext(cur.getNext());
	if (cur.getNext() != null)
	    cur.getNext().setPrev(newNode);
	cur.setNext(newNode);
	_size++;
    }


    
    //default: remove at head
    public T remove() {
	if (_size <= 0) throw new IndexOutOfBoundsException();
	T ret = _head.getData();
	_head = _head.getNext();
	if (_head != null) //new head
	    _head.setPrev(null);
	_size--;
	return ret;
    }
    
    //overload remove() when index given
    public T remove(int index) {
	if (index < 0 || index >= _size)
	    throw new IndexOutOfBoundsException();
	if (index == 0)
	    return remove();
	
	T ret;
	Node<T> cur = _head;
	for (int i = 0; i < index-1; i++)
	    cur = cur.getNext(); //stop at node before insertion pt
	ret = cur.getNext().getData();
	cur.setNext( cur.getNext().getNext() ); //if removing last item, will be null
	if (cur.getNext() != null) //new next for cur
	    cur.getNext().setPrev(cur);
	_size--;
	return ret;
    }

    		    
    @Override
    public String toString() {
	if (_size <= 0) return "";
	if (_size == 1) return _head.getData().toString();
	
	String retStr = "";
	Node<T> cur = _head;
	while (cur != null) {
	    retStr += cur.getData() + " ";
	    cur = cur.getNext();
	}
	return retStr.substring(0, retStr.length()-1);
    }

    
    public static void main(String[] args) throws InterruptedException {
	System.out.println("initializing DLL m...");
	DLL<Integer> m = new DLL<Integer>();

	System.out.println("adding 5...");
	m.add(5);
	System.out.println(m);

	//test adding
	System.out.println("adding 7 at front: ");
	m.add(7);
	System.out.println(m);
	System.out.println("adding 8 at back: ");
	m.add(m.size(), 8);
	System.out.println(m);
	System.out.println("adding 12 at index 2: ");
	m.add(2, 12);
	System.out.println(m);
	System.out.println("m.size(): " + m.size());

	System.out.println("\nm.get(0): " + m.get(0));
	System.out.println("m.get(2): " + m.get(2));
	System.out.println("m.get( m.size()-1 ): " + m.get( m.size()-1 ));

	//test iterating
	System.out.println("\niterating through m...");
	MyIterator<Integer> iter = m.iterator();
	while (iter.hasNext())
	    System.out.println( iter.next() );

	System.out.println("\niterating through m and removing 2nd element...");
	iter = m.iterator();
	while (iter.hasNext()) {
	    if (iter.next() == m.get(2)) {
		iter.remove();
	    }
	    System.out.println( iter.next() );
	}
	System.out.println(m);

	//test prev fxnality: each should be true
	System.out.println("\ntesting prev fxnality: ");
	Node<Integer> cur = m.getHead();
	for (int i = 1; i < m.size(); i++) {
	    cur = cur.getNext();
	    System.out.println("i=" + i + ": cur.getPrev().getNext() == cur -> " + (cur.getPrev().getNext() == cur));
	}
	
	
	//test removing
	System.out.println("\nremoving from front: " + m.remove());
	System.out.println(m);
	System.out.println("removing from back: " + m.remove( m.size()-1 ));
	System.out.println(m);
	System.out.println("removing from front: " + m.remove());
	System.out.println(m);
	System.out.print("removing from front: ");
	try {
	    System.out.println( m.remove() );
	}
	catch (IndexOutOfBoundsException e) {
	    System.out.println("INDEX OUT OF BOUNDS ERROR");
	}
	System.out.println(m);
    }
}
