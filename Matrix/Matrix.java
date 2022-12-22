package Matrix;

import List.List;
import java.util.function.Supplier;

public abstract class Matrix <T,L extends List<T>>{
    public Matrix(int rows, int cols){
        _rows=rows;
        _cols=cols;
    }
    protected int _rows,_cols;
    protected MatrixType _type;
}
