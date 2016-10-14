package bl;

import ejb.AnalysisDetail;
import java.util.List;
import ExceptionPackage.AppException;

public interface AnalysisDetailInterface {
    AnalysisDetail create(AnalysisDetail analysis)throws AppException;
    void edit(AnalysisDetail analysisDetail)throws AppException;
    void remove(AnalysisDetail analysisDetail);
    List<AnalysisDetail> findAll();
}
