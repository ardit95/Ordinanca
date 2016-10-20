package bl;

import ExceptionPackage.AppException;
import ejbView.ReportDoctor;
import java.util.List;

public interface ReportDoctorInterface {

    ReportDoctor create(ReportDoctor reportDoctor) throws AppException;

    void edit(ReportDoctor DoctorVisit) throws AppException;

    void remove(ReportDoctor reportDoctor);

    List<ReportDoctor> findAll();
}
