
package bl;

import ExceptionPackage.AppException;
import ejb.DoctorVisit;
import java.util.List;

public interface DoctorVisitInterface {
    DoctorVisit create(DoctorVisit doctorVisit)throws AppException;
    void edit(DoctorVisit doctorVisit)throws AppException;
    void remove(DoctorVisit doctorVisit);
    List<DoctorVisit> findAll();
}
