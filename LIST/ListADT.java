package LIST;
public interface ListADT<E> {
    /** Return the number of elements in the list */
    int size();
    boolean isEmpty();
    E get(int i) throws IndexOutOfBoundsException;
    E set(int i, E e) throws IndexOutOfBoundsException;
    void add(int i, E e) throws IndexOutOfBoundsException;
    E remove(int i) throws IndexOutOfBoundsException;
    int find(E e) throws IndexOutOfBoundsException;
}