package bl;

import ExceptionPackage.AppException;
import ejbView.ReportMonthAnalysisVisit;
import java.util.List;

public interface ReportMonthAnalysisVisitInterface {

    ReportMonthAnalysisVisit create(ReportMonthAnalysisVisit reportMonthAnalysisVisit) throws AppException;

    void edit(ReportMonthAnalysisVisit reportMonthAnalysisVisit) throws AppException;

    void remove(ReportMonthAnalysisVisit reportMonthAnalysisVisit);

    List<ReportMonthAnalysisVisit> findAll();
}

