package List;

import ErrorHandling.DataStructureException;

public class ArrayList<T> implements List<T> {
    public ArrayList(int size) {
        _size = 0;
        _length = size;
        _elements = (T[]) new Object[_length];
    }

    public ArrayList() {
        _size = 0;
        _length = 1;
        _elements = (T[]) new Object[_length];
    }

    public ArrayList(ArrayList<T> arrayList) {
        _size = arrayList._size;
        _length = arrayList._length;
        _elements = arrayList._elements;
    }

    public ArrayList(T[] data, int size){
        _size=size;
        _length=size;
        _elements=data;
    }
    private int _size;
    private int _length;
    private T[] _elements;

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public T get(int i) throws DataStructureException {
        checkIndex(i);
        return _elements[i];
    }

    @Override
    public void set(int i, T element) throws DataStructureException {
        checkIndex(i);
        _elements[i] = element;
    }

    @Override
    public void insert(int i, T element) throws DataStructureException {
        if (i < 0 || i > _size) throw new DataStructureException("Index out of range");
        if (_size + 1 >= _length) {
            _length *= 2;
            T tmp[] = (T[]) new Object[_length];
            int id = Math.min(i + 1, _size);
            for (int j = 0; j < id; j++) {
                tmp[j] = _elements[j];
            }
            for (int j = id + 1; j < _size + 1; j++) {
                tmp[j] = _elements[j - 1];
            }
            _elements = tmp;
            _elements[id] = element;
        } else {
            for (int j = _size + 1; j > i; j--) {
                _elements[j] = _elements[j - 1];
            }
            _elements[i] = element;
        }
        _size++;
    }

    @Override
    public T remove(int i) throws DataStructureException {
        checkIndex(i);
        T res = _elements[i];
        for (int j = i; j < _size - 1; j++) {
            _elements[j] = _elements[j + 1];
        }
        _size--;
        if (_size < _length / 4) {
            _length = _length / 2 + 1;
            T[] tmp = (T[]) new Object[_length];
            for (int j = 0; j < _size; j++) {
                tmp[j] = _elements[j];
            }
            _elements = tmp;
        }
        return res;
    }

    @Override
    public int find(T e) {
        for (int i = 0; i < _size; i++) {
            if (_elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public void append(T element) throws DataStructureException {
        insert(_size, element);
    }

    @Override
    public ArrayList<Integer> findAll(T e) throws DataStructureException {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < _size; i++) {
            if (_elements[i].equals(e)) {
                res.append(i);
            }
        }
        return res;
    }

    @Override
    public void resize(int size) throws DataStructureException {
        if(size==0) throw new DataStructureException("Resize cannot be 0");
        if (_size > size) {
            System.out.println("The input size is less than the number of elements in the list! Some data will be lost");
        }
        int tmpSize=Math.min(size,_size);
        T[] tmp = (T[]) new Object[size];
        for (int i = 0; i < tmpSize; i++) {
            tmp[i] = _elements[i];
        }
        _size=size;
        _elements = tmp;
    }

    public void checkIndex(int i) throws DataStructureException {
        if (i < 0 || i >= _size) {
            throw new DataStructureException("Index "+i+" out of range "+_size);
        }
    }

    public String toString() {
        if (isEmpty()) return "";
        StringBuilder res = new StringBuilder(new String());
        for (int i = 0; i < _size - 1; i++) {
            res.append(_elements[i].toString()).append(" ");
        }
        res.append(_elements[_size - 1].toString());
        return res.toString();
    }
}
