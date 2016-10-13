package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Staff;

public interface StaffInterface {
    Staff create (Staff staff)throws AppException;
    void edit (Staff staff)throws AppException;
    void remove (Staff staff);
    List <Staff> findAll();
    String findSalt(String username);
    Staff findByUsernamePassword(String us,byte[] pass);
    byte[] kripto(String pass);
    void changeLoginPassword(Staff staff, String text);
    int getNumberOfLogins(Staff staff);
}
