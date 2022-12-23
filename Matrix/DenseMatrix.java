package Matrix;

import ErrorHandling.DataStructureException;
import List.List;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.function.Supplier;

public class DenseMatrix<T extends Number & Comparable<? super T>, L extends List<T>> extends Matrix<T, L> {
    public DenseMatrix(Supplier<L> supplier, int rows, int cols) throws DataStructureException {
        super(rows, cols);
        _data = supplier.get();
        _data.resize(rows * cols);
        _type = MatrixType.DenseRowMajorSingleList;
        _minId = -1;
        _maxId = -1;
        _maxDigits=0;
    }

    public DenseMatrix(L list, int rows, int cols) throws DataStructureException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        super(list, rows, cols);
        _initMinMax();
        _type = MatrixType.DenseRowMajorSingleList;
    }

    public DenseMatrix(DenseMatrix<T, L> matrix) {
        super(matrix);
        _data = matrix._data;
        _type = matrix._type;
        _max = matrix._max;
        _min = matrix._min;
        _maxId = matrix._maxId;
        _minId = matrix._minId;
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
    protected int posToId(int row, int col) throws DataStructureException {
        if (_type == MatrixType.DenseRowMajorSingleList) {
            return row * _cols + col;
        } else if (_type == MatrixType.DenseColumnMajorSingleList) {
            return col * _rows + row;
        } else throw new DataStructureException("Debug: Wrong MatrixType!");
    }

    @Override
    protected Matrix<T, L>.Pos idToPos(int id) throws DataStructureException {
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
    public Matrix<T, L>.Pos maxPos() throws DataStructureException {
        return idToPos(_maxId);
    }

    @Override
    public Matrix<T, L>.Pos minPos() throws DataStructureException {
        return idToPos(_minId);
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
    private int _maxDigits;

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
}
