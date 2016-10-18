package bl;

import ejb.AnalysisForVisit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ExceptionPackage.AppException;

public class AnalysisForVisitRepository extends EntMngClass implements AnalysisForVisitInterface {

    public AnalysisForVisitRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public AnalysisForVisit create(AnalysisForVisit AnalysisForVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(AnalysisForVisit);
            em.flush();
            em.getTransaction().commit();
            return AnalysisForVisit;
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
    public void edit(AnalysisForVisit AnalysisForVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(AnalysisForVisit);
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
    public void remove(AnalysisForVisit AnalysisForVisit) {
        em.getTransaction().begin();
        em.remove(AnalysisForVisit);
        em.getTransaction().commit();
    }

    @Override
    public List<AnalysisForVisit> findAll() {
        return em.createNamedQuery("AnalysisForVisit.findAll").getResultList();
    }
    
    @Override
    public List<AnalysisForVisit> findByPatient(int PatientID) {
        Query query=em.createQuery("SELECT object (afv) FROM AnalysisForVisit afv WHERE afv.analysisVisitID.patientID.patientID= :patientID");
        query.setParameter("patientID", PatientID);
        return (List <AnalysisForVisit>)query.getResultList();
    }
}
