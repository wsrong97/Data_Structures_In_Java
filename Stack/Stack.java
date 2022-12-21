package Stack;

import ErrorHandling.DataStructureException;
import List.List;

public class Stack<T,L extends List<T>>{
    public Stack(){
        _list=(L) new Object();
    }
    public Stack(int size){
        _list=(L) new Object();
    }
    public Stack(L list){
        _list=list;
    }
    public Stack(Stack<T,L> stack){
        _list=stack._list;
    }
    private L _list;
    public void push(T element) throws DataStructureException {
        _list.insert(0,element);
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
}
