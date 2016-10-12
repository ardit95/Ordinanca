package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Message;

public interface MessageInterface {
    Message create (Message message)throws AppException;
    void edit (Message message)throws AppException;
    void remove (Message message);
    List <Message> findAll();
}
