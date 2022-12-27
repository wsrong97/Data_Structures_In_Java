package STACK;
import LIST.ArrayList;

public class Stack_byArrayList<E> implements StackADT<E> {
    ArrayList<E> arr_list = new ArrayList<>();

    @Override
    public void push(E e) {
        arr_list.add(arr_list.size(),e);
   }

    @Override
    public E pop() {
        if (isEmpty()){
            return null;
        }
        else{
            return arr_list.remove(arr_list.size()-1);
        }
    }

    @Override
    public E top() {
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
