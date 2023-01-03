package Sorting_algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class quicksortTest {

    @Test
    void quick_sort_test(){
        int[] arr = {1,4,2,6,8,9,-1,3};
        int[] arr_copy = {1,4,2,6,8,9,-1,3};

        System.out.println("Before"+Arrays.toString(arr));
        quicksort.quick_sort(arr,0,7);
        System.out.println("After"+Arrays.toString(arr));
        Arrays.sort(arr_copy);
        System.out.println("Test_out"+Arrays.toString(arr));
        assertArrayEquals(arr_copy,arr);
    }
}