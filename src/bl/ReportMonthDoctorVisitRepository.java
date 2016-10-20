package bl;

import ejbView.ReportMonthDoctorVisit;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class ReportMonthDoctorVisitRepository extends EntMngClass implements ReportMonthDoctorVisitInterface {

    public ReportMonthDoctorVisitRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public ReportMonthDoctorVisit create(ReportMonthDoctorVisit reportMonthDoctorVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(reportMonthDoctorVisit);
            em.flush();
            em.getTransaction().commit();
            return reportMonthDoctorVisit;
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")) {
                    throw new AppException("Ekziston një kompani me këto vlera.Secila kompani duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje kompani me këtë çelës primarë.");
                }
            } else {
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void edit(ReportMonthDoctorVisit reportMonthDoctorVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(reportMonthDoctorVisit);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")) {
                    throw new AppException("Ekziston një kompani me këto vlera.Secila kompani duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje kompani me këtë çelës primarë.");
                }
            } else {
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void remove(ReportMonthDoctorVisit reportMonthDoctorVisit) {
        em.getTransaction().begin();
        em.remove(reportMonthDoctorVisit);
        em.getTransaction().commit();
    }

    @Override
    public List<ReportMonthDoctorVisit> findAll() {
        return em.createNamedQuery("ReportMonthDoctorVisit.findAll").getResultList();
    }
}
