package List;

import ErrorHandling.DataStructureException;

public class LinkedList<T> implements List<T> {
    private class Node<E> {
        public Node() {
            _valueInitialized = false;
            _nextInitialized = false;
        }

        public Node(E value) {
            _value = value;
            _next = new Node<E>();
            _valueInitialized = true;
            _nextInitialized = false;
        }

        public Node(E value, Node<E> next) {
            _value = value;
            _next = next;
            _valueInitialized = true;
            _nextInitialized = true;
        }

        public Node<E> next() throws DataStructureException {
            if (!_nextInitialized) throw new DataStructureException("Next Node not Initialized.");
            return _next;
        }

        public E value() throws DataStructureException {
            if (!_valueInitialized) throw new DataStructureException("Value not Initialized.");
            return _value;
        }

        public void setValue(E value) {
            _value = value;
            _valueInitialized = true;
        }

        public void setNext(Node<E> node) {
            _next = node;
            _nextInitialized = true;
        }

        public boolean initialized() {
            return _nextInitialized && _valueInitialized;
        }

        private E _value;
        private boolean _valueInitialized;
        private boolean _nextInitialized;
        private Node<E> _next;
    }

    public LinkedList() {
        _size = 0;
        _head = new Node<T>();
    }

    public LinkedList(LinkedList<T> linkedList) {
        _head = linkedList._head;
        _size = linkedList._size;
    }

    private int _size;
    private Node<T> _head;

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    private Node<T> nodeAt(int i) throws DataStructureException {
        Node<T> tmp = _head;
        for (int j = 0; j < i; j++) {
            tmp = tmp.next();
        }
        return tmp;
    }

    @Override
    public T get(int i) throws DataStructureException {
        checkIndex(i);
        return nodeAt(i).value();
    }

    @Override
    public void set(int i, T element) throws DataStructureException {
        checkIndex(i);
        Node<T> tmp = _head;
        for (int j = 0; j < i; j++) {
            tmp = tmp.next();
        }
        tmp.setValue(element);
    }

    @Override
    public void insert(int i, T element) throws DataStructureException {
        if (i < 0 || i > _size) throw new DataStructureException("Index out of range.");
        if(i==0){
            _head = new Node<T>(element, _head);
        }
        else{
            Node<T> tmp = nodeAt(i - 1);
            Node<T> toBeInserted = new Node<T>(element, tmp.next());
            tmp.setNext(toBeInserted);
        }
        _size++;
    }

    @Override
    public T remove(int i) throws DataStructureException {
        checkIndex(i);
        T res;
        if (i == 0) {
            _head = _head.next();
            res=_head.value();
        } else {
            Node<T> tmp = nodeAt(i - 1);
            res=tmp.next().value();
            tmp.setNext(tmp.next().next());
        }
        _size--;
        return res;
    }

    @Override
    public int find(T e) throws DataStructureException {
        Node<T> tmp = _head;
        for (int i = 0; i < _size; i++) {
            if (tmp.value().equals(e)) {
                return i;
            }
            tmp = tmp.next();
        }
        return -1;
    }

    public void append(T element) throws DataStructureException {
        insert(_size, element);
    }

    @Override
    public LinkedList<Integer> findAll(T e) throws DataStructureException {
        LinkedList<Integer> res = new LinkedList<Integer>();
        Node<T> tmp = _head;
        for (int i = 0; i < _size; i++) {
            if (tmp.value().equals(e)) {
                res.append(i);
            }
            tmp = tmp.next();
        }
        return res;
    }

    @Override
    public void resize(int size) throws DataStructureException {
        if(size==0) throw new DataStructureException("Resize cannot be 0");
        if(_size>size){
            System.out.println("The input size is less than the number of elements in the list! Some data will be lost");
            Node<T> tmp=nodeAt(size);
            tmp.setNext(new Node<T>());
            _size = size;
        }
        else{
            int tmpSize=_size;
            for (int i = 0; i < size - tmpSize; i++) {
                append((T)new Object());
            }
        }
    }
    public void checkIndex(int i) throws DataStructureException {
        if (i < 0 || i >= _size) {
            throw new DataStructureException("Index "+i+" out of range ("+_size+")");
        }
    }

    public String toString() {
        if (isEmpty()) return "";
        String res = new String();
        Node<T> tmp = _head;
        for (int i = 0; i < _size - 1; i++) {
            try {
                res += tmp.value().toString() + " ";
            } catch (DataStructureException e) {
                throw new RuntimeException(e);
            }
            try {
                tmp = tmp.next();
            } catch (DataStructureException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            res += tmp.value().toString();
        } catch (DataStructureException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
