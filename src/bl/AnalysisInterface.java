package bl;

import ejb.Analysis;
import java.util.List;
import ExceptionPackage.AppException;

public interface AnalysisInterface {

    Analysis create(Analysis analysis) throws AppException;

    void edit(Analysis analysis) throws AppException;

    void remove(Analysis analysis);

    List<Analysis> findAll();
    
    List<Analysis> findByAnalysisForVisit(int AnalysisVisitID);
    
    List<Analysis> findByAnalysisVisit(int AnalysisVisitID);
}
