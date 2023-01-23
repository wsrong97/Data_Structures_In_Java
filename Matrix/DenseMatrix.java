package Matrix;

import ErrorHandling.DataStructureException;
import List.List;
import List.ArrayList;
import jdk.jshell.DeclarationSnippet;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class DenseMatrix<T extends Number & Comparable<? super T>> implements Matrix<T, DenseMatrix<T>> {
    public DenseMatrix(int rows, int cols) throws DataStructureException {
        _data=new ArrayList<T>(rows*cols);
        _data.resize(rows * cols);
        _rows = rows;
        _cols = cols;
        _type = MatrixType.DenseRowMajorSingleList;
        _minId = -1;
        _maxId = -1;
        _maxDigits = 0;
    }

    public DenseMatrix(ArrayList<T> list, int rows, int cols) throws DataStructureException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        _rows = rows;
        _cols = cols;
        _minId = -1;
        _maxId = -1;
        init(list);
        _initMinMax();
        _type = MatrixType.DenseRowMajorSingleList;
        _maxDigits = 0;
    }

    public DenseMatrix(DenseMatrix<T> matrix) {
        _data = new ArrayList<T>(matrix._data);
        _type = matrix._type;
        _max = matrix._max;
        _min = matrix._min;
        _maxId = matrix._maxId;
        _minId = matrix._minId;
        _maxDigits = matrix._maxDigits;
        _rows = matrix._rows;
        _cols = matrix._cols;
    }

    private void _initMinMax() throws DataStructureException {
        for (int i = 0; i < _data.size(); i++) {
            _maxDigits = Math.max(_maxDigits, String.valueOf(_data.get(i).intValue()).length());
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
        _maxDigits = Math.max(_maxDigits, String.valueOf(_data.get(posToId(row, col)).intValue()).length());
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
        set(pos.row, pos.col, value);
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
    public void init(ArrayList<T> list) throws DataStructureException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (list.size() != _rows * _cols)
            throw new DataStructureException("Input list size doesn't match with input rows and cols (" + list.size() + ")!=(" + _rows + ", " + _cols + ").");
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
    public DenseMatrix<T> inv(DenseMatrix<T> matrix) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
    }

    @Override
    public DenseMatrix<T> mul(DenseMatrix<T> matrix) throws DataStructureException {
        if (_cols != matrix.rows())
            throw new DataStructureException(String.format("Matrix Addition cannot propagate between (%d,%d) and (%d,%d)", _rows, _cols, matrix.rows(), matrix.cols()));
        if (size() == 0) throw new DataStructureException("Empty Matrix!");
        DenseMatrix<T> res = new DenseMatrix<T>(_rows,matrix.cols());
        if (matrix.at(0) instanceof Integer)
            for (int i = 0; i < _rows; i++) {
                for (int j = 0; j < matrix.cols(); j++) {
                    Integer tmp=0;
                    for (int k = 0; k < _cols; k++) {
                        tmp+=at(i,k).intValue()*matrix.at(k,j).intValue();
                    }
                    res.set(i,j,(T)tmp);
                }
            }
        else if (matrix.at(0) instanceof Double)
            for (int i = 0; i < _rows; i++) {
                for (int j = 0; j < matrix.cols(); j++) {
                    Double tmp=0.;
                    for (int k = 0; k < _cols; k++) {
                        tmp+=at(i,k).doubleValue()*matrix.at(k,j).doubleValue();
                    }
                    res.set(i,j,(T)tmp);
                }
            }
        else if (matrix.at(0) instanceof Long)
            for (int i = 0; i < _rows; i++) {
                for (int j = 0; j < matrix.cols(); j++) {
                    Long tmp= 0L;
                    for (int k = 0; k < _cols; k++) {
                        tmp+=at(i,k).longValue()*matrix.at(k,j).longValue();
                    }
                    res.set(i,j,(T)tmp);
                }
            }
        else if (matrix.at(0) instanceof Float)
            for (int i = 0; i < _rows; i++) {
                for (int j = 0; j < matrix.cols(); j++) {
                    Float tmp= 0.F;
                    for (int k = 0; k < _cols; k++) {
                        tmp+=at(i,k).floatValue()*matrix.at(k,j).floatValue();
                    }
                    res.set(i,j,(T)tmp);
                }
            }
        else {
            throw new DataStructureException("Unknown Number Type!");
        }
        return res;
    }

    @Override
    public DenseMatrix<T> mul(T num) throws DataStructureException {
        DenseMatrix<T> res=new DenseMatrix<T>(this);
        for (int i = 0; i < size(); i++) {
            res.set(idToPos(i),(T)new Double(num.doubleValue()*res.at(i).doubleValue()));
        }
        return res;
    }

    @Override
    public DenseMatrix<T> sub(DenseMatrix<T> matrix) throws DataStructureException {
        if (_rows != matrix.rows() || _cols != matrix.cols())
            throw new DataStructureException(String.format("Matrix Addition cannot propagate between (%d,%d) and (%d,%d)", _rows, _cols, matrix.rows(), matrix.cols()));
        if (size() == 0) throw new DataStructureException("Empty Matrix!");
        return add(matrix.mul((T)new Double(-1.)));
    }

    @Override
    public DenseMatrix<T> sub(T num) throws DataStructureException {
        DenseMatrix<T> res=new DenseMatrix<T>(_rows,_cols);
        for (int i = 0; i < size(); i++) {
            res.set(idToPos(i),(T)new Double(this.at(i).doubleValue()-num.doubleValue()));
        }
        return res;
    }

    @Override
    public DenseMatrix<T> add(DenseMatrix<T> matrix) throws DataStructureException {
//        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
        if (_rows != matrix.rows() || _cols != matrix.cols())
            throw new DataStructureException(String.format("Matrix Addition cannot propagate between (%d,%d) and (%d,%d)", _rows, _cols, matrix.rows(), matrix.cols()));
        if (size() == 0) throw new DataStructureException("Empty Matrix!");
        DenseMatrix<T> res = new DenseMatrix<T>(_rows,_cols);
        if (matrix.at(0) instanceof Integer)
            for (int i = 0; i < size(); i++) {
                res.set(idToPos(i), (T) new Integer(matrix.at(i).intValue() + this.at(i).intValue()));
            }
        else if (matrix.at(0) instanceof Double)
            for (int i = 0; i < size(); i++) {
                res.set(idToPos(i), (T) new Double(matrix.at(i).doubleValue() + this.at(i).doubleValue()));
            }
        else if (matrix.at(0) instanceof Long)
            for (int i = 0; i < size(); i++) {
                res.set(idToPos(i), (T) new Long(matrix.at(i).longValue() + this.at(i).longValue()));
            }
        else if (matrix.at(0) instanceof Float)
            for (int i = 0; i < size(); i++) {
                res.set(idToPos(i), (T) new Float(matrix.at(i).floatValue() + this.at(i).floatValue()));
            }
        else {
            throw new DataStructureException("Unknown Number Type!");
        }
        return res;
    }

    @Override
    public DenseMatrix<T> add(T num) throws DataStructureException {
        DenseMatrix<T> res=new DenseMatrix<T>(this);
        for (int i = 0; i < size(); i++) {
            res.set(idToPos(i),(T)new Double(res.at(i).doubleValue()+num.doubleValue()));
        }
        return res;
    }

    @Override
    public int size() {
        return _data.size();
    }

    public void changeType(MatrixType type) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
//        _type = type;
    }

    public DenseMatrix<T> changeMatrixType(MatrixType type) throws DataStructureException {
        throw DataStructureException.FUNCTION_NOT_IMPLEMENTED();
    }

    private ArrayList<T> _data;
    private int _maxDigits, _rows, _cols, _maxId, _minId;
    private T _max, _min;
    private MatrixType _type;

    public String toString() {
        String format = "%" + (_maxDigits + 1 + 6) + ".6f";
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
    public boolean checkPos(int row, int col) {
        if (row < 0 || row > _rows - 1 || col < 0 || col > _cols - 1) return false;
        else return true;
    }

    public void setAll(T num) throws DataStructureException {
        for (int i = 0; i < size(); i++) {
            set(idToPos(i),num);
        }
    }
}
