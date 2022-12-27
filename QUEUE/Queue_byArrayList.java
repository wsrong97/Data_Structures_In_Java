package QUEUE;
import LIST.ArrayList;
public class Queue_byArrayList<E> implements QueueADT<E> {

    ArrayList<E> arr_list = new ArrayList<E>();
    @Override
    public void enqueue(E e) {
        arr_list.add(0,e);
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            return null;
        }
        else{
            return arr_list.remove(arr_list.size()-1);
        }
    }

    @Override
    public E first() {
        if (isEmpty()){
            return null;
        }
        else{
            return arr_list.get(arr_list.size()-1);
        }
    }

    @Override
    public int size() {
        return arr_list.size();
    }

    @Override
    public boolean isEmpty() {
        return arr_list.isEmpty();
    }
}
