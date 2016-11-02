package bl;

import ejb.AnalysisVisit;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;
import ejb.Staff;
import javax.persistence.Query;

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
        return em.createNamedQuery("AnalysisVisit.findAll").getResultList();
    }

    @Override
    public List<AnalysisVisit> findPresentAndFuture(Staff currentUser) {
        Query query = em.createQuery("SELECT Object (analysisVisit) FROM AnalysisVisit analysisVisit WHERE FUNC('date',analysisVisit.timeStamp )>= FUNC('date',CURRENT_TIMESTAMP) AND analysisVisit.laboratorTechnicianID.username = :currentU ORDER BY analysisVisit.timeStamp ");
        query.setParameter("currentU",currentUser.getUsername());
        return (List<AnalysisVisit>)query.getResultList();
    }
    
    @Override
    public List<AnalysisVisit> findPresentAndFuture() {
        Query query = em.createQuery("SELECT Object (analysisVisit) FROM AnalysisVisit analysisVisit WHERE FUNC('date',analysisVisit.timeStamp )>= FUNC('date',CURRENT_TIMESTAMP) ORDER BY analysisVisit.timeStamp ");
        return (List<AnalysisVisit>)query.getResultList();
    }

    @Override
    public List<AnalysisVisit> findAllForCurrentUser(Staff currentUser) {
        Query query = em.createQuery("SELECT Object (analysisVisit) FROM AnalysisVisit analysisVisit WHERE analysisVisit.laboratorTechnicianID.username = :currentU ORDER BY analysisVisit.timeStamp DESC ");
        query.setParameter("currentU",currentUser.getUsername());
        return (List<AnalysisVisit>)query.getResultList();
    }
}
