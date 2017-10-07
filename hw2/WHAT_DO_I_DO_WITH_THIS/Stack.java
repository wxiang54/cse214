package q3src;
import java.util.EmptyStackException;

//STACK INTERFACE FOR Q3
public interface Stack<T> {
    public void push(T n);
    public T pop() throws EmptyStackException;
    public T peek() throws EmptyStackException;
    public boolean isEmpty();
}
