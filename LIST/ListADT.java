public interface ListADT<E> {
    /** Return the number of elements in the list */
    int size();
    boolean isEmpty();
    E get(int i);
    E set(int i, E e);
    void add(int i, E e);
    E remove(int i);
    int find(E e);
}