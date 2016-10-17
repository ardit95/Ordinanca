package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Staff;
import java.sql.SQLException;

public interface StaffInterface {

    Staff create(Staff staff) throws AppException;

    void edit(Staff staff) throws AppException;

    void remove(Staff staff);

    List<Staff> findAll();

    String findSalt(String username);

    Staff findByUsernamePassword(String us, byte[] pass);

    byte[] kripto(String pass);

    void changeLoginPassword(Staff staff, String text);

    int getNumberOfLogins(Staff staff);

    void createMySQLUser(Staff staff, String text) throws SQLException;

    public int CheckAdminExists();

    void deleteMySQLUser(Staff staff) throws SQLException;

    void setStaffPassword(Staff staff);

    List<Staff> findByAll(String text);

    List<Staff> findAllWithoutAdministratorAndMyself(Staff currentUser);

    List<Staff> findAllWithoutAdministrator();
}
