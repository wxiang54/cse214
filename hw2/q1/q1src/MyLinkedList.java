package q1src;
import java.util.NoSuchElementException;

/* LINKEDLIST CLASS FOR HW2 Q1 */
public class MyLinkedList<T> {
    private Node<T> _head;
    private Node<T> _tail;
    private int _size;
        
    //INNER NODE CLASS
    private static class Node<T> {
	private T _data;
	private Node<T> _next;
	
	public Node(T data) {
	    _data = data;
	    _next = null;
	}
	
	public T getData() {return _data;}
	public void setData(T data) {_data = data;}
	public Node<T> getNext() {return _next;}
	public void setNext(Node<T> next) {_next = next;}
    } //end inner node class


    //constructor
    public MyLinkedList() {
	_head = _tail = null;
	_size = 0;
    }

    //getters/setters
    public T getFirst() {return _head.getData();}
    public T getLast() {return _tail.getData();}
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


    public void addFirst(T item) {
	Node<T> newNode = new Node<T>(item);
	newNode.setNext(_head); //if list empty, next is null
	_head = newNode;
	if (_size == 0) //before size increment
	    _tail = newNode; //because only one node in LL
	_size++;
    }

    public void addLast(T item) {
	Node <T> newNode = new Node<T>(item);
	if (_size == 0)
	    _head = newNode;
	else
	    _tail.setNext(newNode); //old tail
	_tail = newNode;
	_size++;
    }
    

    //add with given index
    public void add(int index, T item) {
	if (index < 0 || _size < index)
	    throw new IndexOutOfBoundsException();
	if (index == 0) {
	    addFirst(item);
	    return;
	}
	if (index == _size) {
	    addLast(item);
	    return;
	}
	
	//now shouldn't go out of bounds
	Node<T> cur = _head;
	for (int i = 0; i < index-1; i++)
	    cur = cur.getNext(); //stop at node before insertion pt

	Node<T> newNode = new Node<T>(item);
	newNode.setNext(cur.getNext());
	cur.setNext(newNode);
	_size++;
    }

    

    public T removeFirst() {
	if (_size <= 0) throw new IndexOutOfBoundsException();
	if (_size == 1) _tail = null; //because LL will be empty after remove
	T ret = _head.getData();
	_head = _head.getNext();
	_size--;
	return ret;
    }

    public T removeLast() {
	if (_size <= 0) throw new IndexOutOfBoundsException();
	T ret = _tail.getData();
	Node<T> cur = _head;
	for (int i = 0; i < _size-2; i++)
	    cur = cur.getNext(); //stop at node before node to be removed
	cur.setNext(null);
	_tail = cur;
	if (_size == 1) _head = null; //because LL will be empty after remove
	_size--;
	return ret;
    }

    //remove at index
    public T remove(int index) {
	if (index < 0 || index >= _size)
	    throw new IndexOutOfBoundsException();
	if (index == 0)
	    return removeFirst();
	if (index == _size-1)
	    return removeLast();
	
	T ret;
	Node<T> cur = _head;
	for (int i = 0; i < index-1; i++)
	    cur = cur.getNext(); //stop at node before insertion pt
	ret = cur.getNext().getData();
	cur.setNext( cur.getNext().getNext() ); //if removing last item, will be null
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

    
    public static void main(String[] args) {
	System.out.println("initializing LinkedList m...");
	MyLinkedList<Integer> m = new MyLinkedList<Integer>();

	System.out.println("\nadding 5 at front...");
	m.addFirst(5);
	System.out.println("\tm: [" + m + "]");

	//test adding
	System.out.println("adding 8 at back: ");
	m.addLast(8);
	System.out.println("\tm: [" + m + "]");
	System.out.println("adding 12 at index 2: ");
	m.add(2, 12);
	System.out.println("\tm: [" + m + "]");
	System.out.println("m.size(): " + m.size());


	System.out.println("\nm.getFirst(): " + m.getFirst());
	System.out.println("m.get(1): " + m.get(1));
	System.out.println("m.getLast(): " + m.getLast());

	
	//test removing
	System.out.println("\nm.removeFirst(): " + m.removeFirst());
	System.out.println("\tm: [" + m + "]");
	System.out.println("m.removeLast(): " + m.removeLast());
	System.out.println("\tm: [" + m + "]");


	System.out.println("pre-empty m.removeLast(): " + m.removeLast());
	System.out.println("\tm: [" + m + "]");
	System.out.println("adding 3 to end of empty list...");
	m.addLast(3);
	System.out.println("\tm: [" + m + "]");
	System.out.println("pre-empty m.removeFirst(): " + m.removeFirst());
	System.out.println("\tm: [" + m + "]");

	
	System.out.print("removing from empty: ");
	try {
	    System.out.println( m.removeFirst() );
	}
	catch (IndexOutOfBoundsException e) {
	    System.out.println("INDEX OUT OF BOUNDS ERROR");
	}
	System.out.println("\tm: [" + m + "]");
    }
}
