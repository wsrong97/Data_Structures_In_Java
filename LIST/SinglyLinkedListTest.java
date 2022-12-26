package LIST;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    @Test
    void linked_list_add_test_plus_size_test() {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<Integer>();
        //failure test
        Exception exception = assertThrows(Exception.class, () -> linkedList.add(1, 0));
        assertEquals("Illegal Index: 1", exception.getMessage());
        //empty add
        assertTrue(linkedList.isEmpty());
        linkedList.add(0, 10);
        assertEquals(10, linkedList.get(0));
        //non-empty add at the front or end
        linkedList.add(1, 11);
        linkedList.add(2, 12);
        linkedList.add(3, 13);
        assertEquals(11, linkedList.get(1));
        assertEquals(12, linkedList.get(2));
        assertEquals(13, linkedList.get(3));
        linkedList.add(0, 88);
        int[] tmp = {88, 10, 11, 12, 13};
        for (int i = 0; i < 5; i++) {
            assertEquals(tmp[i], linkedList.get(i));
        }
        //non-empty add in between
        linkedList.add(3, 99);
        int[] tmp2 = {88, 10, 11, 99, 12, 13};
        for (int i = 0; i < 6; i++) {
            assertEquals(tmp2[i], linkedList.get(i));
        }
    }

    @Test
    void remove_test(){
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<Integer>();
        Exception exception = assertThrows(Exception.class, () -> linkedList.remove(0));
        assertEquals("Illegal Index: 0", exception.getMessage());
        linkedList.add(0, 10);
        linkedList.add(1,11);
        linkedList.add(2,12);
        linkedList.add(3,13);
        linkedList.add(0,88);
        linkedList.add(3,99);
        linkedList.remove(0);
        int[] tmp = {10,11,99,12,13};
        for(int i = 0; i < 4; i++){
            assertEquals(tmp[i],linkedList.get(i));
        }
        linkedList.remove(1);
        int[] tmp1 = {10,99,12,13};
        for(int i = 0; i < 3; i++){
            assertEquals(tmp1[i],linkedList.get(i));
        }
        linkedList.remove(3);
        int[] tmp2 = {10,99,12};
        for(int i = 0; i < 2; i++){
            assertEquals(tmp2[i],linkedList.get(i));
        }
    }

    @Test
    void set_test(){
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        Exception exception = assertThrows(Exception.class, () -> linkedList.set(0,1));
        assertEquals("Illegal Index: 0", exception.getMessage());
        linkedList.add(0, 10);
        linkedList.add(1,11);
        linkedList.add(2,12);
        linkedList.add(3,13);
        linkedList.add(0,88);
        linkedList.add(3,99);
        linkedList.set(2,1258);
        assertEquals(1258,linkedList.get(2));
        int[] tmp = {88,10,1258,99,12,13};
        for(int i = 0; i < 5; i++){
            assertEquals(tmp[i],linkedList.get(i));
        }
        linkedList.set(3,1314);
        assertEquals(1314, linkedList.get(3));
    }
    
}
