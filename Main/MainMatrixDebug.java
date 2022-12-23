package Main;

import ErrorHandling.DataStructureException;
import Matrix.DenseMatrix;
import List.ArrayList;
import List.LinkedList;
public class MainMatrixDebug {
    public static void main(String[] argv) throws DataStructureException {
        int rows=4;
        int cols=5;
        DenseMatrix<Double,ArrayList<Double>> mat= new DenseMatrix<Double, ArrayList<Double>>(ArrayList::new,rows,cols);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat.set(i,j,(double)(i*cols+j+1));
            }
        }
        System.out.println("mat=\n"+mat);
        System.out.println("mat.max="+mat.max());
        System.out.println("mat.min="+mat.min());
        System.out.println("mat.maxPos="+mat.maxPos());
        System.out.println("mat.minPos="+mat.minPos());
        System.out.println("mat.maxId="+mat.maxId());
        System.out.println("mat.minId="+mat.minId());
        DenseMatrix<Double,ArrayList<Double>> mat1= new DenseMatrix<Double, ArrayList<Double>>(mat);
        mat1.set(3,3,Math.PI);
        mat1.set(1,2,10000.);
        mat1.set(2,4,-10000.);
        System.out.println("mat1=\n"+mat1);
        System.out.println("mat1.max="+mat1.max());
        System.out.println("mat1.min="+mat1.min());
        System.out.println("mat1.maxPos="+mat1.maxPos());
        System.out.println("mat1.minPos="+mat1.minPos());
        System.out.println("mat1.maxId="+mat1.maxId());
        System.out.println("mat1.minId="+mat1.minId());
    }
}
