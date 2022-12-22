package Stack;

import ErrorHandling.DataStructureException;
import List.List;

import java.util.function.Supplier;

public class Stack<T,L extends List<T>>{
    public Stack(Supplier<L> supplier){
        _list= supplier.get();
    }
    public Stack(L list){
        _list=list;
    }
    public Stack(Stack<T,L> stack){
        _list=stack._list;
    }
    private final L _list;
    public void push(T element) throws DataStructureException {
        _list.insert(_list.size(),element);
    }
    public T pop() throws DataStructureException {
        return _list.remove(_list.size()-1);
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
