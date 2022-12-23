package Matrix;

import ErrorHandling.DataStructureException;
import List.List;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class DenseMatrix<T extends Number & Comparable<? super T>, L extends List<T>> implements Matrix<T, L,DenseMatrix<T,L>> {
    public DenseMatrix(Supplier<L> supplier, int rows, int cols) throws DataStructureException {
        _data = supplier.get();
        _data.resize(rows * cols);
        _rows=rows;
        _cols=cols;
        _type = MatrixType.DenseRowMajorSingleList;
        _minId = -1;
        _maxId = -1;
        _maxDigits=0;
    }

    public DenseMatrix(L list, int rows, int cols) throws DataStructureException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        _rows=rows;
        _cols=cols;
        init(list);
        _initMinMax();
        _type = MatrixType.DenseRowMajorSingleList;
        _maxDigits=0;
    }

    public DenseMatrix(DenseMatrix<T, L> matrix) {
        _data = matrix._data;
        _type = matrix._type;
        _max = matrix._max;
        _min = matrix._min;
        _maxId = matrix._maxId;
        _minId = matrix._minId;
        _maxDigits=matrix._maxDigits;
        _rows=matrix._rows;
        _cols=matrix._cols;
    }

    private void _initMinMax() throws DataStructureException {
        for (int i = 0; i < _data.size(); i++) {
            _maxDigits=Math.max(_maxDigits,String.valueOf(_data.get(i).intValue()).length());
            if (_maxId == -1) {
                _max = _data.get(i);
            } else {
                if (_max.compareTo(_data.get(i)) < 0) {
                    _max = _data.get(i);
                }
            }
            if (_minId == -1) {
                _min = _data.get(i);
            } else {
                if (_min.compareTo(_data.get(i)) < 0) {
                    _min = _data.get(i);
                }
            }
        }
    }

    private void _updateMinMax(int row, int col, T value) throws DataStructureException {
        _maxDigits=Math.max(_maxDigits,String.valueOf(_data.get(posToId(row,col)).intValue()).length());
        if (_maxId == -1) {
            _max = value;
            _maxId = posToId(row, col);
        } else {
            if (_max.compareTo(value) < 0) {
                _max = value;
                _maxId = posToId(row, col);
            }
        }
        if (_minId == -1) {
            _min = value;
            _minId = posToId(row, col);
        } else {
            if (_min.compareTo(value) > 0) {
                _min = value;
                _minId = posToId(row, col);
            }
        }
    }

    @Override
    public int rows() {
       return _rows;
    }

    @Override
    public int cols() {
        return _cols;
    }

    public void set(Pos pos, T value) throws DataStructureException {
        set(pos.row,pos.col,value);
    }
    @Override
    public void set(int row, int col, T value) throws DataStructureException {
        if (checkPos(row, col)) {
            _data.set(posToId(row, col), value);
            _updateMinMax(row, col, value);
        } else throw new DataStructureException("Wrong Row or Column index: (rows,cols)=(" + _rows + "," + _cols + ")");
    }

    @Override
    public T at(int row, int col) throws DataStructureException {
        if (checkPos(row, col))
            return at(posToId(row, col));
        else throw new DataStructureException("Wrong Row or Column index: (rows,cols)=(" + _rows + "," + _cols + ")");
    }

    public T at(int id) throws DataStructureException {
        return _data.get(id);
    }

    public int maxId() {
        return _maxId;
    }

    public int minId() {
        return _minId;
    }

    @Override
    public void init(L list) throws DataStructureException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (list.size() != _rows * _cols)
            throw new DataStructureException("Input list size doesn't match with input rows and cols (" + list.size() + ")!=(" + _rows + ", " + _cols + ").");
//        _data = (L) list.getClass().getConstructor(list.getClass()).newInstance(list);
        _data = list;
    }

    @Override
    public int posToId(int row, int col) throws DataStructureException {
        if (_type == MatrixType.DenseRowMajorSingleList) {
            return row * _cols + col;
        } else if (_type == MatrixType.DenseColumnMajorSingleList) {
            return col * _rows + row;
        } else throw new DataStructureException("Debug: Wrong MatrixType!");
    }

    @Override
    public int posToId(Pos pos) throws DataStructureException {
        return 0;
    }

    @Override
    public Pos idToPos(int id) throws DataStructureException {
        Pos pos = new Pos();
        if (_type == MatrixType.DenseRowMajorSingleList) {
            pos.row = id / _cols;
            pos.col = id % _cols;
        } else if (_type == MatrixType.DenseColumnMajorSingleList) {
            pos.row = id % _rows;
            pos.col = id / _rows;
        } else throw new DataStructureException("Debug: Wrong MatrixType");
        return pos;
    }

    @Override
    public T max() throws DataStructureException {
        if (_maxId == -1) throw new DataStructureException("Debug: Max value is not initialized.");
        return _max;
    }

    @Override
    public T min() throws DataStructureException {
        if (_minId == -1) throw new DataStructureException("Debug: Min value is not initialized.");
        return _min;
    }

    @Override
    public Pos maxPos() throws DataStructureException {
        return idToPos(_maxId);
    }

    @Override
    public Pos minPos() throws DataStructureException {
        return idToPos(_minId);
    }

    @Override
    public DenseMatrix<T, L> inv(DenseMatrix<T, L> matrix) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
    }

    @Override
    public DenseMatrix<T, L> mul(DenseMatrix<T, L> matrix) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
    }

    @Override
    public DenseMatrix<T, L> sub(DenseMatrix<T, L> matrix) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
    }

    @Override
    public DenseMatrix<T, L> add(DenseMatrix<T, L> matrix) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
//        if(_rows!=matrix.rows()||_cols!=matrix.cols()) throw new DataStructureException(String.format("Matrix Addition cannot propagate between (%d,%d) and (%d,%d)",_rows,_cols,matrix.rows(),matrix.cols()));
//        DenseMatrix<T,L> res=new DenseMatrix<T, L>(matrix);
//        for (int i = 0; i < size(); i++) {
//            res.set(idToPos(i),res.at(i).add(this.at(i)));
//        }
    }

    @Override
    public int size() {
        return _data.size();
    }

    public void changeType(MatrixType type) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
//        _type = type;
    }

    public DenseMatrix<T, L> changeMatrixType(MatrixType type) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
//        DenseMatrix<T, L> mat = new DenseMatrix<T, L>(this);
//        return mat;
    }

    private L _data;
    private int _maxDigits,_rows,_cols, _maxId, _minId;
    private T _max, _min;
    private MatrixType _type;

    public String toString() {
        String format = "%"+(_maxDigits+1+6)+".6f";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _cols - 1; j++) {
                try {
                    res.append(String.format(format, at(i, j))).append("\t");
                } catch (DataStructureException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                res.append(String.format(format, at(i, _cols - 1))).append("\n");
            } catch (DataStructureException e) {
                throw new RuntimeException(e);
            }
        }
        return res.toString();
    }

    @Override
    public boolean checkPos(int row, int col){
        if(row<0||row>_rows-1||col<0||col>_cols-1) return false;
        else return true;
    }
}
