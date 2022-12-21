package List;

import ErrorHandling.DataStructureException;

public interface List <T>{
    public int size();
    public boolean isEmpty();
    public T get(int i) throws DataStructureException;
    public void set(int i, T element) throws DataStructureException;
    public void insert(int i, T element) throws DataStructureException;
    public void remove(int i) throws DataStructureException;
    public int find(T e);
    public ArrayList<Integer> findAll(T e) throws DataStructureException;
}