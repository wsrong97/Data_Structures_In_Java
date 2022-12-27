package QUEUE;

import STACK.Stack_byArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Queue_byArrayListTest {

    @Test
    void enqueue_dequeue_first() {
        Queue_byArrayList<Integer> queue1 = new Queue_byArrayList<>();
        assertEquals(null,queue1.dequeue());
        assertEquals(null,queue1.first());
        for (int i =0; i<10;i++){
            queue1.enqueue(i);
            assertEquals(0,queue1.first());
//            System.out.println(i+"th"+"is"+queue1.first());
        }
        for (int i =0; i<10;i++) {
            assertEquals(i, queue1.dequeue());
        }
    }

    @Test
    void size() {
        Queue_byArrayList<Integer> queue1 = new Queue_byArrayList();
        assertEquals(0,queue1.size());
        for (int i =0; i<10;i++){
            queue1.enqueue(i);
        }
        assertEquals(10,queue1.size());
    }


    @Test
    void isEmpty() {
        Queue_byArrayList<Integer> queue1 = new Queue_byArrayList();
        assertEquals(true, queue1.isEmpty());
        queue1.enqueue(1);
        assertEquals(false,queue1.isEmpty());
    }
}