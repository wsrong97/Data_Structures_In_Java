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
    public void remove(int i) throws DataStructureException {
        checkIndex(i);
        if (i == 0) {
            _head = _head.next();
        } else {
            Node<T> tmp = nodeAt(i - 1);
            tmp.setNext(tmp.next().next());
        }
        _size--;
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

    public void checkIndex(int i) throws DataStructureException {
        if (i < 0 || i >= _size) {
            throw new DataStructureException("Index out of range");
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
