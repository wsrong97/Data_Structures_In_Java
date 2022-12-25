package LIST;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void array_empty_adding_failure_test(){
        var arr_list= new ArrayList<String>();
//        assertEquals("Illegal Illegal index: 1",arr_list.add(1,"A"));
        Exception exception = assertThrows(Exception.class, () -> arr_list.add(1,"A"));
        assertEquals("Illegal index: 1", exception.getMessage());
    }

    @Test
    void array_empty_add_success_test(){
        var arr_list = new ArrayList<String>();
        arr_list.add(0,"A");
        arr_list.add(1,"B");
        assertEquals(2,arr_list.size());
        assertEquals("A",arr_list.get(0));
        assertEquals("B",arr_list.get(1));
//        System.out.println(arr_list.get(0));
//        System.out.println(arr_list.get(1));
        arr_list.add(1,"C");
        arr_list.add(2,"D");
        assertEquals("C",arr_list.get(1));
        assertEquals("D",arr_list.get(2));
        assertEquals("B",arr_list.get(3));
        assertEquals(4,arr_list.size());

    }
    @Test
    void array_add_expand_test(){
        var arr_list = new ArrayList<String>();
        int i=0;
        for (char alphabet = 'a'; alphabet <= 'z';alphabet++){
            arr_list.add(i,Character.toString(alphabet));
            System.out.println(arr_list.get(i));
            assertEquals(Character.toString(alphabet), arr_list.get(i));
            i++;
        }
        assertEquals(26, arr_list.size());
    }

    @Test
    void array_remove_test(){
        var arr_list = new ArrayList<String>();
        //error
        //size =0
        Exception exception = assertThrows(Exception.class, () -> arr_list.remove(1));
        assertEquals("Illegal index: 1", exception.getMessage());
        //i>size -1
        arr_list.add(0,"A");
        exception = assertThrows(Exception.class, () -> arr_list.remove(1));
        assertEquals("Illegal index: 1", exception.getMessage());
        //success
        assertEquals("A",arr_list.remove(0));
    }

    @Test
    void array_get_test(){
        var arr_list = new ArrayList<String>();
        //failure
        Exception exception = assertThrows(Exception.class, () -> arr_list.get(-3));
        assertEquals("Illegal index: -3", exception.getMessage());
        //success
        arr_list.add(0,"P");
        assertEquals("P", arr_list.get(0));
        exception = assertThrows(Exception.class, () -> arr_list.get(1));
        assertEquals("Illegal index: 1", exception.getMessage());
    }

    @Test
    void array_set_test(){
        var arr_list = new ArrayList<String>();
        //failure
        Exception exception = assertThrows(Exception.class, () -> arr_list.set(-3,"G"));
        assertEquals("Illegal index: -3", exception.getMessage());
        //success
        arr_list.add(0,"P");
        assertEquals("P", arr_list.set(0,"g"));
        assertEquals("g",arr_list.get(0));
        //failure
        exception = assertThrows(Exception.class, () -> arr_list.set(1,"F"));
        assertEquals("Illegal index: 1", exception.getMessage());
    }
}