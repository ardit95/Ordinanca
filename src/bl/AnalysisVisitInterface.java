package bl;

import ejb.AnalysisVisit;
import java.util.List;
import ExceptionPackage.AppException;
import ejb.Staff;

public interface AnalysisVisitInterface {

    AnalysisVisit create(AnalysisVisit AnalysisVisit) throws AppException;

    void edit(AnalysisVisit AnalysisVisit) throws AppException;

    void remove(AnalysisVisit AnalysisVisit);

    List<AnalysisVisit> findAll();

    List<AnalysisVisit> findPresentAndFutureForCurrentUser(Staff currentUser);

    List<AnalysisVisit> findAllForCurrentUser(Staff currentUser);
    
    List<AnalysisVisit> findPresentAndFuture();
    
    List<AnalysisVisit> findByAll(String text);
}
