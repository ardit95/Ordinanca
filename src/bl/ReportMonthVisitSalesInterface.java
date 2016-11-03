package bl;

import ExceptionPackage.AppException;
import ejbView.ReportMonthVisitSales;
import java.util.List;

public interface ReportMonthVisitSalesInterface {

    ReportMonthVisitSales create(ReportMonthVisitSales reportMonthVisitSales) throws AppException;

    void edit(ReportMonthVisitSales reportMonthVisitSales) throws AppException;

    void remove(ReportMonthVisitSales reportMonthVisitSales);

    List<ReportMonthVisitSales> findAll();
}