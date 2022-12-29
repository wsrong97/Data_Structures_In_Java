package MATRIX;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void addition_test(){
        //failure test

        int[][] A= {{1,3,4,5,6},{-2,-5,-4,1,4},{6,5,4,2,1}};
        int[][] B= {{1,5,8,7,9},{-2,4,8,5,9},{4,5,7,8,9}};

        //success test
        int row_n = A.length;
        int col_n = A[0].length;
        int[][] result = Matrix.matrix_addition(A,B);
        for (int i=0; i <row_n; i++){
            for (int j=0; j<col_n;j++){
                assertEquals(A[i][j] + B[i][j], result[i][j]);
                System.out.print(" "+ result[i][j] + " ");
            }
            System.out.println();
        }
    }
}