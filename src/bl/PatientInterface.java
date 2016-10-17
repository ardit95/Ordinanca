package bl;

import ExceptionPackage.AppException;
import java.util.List;
import ejb.Patient;

public interface PatientInterface {

    Patient create(Patient patient) throws AppException;

    void edit(Patient patient) throws AppException;

    void remove(Patient patient);

    List<Patient> findAll();

    List<Patient> findByAll(String text);
}
