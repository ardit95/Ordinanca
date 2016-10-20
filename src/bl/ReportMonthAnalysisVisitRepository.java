package bl;

import ejbView.ReportMonthAnalysisVisit;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;
import javax.swing.JOptionPane;

public class ReportMonthAnalysisVisitRepository extends EntMngClass implements ReportMonthAnalysisVisitInterface {

    public ReportMonthAnalysisVisitRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public ReportMonthAnalysisVisit create(ReportMonthAnalysisVisit reportMonthAnalysisVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(reportMonthAnalysisVisit);
            em.flush();
            em.getTransaction().commit();
            return reportMonthAnalysisVisit;
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
    public void edit(ReportMonthAnalysisVisit reportMonthAnalysisVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(reportMonthAnalysisVisit);
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
    public void remove(ReportMonthAnalysisVisit reportMonthAnalysisVisit) {
        em.getTransaction().begin();
        em.remove(reportMonthAnalysisVisit);
        em.getTransaction().commit();
    }

    @Override
    public List<ReportMonthAnalysisVisit> findAll(){
            return em.createNamedQuery("ReportMonthAnalysisVisit.findAll").getResultList();
    }
}
