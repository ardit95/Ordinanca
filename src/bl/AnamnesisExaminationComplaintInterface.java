
package bl;

import ExceptionPackage.AppException;
import ejb.AnamnesisExaminationComplaint;
import java.util.List;

public interface AnamnesisExaminationComplaintInterface {
    AnamnesisExaminationComplaint create(AnamnesisExaminationComplaint aec)throws AppException;
    void edit(AnamnesisExaminationComplaint aec)throws AppException;
    void remove(AnamnesisExaminationComplaint aec);
    List<AnamnesisExaminationComplaint> findAll();
}
