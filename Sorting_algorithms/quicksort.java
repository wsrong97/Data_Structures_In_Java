package Sorting_algorithms;

public class quicksort {
    public static void quick_sort(int[] arr, int start, int end) {

        if(start< end){//base case check, when start=end, only one element, it's in its place.
            int partition_index = partition(arr, start, end);//after each partition, the element at the
            //partition_index is in its place. Now we need to sort the left and the right array using recursive call.
            quick_sort(arr, start, partition_index - 1);
            quick_sort(arr, partition_index + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {

        int pivot = arr[end];  //partition using the last element of the array
        int small_e = start; // small_end represents the start of the larger half part.

        /**
         * Note that in the textbook, there is an easier way to write this algorithm, as follows.
         * Notice the reason why arr[i]<= pivot we can always do swap is that if there is no larger part, the swap
         * is swapping itself with itself. Is there a need to separate cases when there is a larger part and when there
         * is not? Maybe in practice this issue has been considered( in the standard library), because
         * we don't need to do this useless swapping every time(extra cost) before the advent of the larger
         * part. But this algorithm written below is more concise.
         */
        for (int i = start; i < end; i++) {

            if (arr[i] <= pivot) {

                //swap arr[small_e] with arr[i]
                int tmp = arr[i];
                arr[i] = arr[small_e];
                arr[small_e] = tmp;
                small_e += 1;
            }

        }
            //return partition index(pivot position after partitioning. )
            int tmp1 = arr[small_e];
            arr[end] = tmp1;
            arr[small_e] = pivot;
            return small_e;
    }

}

//
//        int pivot = arr[end];
//        int i = (start - 1);
//
//        for (int j = start; j < end; j++) {
//            if (arr[j] <= pivot) {
//                i++;
//
//                int swapTemp = arr[i];
//                arr[i] = arr[j];
//                arr[j] = swapTemp;
//            }
//        }
//
//        int swapTemp = arr[i + 1];
//        arr[i + 1] = arr[end];
//        arr[end] = swapTemp;
//
//        return i + 1;
//    }