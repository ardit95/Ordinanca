package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Logs;
import java.util.Date;

public interface LogsInterface {

    Logs create(Logs logs) throws AppException;

    void edit(Logs logs) throws AppException;

    void remove(Logs logs);

    List<Logs> findAll();

    Date findDate();

    List<Logs> findByAll(String text);
}
