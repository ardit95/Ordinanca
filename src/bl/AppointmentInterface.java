
package bl;

import ExceptionPackage.AppException;
import ejb.Appointment;
import java.util.List;

public interface AppointmentInterface {
    Appointment create(Appointment appointment)throws AppException;
    void edit(Appointment appointment)throws AppException;
    void remove(Appointment appointment);
    List<Appointment> findAll();
}
