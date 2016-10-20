package bl;

import ExceptionPackage.AppException;
import ejbView.ReportMonthDoctorVisit;
import java.util.List;

public interface ReportMonthDoctorVisitInterface {

    ReportMonthDoctorVisit create(ReportMonthDoctorVisit reportMonthDoctorVisit) throws AppException;

    void edit(ReportMonthDoctorVisit reportMonthDoctorVisit) throws AppException;

    void remove(ReportMonthDoctorVisit reportMonthDoctorVisit);

    List<ReportMonthDoctorVisit> findAll();
}

