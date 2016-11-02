package bl;

import ejb.Diagnosis;
import java.util.List;
import ExceptionPackage.AppException;

public interface DiagnosisInterface {

    Diagnosis create(Diagnosis Diagnosis) throws AppException;

    void edit(Diagnosis Diagnosis) throws AppException;

    void remove(Diagnosis Diagnosis);

    List<Diagnosis> findAll();
    
    List<Diagnosis> findByDiagnosisForVisit(int DoctorVisitID);
    
    List<Diagnosis> findByDoctorVisit(int DoctorVisitID);
}
