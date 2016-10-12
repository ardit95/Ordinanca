package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Notification;

public interface NotificationInterface {
    Notification create (Notification notification)throws AppException;
    void edit (Notification notification)throws AppException;
    void remove (Notification notification);
    List <Notification> findAll();
}
