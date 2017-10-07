package q2src;
import java.util.NoSuchElementException;

public interface MyIterator<T> {
    public boolean hasNext();
    public T next() throws NoSuchElementException;
    public void remove() throws IllegalStateException, UnsupportedOperationException;

    //new optimization method
    public T peekNext() throws NoSuchElementException;
}
