package QUEUE;

public interface QueueADT<E> {
    void enqueue(E e);
    E dequeue();
    E first();
    int size();
    boolean isEmpty();

}
