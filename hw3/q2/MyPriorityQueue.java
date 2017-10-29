//heap implementation of priority queue
public class MyPriorityQueue {
    
    private MaxHeap _heap;

    public MyPriorityQueue() {
	_heap = new MaxHeap();
    }

    public MyPriorityQueue( int capacity ) {
	_heap = new MaxHeap(capacity);
    }

    public void enqueue( int item ) {
	_heap.insert(item);
    }

    public int dequeue() {
	return _heap.deleteMax();
    }

    @Override
    public String toString() {
	return _heap.toString();
    }
}
