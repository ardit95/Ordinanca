package bl;

import ejb.DoctorVisitDetails;
import java.util.List;
import ExceptionPackage.AppException;

public interface DoctorVisitDetailsInterface {
    DoctorVisitDetails create(DoctorVisitDetails DoctorVisitDetails)throws AppException;
    void edit(DoctorVisitDetails DoctorVisitDetails)throws AppException;
    void remove(DoctorVisitDetails DoctorVisitDetails);
    List<DoctorVisitDetails> findAll();
}
