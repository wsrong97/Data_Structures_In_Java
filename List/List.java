package List;

import ErrorHandling.DataStructureException;

public interface List <T>{
    public int size();
    public boolean isEmpty();
    public T get(int i) throws DataStructureException;
    public void set(int i, T element) throws DataStructureException;
    public void insert(int i, T element) throws DataStructureException;
    public T remove(int i) throws DataStructureException;
    public int find(T e) throws DataStructureException;
    public List<Integer> findAll(T e) throws DataStructureException;
}