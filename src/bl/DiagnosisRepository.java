package bl;

import ejb.Diagnosis;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;
import javax.persistence.Query;

public class DiagnosisRepository extends EntMngClass implements DiagnosisInterface {

    public DiagnosisRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public Diagnosis create(Diagnosis Diagnosis) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(Diagnosis);
            em.flush();
            em.getTransaction().commit();
            return Diagnosis;
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
    public void edit(Diagnosis Diagnosis) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(Diagnosis);
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
    public void remove(Diagnosis Diagnosis) {
        em.getTransaction().begin();
        em.remove(Diagnosis);
        em.getTransaction().commit();
    }

    @Override
    public List<Diagnosis> findAll() {
        return em.createNamedQuery("DiagnosisRepository.findAll").getResultList();
    }
    
    @Override
    public List<Diagnosis> findByDiagnosisForVisit(int DoctorVisitID) {
        Query query = em.createQuery("SELECT Object (d) FROM Diagnosis d ,DiagnosisForVisit dfv,DoctorVisit dv WHERE dfv.diagnosisID.diagnosisID=d.diagnosisID AND dfv.doctorVisitID.doctorID =dv.doctorID AND dv.DoctorVisitID= :doctorVisitID ");
        query.setParameter("doctorVisitID", DoctorVisitID);
        return (List<Diagnosis>) query.getResultList();
    }
    
    @Override
    public List<Diagnosis> findByDoctorVisit(int DoctorVisitID) {
        Query query = em.createQuery("SELECT Object (d) FROM Diagnosis d ,DiagnosisForVisit dfv,DoctorVisit dv WHERE dfv.diagnosisID.diagnosisID=d.diagnosisID AND dfv.doctorVisitID.DoctorVisitID =dv.DoctorVisitID AND dv.DoctorVisitID= :doctorVisitID ");
        query.setParameter("doctorVisitID", DoctorVisitID);
        return (List<Diagnosis>) query.getResultList();
    }
}
