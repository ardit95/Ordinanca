package bl;

import ejb.AnalysisVisit;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class AnalysisVisitRepository extends EntMngClass implements AnalysisVisitInterface {

    public AnalysisVisitRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public AnalysisVisit create(AnalysisVisit AnalysisVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(AnalysisVisit);
            em.flush();
            em.getTransaction().commit();
            return AnalysisVisit;
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
    public void edit(AnalysisVisit AnalysisVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(AnalysisVisit);
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
    public void remove(AnalysisVisit AnalysisVisit) {
        em.getTransaction().begin();
        em.remove(AnalysisVisit);
        em.getTransaction().commit();
    }

    @Override
    public List<AnalysisVisit> findAll() {
        return em.createNamedQuery("AnalysisVisitRepository.findAll").getResultList();
    }
}
