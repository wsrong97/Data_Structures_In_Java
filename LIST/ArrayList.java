package LIST;
//package LIST;
public class ArrayList<E> implements ListADT<E> {

    private static final int CAPACITY = 16;
    private int size;
    private int length;
    private E[] data;

    private void expand(){
        /** create a new array(double size) and copy the original element into the new array.
         * Pass the new array's reference to this.data.
         * */
        length = 2* length;
        E[] temp = (E[])new Object[length];

        for (int i = 0; i<= size-1; i++){
            temp[i]= data[i];
        }
        this.data = temp;
    };

    private void shrink(){
        /** create a new array(double size) and copy the original element into the new array.
         * Pass the new array's reference to this.data.
         * */
        length = length/2;
        E[] temp = (E[])new Object[length];
        for (int i = 0; i<= size-1; i++){
            temp[i]= data[i];
        }
        this.data = temp;
    };

    private void move(int j, boolean right){
        //** move the elements start at j one to the right
        if (right){
            for (int i= size-1 ; i>=j; i--){
                data[i+1]=data[i];
            }
//            E next = data[j];
//            E tmp;
//            for(int i=0; i< size-j+1; i++){
//                if(j+i+1<=size-1){
//                    tmp = data[j+i+1];
//                    data[j+i+1] = next;
//                    next = tmp;
//                }
//            }
        }
        else{
            for (int i =0; i< size-j; i++){
                if (j+i<size){
                    data[j+i]= data[j+i+1];
                }
            }
        }
    };

    /** Class constructor*/
    public ArrayList(){
        data = (E[])new Object[CAPACITY];
        length = CAPACITY;
        size = 0;
    };
    public ArrayList(int capacity){
        data = (E[])new Object[capacity];
        length = capacity;
        size = 0;
    };

    @Override
    public int size(){ return size;};
    public boolean isEmpty(){
        if(size!=0){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        if (i > size - 1 || i < 0) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
        return data[i];
    }

    ;


    public E set(int i, E e) throws IndexOutOfBoundsException{
        if(i> size -1 || i <0){
             throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
        else{
            E tmp = data[i];
            data[i] = e;
            return tmp;
        }
    };

    public void add(int i, E e) throws IndexOutOfBoundsException{
        if(size == length){
            /** if ever size reaches length, we need to create a larger array
             * and move the elements in the original array into the new array
             * and then consider adding
             * */
            expand();
        }
        if(size ==0){
            if(i!=0){
                throw new IndexOutOfBoundsException("Illegal index: " + i);
            }
            else {
                data[0] = e;
                size +=1;
            }
        }//make sure size can't be less than 0 in remove;
        else{
            if(i > size || i < 0){
                throw new IndexOutOfBoundsException("Illegal index: " + i);
            }
            else{//i>=0
                //not adding at the end
                //move the element starting at index i towards right one block
                if(i!=size){
                    move(i,true);
                }
                data[i]= e;
                size +=1;
            }
        }
        }

    public E remove(int i){
        if(i> size -1 || i <0 || size ==0){
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
        E tmp = data[i];
        move(i, false);
        if(size -1 < length/4&& length>16){// shrink only 1/4 and length >16
            shrink();
        }
        size--;
        return tmp;
    }

//    @Override
//    public int find(E e) throws IndexOutOfBoundsException {
//        return 0;
//    }

    ;

//    int find(E e);
}
