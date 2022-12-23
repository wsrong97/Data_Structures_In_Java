package ErrorHandling;
import java.lang.Exception;

public class DataStructureException extends Exception{
    public DataStructureException(String errorMessage){
        super(errorMessage);
    }
    public static DataStructureException FUNCTION_NOT_IMPLEMENTED(){return new DataStructureException("FUNCTION_NOT_IMPLEMENTED");}
}
