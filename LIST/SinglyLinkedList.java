package LIST;

public class SinglyLinkedList<E> implements ListADT<E> {


    private int size=0;
    private ListNode head;
    private class ListNode{
        ListNode next;
        E element;
        ListNode(ListNode node, E t){
            next = node;
            element = t;
//            size++;
        }
        ListNode get_nextNod(){
            return this.next;
        }
        void setNext(ListNode next){
            this.next= next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        if(i<0|| i>size-1){
        throw new IndexOutOfBoundsException("Illegal Index: "+ i);
        }
        else{
            ListNode tmp = get_helper(i);
            return tmp.element;
            }

        }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        if(i<0|| i>size-1){
            throw new IndexOutOfBoundsException("Illegal Index: "+ i);
        }
        else{
            ListNode L = get_helper(i);
            E temp = L.element;
            L.element = e;
            return temp;
        }
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            if (i!=0){
                throw new IndexOutOfBoundsException("Illegal Index: "+ i);
            }
            else {
                head = new ListNode(null, e);
                size++;
            }
        }
        else {// not empty
            if(i<0|| i>size){
                throw new IndexOutOfBoundsException("Illegal Index: "+ i);
            }
            else{
                ListNode prev;
                ListNode ith = new ListNode(null,e);
                ListNode aft;
                if (i==0){
                    aft = get_helper(i);
                    head = ith;
                    ith.setNext(aft);
                }
                if(i==size){
                    prev = get_helper(i-1);
                    prev.setNext(ith);
                    ith.setNext(null);
                }
                if (i>0 && i < size){
                    prev= get_helper(i-1);
                    aft= get_helper(i);
                    prev.setNext(ith);
                    ith.setNext(aft);
                }
                size++;
            }
        }
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        if(i<0|| i>size-1){
            throw new IndexOutOfBoundsException("Illegal Index: "+ i);
        }
        else {
            if(i==0){
                E tmp = head.element;
                head = head.get_nextNod();
                return tmp;
            }
            if(i==size-1){
                E tmp = get_helper(i).element;
                ListNode pre= get_helper(i-1);
                pre.setNext(null);
                return tmp;
            }
            // remove in the middle
            E tmp = get_helper(i).element;
            ListNode pre= get_helper(i-1);
            ListNode aft= get_helper(i+1);
            pre.setNext(aft);
            return tmp;
            }
        }


    private ListNode get_helper(int i){// return the ith ListNode
        if (i != 0) {
            ListNode tmp = head.get_nextNod();
            for (int j = 0; j < i-1; j++) {
                tmp = tmp.get_nextNod();
            }
            return tmp;
        }
        else{
            return head;
        }
    }

}

