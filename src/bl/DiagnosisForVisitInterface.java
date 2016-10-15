package bl;

import ejb.DiagnosisForVisit;
import java.util.List;
import ExceptionPackage.AppException;

public interface DiagnosisForVisitInterface {
    DiagnosisForVisit create(DiagnosisForVisit DiagnosisForVisit)throws AppException;
    void edit(DiagnosisForVisit DiagnosisForVisit)throws AppException;
    void remove(DiagnosisForVisit DiagnosisForVisit);
    List<DiagnosisForVisit> findAll();
}