package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Staff;

public interface StaffInterface {
    Staff create (Staff staff)throws AppException;
    void edit (Staff staff)throws AppException;
    void remove (Staff staff);
    List <Staff> findAll();
}
