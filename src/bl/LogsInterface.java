package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Logs;

public interface LogsInterface {
    Logs create (Logs logs)throws AppException;
    void edit (Logs logs)throws AppException;
    void remove (Logs logs);
    List <Logs> findAll();
}
