package bl;

import ExceptionPackage.AppException;
import ejb.DoctorVisit;
import ejb.Staff;
import java.util.List;

public interface DoctorVisitInterface {

    DoctorVisit create(DoctorVisit DoctorVisit) throws AppException;

    void edit(DoctorVisit DoctorVisit) throws AppException;

    void remove(DoctorVisit DoctorVisit);

    List<DoctorVisit> findAll();

    List<DoctorVisit> findPresentAndFuture(Staff currentUser);

    List<DoctorVisit> findAllForCurrentUser(Staff currentUser);
}
