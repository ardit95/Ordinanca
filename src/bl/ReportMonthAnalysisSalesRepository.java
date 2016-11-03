package bl;

import ejbView.ReportMonthAnalysisSales;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class ReportMonthAnalysisSalesRepository extends EntMngClass implements ReportMonthAnalysisSalesInterface {

    public ReportMonthAnalysisSalesRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public ReportMonthAnalysisSales create(ReportMonthAnalysisSales reportMonthAnalysisSales) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(reportMonthAnalysisSales);
            em.flush();
            em.getTransaction().commit();
            return reportMonthAnalysisSales;
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
    public void edit(ReportMonthAnalysisSales reportMonthAnalysisSales) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(reportMonthAnalysisSales);
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
    public void remove(ReportMonthAnalysisSales reportMonthAnalysisSales) {
        em.getTransaction().begin();
        em.remove(reportMonthAnalysisSales);
        em.getTransaction().commit();
    }

    @Override
    public List<ReportMonthAnalysisSales> findAll() {
        return em.createNamedQuery("ReportMonthAnalysisSales.findAll").getResultList();
    }
}
