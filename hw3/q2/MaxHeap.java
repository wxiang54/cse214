import java.util.NoSuchElementException;

//array implementation of max heap
public class MaxHeap {
    
    private int[] _data;
    private int _size;
    private int _capacity;
    private final static int DEFAULT_CAPACITY = 127;
    
    public MaxHeap() {
	this(DEFAULT_CAPACITY);
    }

    public MaxHeap(int capacity) {
	_capacity = capacity;
	_size = 0;
	_data = new int[_capacity];
    }

    public void insert(int item) {
	if ( isFull() ) throw new IllegalStateException();
	_data[_size] = item; //insert at leaf

	//swap with parent until parent > item or reached root
	int curPos = _size;
	int parentPos = (curPos - 1) / 2;
	while (curPos > 0 && _data[curPos] > _data[parentPos]) {
	    swap(curPos, parentPos);
	    curPos = parentPos;
	    parentPos = (curPos - 1) / 2;
	}
	_size++;
    }

    public void swap(int i1, int i2) {
	_data[i1] = _data[i1] ^ _data[i2];
	_data[i2] = _data[i1] ^ _data[i2];
	_data[i1] = _data[i1] ^ _data[i2];
    }
    
    public int deleteMax() {
	if ( isEmpty() ) throw new NoSuchElementException();
	int ret = _data[0]; //item to be deleted
	_size--;
	_data[0] = _data[_size]; //swap last with root
	int curPos = 0;
	int childPos = 1;
	while (childPos < _size) {
	    if (childPos+1 < _size && _data[childPos] < _data[childPos+1])
		childPos++;
	    if (_data[childPos] > _data[curPos])
		swap(childPos, curPos);
	    curPos = childPos;
	    childPos = 2 * curPos + 1;
	}
	return ret;
    }

    public boolean isEmpty() { return _size == 0; }
    public boolean isFull() { return _size == _capacity; }
    
    @Override
    public String toString() {
	String retStr = "[ ";
	for (int i = 0; i < _size; i++)
	    retStr += _data[i] + " ";
	return retStr + "]";
    }
    
    public static void main(String[] args) {
	System.out.println("\nTesting MaxHeap...");
	MaxHeap mh = new MaxHeap();

	System.out.println("inserting 1, 7, 2, 99, 99:");
	mh.insert(1);
	System.out.println(mh);
	mh.insert(7);
	System.out.println(mh);
	mh.insert(2);
	System.out.println(mh);
	mh.insert(99);
	System.out.println(mh);
	mh.insert(99);
	System.out.println(mh);

	System.out.println("Deleting all:");
	while (!mh.isEmpty()) {
	    mh.deleteMax();
	    System.out.println(mh);
	}
    }
}
