package bl;

import ejb.Analysis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ExceptionPackage.AppException;

public class AnalysisRepository extends EntMngClass implements AnalysisInterface {

    public AnalysisRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public Analysis create(Analysis analysis) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(analysis);
            em.flush();
            em.getTransaction().commit();
            return analysis;
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
    public void edit(Analysis analysis) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(analysis);
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
    public void remove(Analysis analysis) {
        em.getTransaction().begin();
        em.remove(analysis);
        em.getTransaction().commit();
    }

    @Override
    public List<Analysis> findAll() {
        return em.createNamedQuery("Analysis.findAll").getResultList();
    }
    
    @Override
    public List<Analysis> findByAnalysisForVisit(int AnalysisVisitID) {
        Query query = em.createQuery("SELECT Object (a) FROM Analysis a ,AnalysisForVisit afv,AnalysisVisit av WHERE afv.analysisID.analysisID= a.analysisID AND afv.analysisVisitID.laboratorTechnicianID =av.laboratorTechnicianID AND av.AnalysisVisitID= :analysisVisitID ");
        query.setParameter("analysisVisitID", AnalysisVisitID);
        return (List<Analysis>) query.getResultList();
    }
}
