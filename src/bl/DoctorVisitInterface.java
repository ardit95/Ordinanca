package bl;

import ExceptionPackage.AppException;
import ejb.DoctorVisit;
import ejb.Staff;
import ejb.Visit;
import java.util.Collection;
import java.util.List;

public interface DoctorVisitInterface {

    DoctorVisit create(DoctorVisit DoctorVisit) throws AppException;

    void edit(DoctorVisit DoctorVisit) throws AppException;

    void remove(DoctorVisit DoctorVisit);

    List<DoctorVisit> findAll();

    List<DoctorVisit> findPresentAndFuture(Staff currentUser);

    List<DoctorVisit> findAllForCurrentUser(Staff currentUser);

    List<DoctorVisit> findPresentAndFuture();
}
