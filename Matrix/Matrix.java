package Matrix;

import ErrorHandling.DataStructureException;
import List.List;
import List.ArrayList;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.function.Supplier;

public interface Matrix <T extends Number & Comparable<? super T>,M extends Matrix>{
    public int rows();
    public int cols();
    public void set(int row, int col, T value) throws DataStructureException;
    public T at(int row, int col) throws DataStructureException;
    void init(ArrayList<T> list) throws DataStructureException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    int posToId(int row, int col) throws DataStructureException;
    default int posToId(Pos pos) throws DataStructureException{
        return posToId(pos.row, pos.col);
    }
    Pos idToPos(int id) throws DataStructureException;
    public T max() throws DataStructureException;
    public T min() throws DataStructureException;
    public Pos maxPos() throws DataStructureException;
    public Pos minPos() throws DataStructureException;
    public M add(M matrix) throws DataStructureException;
    public M add(T num) throws DataStructureException;
    public M sub(M matrix) throws DataStructureException;
    public M sub(T num) throws DataStructureException;
    public M mul(M matrix) throws DataStructureException;
    public M mul(T num) throws DataStructureException;
    public M inv(M matrix) throws DataStructureException;
    boolean checkPos(int row, int col);
    default boolean checkPos(Pos pos){
        return checkPos(pos.row, pos.col);
    }
    public int size();
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
