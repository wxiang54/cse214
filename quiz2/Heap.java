//maxheap
import java.util.Arrays;

public class Heap {
    private int[] _data;
    private int _size;
    private int _capacity;

    public Heap() {
	_data = new int[10];
	_size = 0;
	_capacity = 10;
    }

    public void insert(int insert) {
	_data[_size] = insert;
	int curPos = _size;
	int parentPos = (curPos - 1) / 2;
	while (curPos > 0 && _data[curPos] > _data[parentPos]) {
	    swap(curPos, parentPos);
	    curPos = parentPos;
	    parentPos = (curPos - 1) / 2;
	}	
	_size++;
    }

    public int deleteMax() {
	if (isEmpty()) throw new IndexOutOfBoundsException();
	int ret = _data[0];
	_size--;
	swap(0, _size);
	int curPos = 0;
	int childPos = curPos * 2 + 1;
	while (childPos+1 <= _size &&
	       (_data[curPos] < _data[childPos]
		|| _data[curPos] < _data[childPos+1])) {
	    if (_data[childPos] < _data[childPos+1]) childPos++;
	    swap(curPos, childPos);
	    curPos = childPos;
	    childPos = curPos * 2 + 1;
	}
	return ret;
    }

    
    public void swap(int i1, int i2) {
	int tmp = _data[i1];
	_data[i1] = _data[i2];
	_data[i2] = tmp;
    }

    public boolean isEmpty() {return _size==0;}
    public boolean isFull() {return _size >= _capacity;}
    
    public String toString() {
	String ret = "[ ";
	for (int i = 0; i < _size; i++)
	    ret += _data[i] + " ";
	return ret + "]";
    }
    
    public static void main(String[] args) {
	Heap h = new Heap();

	System.out.println("\ninserting...");
	h.insert(1);
	h.insert(5);
	h.insert(7);
	h.insert(10);
	h.insert(97);
	h.insert(2);
	h.insert(98);
	h.insert(14);
	h.insert(3);
	System.out.println(h);

	System.out.println("\ndeleting...");
	for (int i = 0; i < h._size; i++) {
	    h.deleteMax();
	    System.out.println(h);
	}
    }
}
