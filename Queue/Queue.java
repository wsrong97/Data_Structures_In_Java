package Queue;

import ErrorHandling.DataStructureException;
import List.List;
import Queue.Queue;

import java.util.function.Supplier;
public class Queue<T, L extends List<T>> {
    public Queue(Supplier<L> supplier){
        _list= supplier.get();
    }
    public Queue(L list){
        _list=list;
    }
    public Queue(Queue<T,L> queue){
        _list=queue._list;
    }
    private final L _list;
    public void push(T element) throws DataStructureException {
        _list.insert(_list.size(),element);
    }
    public T pop() throws DataStructureException {
        return _list.remove(0);
    }
    public boolean isEmpty(){
        return _list.isEmpty();
    }
    public int size(){
        return _list.size();
    }
    public int find(T element) throws DataStructureException {
        return _list.find(element);
    }
    public List<Integer> findAll(T element) throws DataStructureException{
        return _list.findAll(element);
    }

    @Override
    public String toString() {
        return _list.toString();
    }
}
