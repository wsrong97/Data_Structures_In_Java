package Main;

import ErrorHandling.DataStructureException;
import Matrix.DenseMatrix;
import List.ArrayList;
import List.LinkedList;

import java.lang.reflect.InvocationTargetException;

public class MainMatrixDebug {
    public static void main(String[] argv) throws DataStructureException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int rows=4;
        int cols=5;
        DenseMatrix<Double> mat= new DenseMatrix<Double>(rows,cols);
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
        DenseMatrix<Double> mat1= new DenseMatrix<Double>(mat);
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
        mat1.setAll(10.);
        System.out.println("mat1=\n"+mat1);
        DenseMatrix<Double> mat2=mat1.sub(5.);
        System.out.println("mat2=\n"+mat2);
        mat2=mat2.add(3.);
        System.out.println("mat2=\n"+mat2);
        DenseMatrix<Double> mat3=mat1.add(mat2);
        System.out.println("mat3=\n"+mat3);
        DenseMatrix<Double> mat4=mat3.mul(0.5);
        System.out.println("mat4=\n"+mat4);

        DenseMatrix<Double> mat5=new DenseMatrix<Double>(new ArrayList<Double>(new Double[]{1., 2., 3., 4., 5., 6.},6),2,3);
        System.out.println("mat5=\n"+mat5);
        DenseMatrix<Double> mat6=new DenseMatrix<Double>(new ArrayList<Double>(new Double[]{1., 2., 3., 4., 5., 6.},6),3,2);
        System.out.println("mat6=\n"+mat6);
        DenseMatrix<Double> mat7=mat5.mul(mat6);
        System.out.println("mat7=\n"+mat7);
    }
}
