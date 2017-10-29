import java.util.NoSuchElementException;
import java.util.Random;

public class MinHeap {
    private int[] _data;
    private int _size;
    private int _capacity;
    private static final int DEFAULT_CAPACITY = 100;

    public MinHeap() {
	this(DEFAULT_CAPACITY); 
    }
    
    public MinHeap(int capacity) {
	_data = new int[capacity];
	_capacity = capacity;
	_size = 0;
    }

    public boolean isEmpty() { return _size == 0; }
    public boolean isFull() { return _size == _capacity; }
    public int size() { return _size; }
    
    public void insert(int item) {
	int pos;
	if (isFull()) throw new IndexOutOfBoundsException();
	_data[_size] = item;
	pos = _size;
	while (pos > 0 && _data[pos] < _data[(pos-1) / 2]) {
	    swap(pos, (pos-1) / 2);
	    pos = (pos-1) / 2;
	}
	_size++;
    }

    public int delete() {
	int ret;
	if (isEmpty()) throw new NoSuchElementException();
	ret = _data[0];
	_data[0] = _data[_size-1];
	_size--;
	heapify();
	return ret;
    }

    public void heapify() {
	int pos = 0; //root
	int childPos;
	while (pos*2+1 < _size) { //if pos HAS left child
	    childPos = pos * 2 + 1;
	    if (childPos < _size-1 && _data[childPos+1] < _data[childPos])
		childPos++;
	    if (_data[pos] <= _data[childPos])
		return;
	    swap(pos, childPos);
	    pos = childPos;
	}
    }

    public void swap(int p1, int p2) {
	int tmp = _data[p1];
	_data[p1] = _data[p2];
	_data[p2] = tmp;
    }

    
    public static void main(String[] args) {
	MinHeap h = new MinHeap(20);
	System.out.println("inserting 10 random numbers");
	Random rand = new Random();
	for (int i = 1; i <= 10; i++) {
	    h.insert( rand.nextInt(100) + 1 );
	}

	System.out.println("deleting all...");
	while (!h.isEmpty()) {
	    System.out.println( h.delete() );
	}
    }
}
