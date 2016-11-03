package bl;

import ejbView.ReportMonthVisitSales;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class ReportMonthVisitSalesRepository extends EntMngClass implements ReportMonthVisitSalesInterface {

    public ReportMonthVisitSalesRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public ReportMonthVisitSales create(ReportMonthVisitSales reportMonthVisitSales) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(reportMonthVisitSales);
            em.flush();
            em.getTransaction().commit();
            return reportMonthVisitSales;
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
    public void edit(ReportMonthVisitSales reportMonthVisitSales) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(reportMonthVisitSales);
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
    public void remove(ReportMonthVisitSales reportMonthVisitSales) {
        em.getTransaction().begin();
        em.remove(reportMonthVisitSales);
        em.getTransaction().commit();
    }

    @Override
    public List<ReportMonthVisitSales> findAll() {
        return em.createNamedQuery("ReportMonthVisitSales.findAll").getResultList();
    }
}
