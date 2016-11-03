package bl;

import ExceptionPackage.AppException;
import ejbView.ReportMonthAnalysisSales;
import java.util.List;

public interface ReportMonthAnalysisSalesInterface {

    ReportMonthAnalysisSales create(ReportMonthAnalysisSales reportMonthAnalysisSales) throws AppException;

    void edit(ReportMonthAnalysisSales reportMonthAnalysisSales) throws AppException;

    void remove(ReportMonthAnalysisSales reportMonthAnalysisSales);

    List<ReportMonthAnalysisSales> findAll();
}