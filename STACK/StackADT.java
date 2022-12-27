package STACK;

public interface StackADT<E> {

    void push(E e);
    E pop();
    E top();
    int size();
    boolean isEmpty();

}
