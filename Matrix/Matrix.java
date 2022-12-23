package Matrix;

import ErrorHandling.DataStructureException;
import List.List;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.function.Supplier;

public abstract class Matrix <T extends Number & Comparable<? super T>,L extends List<T>>{
    public Matrix(int rows, int cols) {
        _rows=rows;
        _cols=cols;
    }
    public Matrix(L list, int rows, int cols) throws DataStructureException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        _rows=rows;
        _cols=cols;
        init(list);
    }
    public Matrix(Matrix<T,L> matrix){
        _rows= matrix._rows;
        _cols=matrix._cols;
    }
    public int rows(){
        return _rows;
    }
    public int cols(){
        return _cols;
    }
    public abstract void set(int row, int col, T value) throws DataStructureException;
    public abstract T at(int row, int col) throws DataStructureException;
    public abstract void init(L list) throws DataStructureException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    protected abstract int posToId(int row, int col) throws DataStructureException;
    protected int posToId(Pos pos) throws DataStructureException{
        return posToId(pos.row, pos.col);
    }
    protected abstract Pos idToPos(int id) throws DataStructureException;
    protected int _rows,_cols;
    protected T _max, _min;
    protected int _maxId, _minId;
    protected MatrixType _type;
    public abstract T max() throws DataStructureException;
    public abstract T min() throws DataStructureException;
    public abstract Pos maxPos() throws DataStructureException;
    public abstract Pos minPos() throws DataStructureException;
    protected boolean checkPos(int row, int col){
        if(row<0||row>_rows-1||col<0||col>_cols-1) return false;
        else return true;
    }
    protected boolean checkPos(Pos pos){
        return checkPos(pos.row, pos.col);
    }
    public abstract int size();
    public class Pos{
        public Pos(int first, int second){
            row=first;
            col=second;
        }
        public Pos(){}
        public int row, col;

        @Override
        public String toString() {
            return "(" +row+", "+col+")";
        }
    }
}
