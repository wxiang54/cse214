package q1src;
import java.util.NoSuchElementException;

public interface Queue<T> {
    public void enqueue(T item);
    public T dequeue() throws NoSuchElementException;
    public T peekFront() throws NoSuchElementException;
    public boolean isEmpty();
    public int size();
}
