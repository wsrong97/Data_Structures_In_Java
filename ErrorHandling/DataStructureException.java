package ErrorHandling;
import java.lang.Exception;

public class DataStructureException extends Exception{
    public DataStructureException(String errorMessage){
        super(errorMessage);
    }
}
