package MATRIX;

import java.util.HashMap;
import java.util.Map;

public class Matrix {

//    public static int[][] matrix_multiply(int[][] A, int[][] B){
//
//
//        int[][] result;
//
//        return result;
//    }

    public static int[][] matrix_addition(int[][] A, int[][] B){

        if (add_dim_check(A, B)){
            int row_n = A.length;
            int col_n = A[0].length;
            int[][] result = new int[row_n][col_n];
            for (int i=0; i <row_n; i++){
                for (int j=0; j<col_n;j++){
                    result[i][j] = A[i][j] + B[i][j];
                }
            }
            return result;
        }
        else {
            return null;
        }
    }

    private static boolean add_dim_check(int[][] A, int[][] B) {
        //matrix check
        Map<String, Integer> dim_A;
        Map<String, Integer> dim_B;
        if (isMatrix(A) != null) {
            dim_A = isMatrix(A);
        } else {
            return false;
        }
        if (isMatrix(A) != null) {
            dim_B = isMatrix(B);
        } else {
            return false;
        }

        //dimension match
        if (dim_A.get("n_row")==dim_B.get("n_row")&&dim_A.get("n_col")==dim_B.get("n_col")) {
            return true;
        }
        else {
            return false;
        }
    }

    
    /** 
     * Whether I shall use map structure or not ?
     * */
    private static Map<String,Integer> isMatrix(int [][] X){
        int n_row = X.length;
        boolean value = false;
//        int col_n = X[0].length;
        for (int i =1; i<n_row;i++){
            if (X[i].length!= X[0].length){
                return null;
            }
        }
        int n_col = X[0].length;
        Map<String, Integer> dimension = new HashMap<>();
        dimension.put("n_row",n_row);
        dimension.put("n_col",n_col);
        return dimension;
        }

}
