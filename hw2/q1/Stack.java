import java.util.EmptyStackException;

//STACK INTERFACE
public interface Stack<T> {
    public void push(T n);
    public T pop() throws EmptyStackException;
    public T peek() throws EmptyStackException;
    public boolean isEmpty();
}