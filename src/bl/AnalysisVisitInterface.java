package bl;

import ejb.AnalysisVisit;
import java.util.List;
import ExceptionPackage.AppException;

public interface AnalysisVisitInterface {
    AnalysisVisit create(AnalysisVisit AnalysisVisit)throws AppException;
    void edit(AnalysisVisit AnalysisVisit)throws AppException;
    void remove(AnalysisVisit AnalysisVisit);
    List<AnalysisVisit> findAll();
}
