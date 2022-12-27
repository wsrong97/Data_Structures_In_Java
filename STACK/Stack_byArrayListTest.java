package STACK;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Stack_byArrayListTest {

    @Test
    void push_top_pop() {
        Stack_byArrayList<Integer> stack1 = new Stack_byArrayList();
        assertEquals(null,stack1.pop());
        assertEquals(null,stack1.top());
        for (int i =0; i<10;i++){
            stack1.push(i);
            assertEquals(i,stack1.top());
            assertEquals(i,stack1.pop());
        }
    }

    @Test
    void size() {
        Stack_byArrayList<Integer> stack1 = new Stack_byArrayList();
        assertEquals(0,stack1.size());
        for (int i =0; i<10;i++){
            stack1.push(i);
        }
        assertEquals(10,stack1.size());
    }

    @Test
    void isEmpty() {
        Stack_byArrayList<Integer> stack1 = new Stack_byArrayList();
        assertEquals(true, stack1.isEmpty());
        stack1.push(1);
        assertEquals(false,stack1.isEmpty());
    }
}