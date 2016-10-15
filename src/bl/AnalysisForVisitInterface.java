package bl;

import ejb.AnalysisForVisit;
import java.util.List;
import ExceptionPackage.AppException;

public interface AnalysisForVisitInterface {
    AnalysisForVisit create(AnalysisForVisit analysis)throws AppException;
    void edit(AnalysisForVisit AnalysisForVisit)throws AppException;
    void remove(AnalysisForVisit AnalysisForVisit);
    List<AnalysisForVisit> findAll();
}
