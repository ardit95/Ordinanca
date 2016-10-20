package bl;

import ejb.DiagnosisForVisit;
import java.util.List;
import ExceptionPackage.AppException;
import ejb.DoctorVisit;

public interface DiagnosisForVisitInterface {

    DiagnosisForVisit create(DiagnosisForVisit DiagnosisForVisit) throws AppException;

    void edit(DiagnosisForVisit DiagnosisForVisit) throws AppException;

    void remove(DiagnosisForVisit DiagnosisForVisit);

    List<DiagnosisForVisit> findAll();

    List<DiagnosisForVisit> findByPatient(int PatientID);

    List<DiagnosisForVisit> findByVisit(DoctorVisit doctorVisit);
            
}
