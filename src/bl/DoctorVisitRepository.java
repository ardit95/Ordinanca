package bl;

import ejb.DoctorVisit;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;
import ejb.Staff;
import javax.persistence.Query;

public class DoctorVisitRepository extends EntMngClass implements DoctorVisitInterface {

    public DoctorVisitRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public DoctorVisit create(DoctorVisit DoctorVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(DoctorVisit);
            em.flush();
            em.getTransaction().commit();
            return DoctorVisit;
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
    public void edit(DoctorVisit DoctorVisit) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(DoctorVisit);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")) {
                    throw new AppException("Ekziston një kompani me këto vlera.Secila kompani duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje kompani me këtë çelës primarë.");
                }
            } else {
                thro.printStackTrace();
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void remove(DoctorVisit DoctorVisit) {
        em.getTransaction().begin();
        em.remove(DoctorVisit);
        em.getTransaction().commit();
    }

    @Override
    public List<DoctorVisit> findAll() {
        return em.createNamedQuery("DoctorVisit.findAll").getResultList();
    }

    @Override
    public List<DoctorVisit> findPresentAndFutureForCurrentUser(Staff currentUser) {
        Query query = em.createQuery("SELECT Object (doctorVisit) FROM DoctorVisit doctorVisit WHERE FUNC('date',doctorVisit.timeStamp)>= FUNC('date',CURRENT_TIMESTAMP) AND doctorVisit.doctorID.username = :currentU ORDER BY doctorVisit.timeStamp ");
        query.setParameter("currentU",currentUser.getUsername());
        return (List<DoctorVisit>)query.getResultList();
    }
    
    @Override
    public List<DoctorVisit> findPresentAndFuture() {
        Query query = em.createQuery("SELECT Object (doctorVisit) FROM DoctorVisit doctorVisit WHERE FUNC('date',doctorVisit.timeStamp)>= FUNC('date',CURRENT_TIMESTAMP) ORDER BY doctorVisit.timeStamp ");
        return (List<DoctorVisit>)query.getResultList();
    }
    
    @Override
    public List<DoctorVisit> findAllForCurrentUser(Staff currentUser) {
        Query query = em.createQuery("SELECT Object (doctorVisit) FROM DoctorVisit doctorVisit WHERE doctorVisit.doctorID.username = :currentU ORDER BY doctorVisit.timeStamp DESC ");
        query.setParameter("currentU",currentUser.getUsername());
        return (List<DoctorVisit>)query.getResultList();
    }
    
    
    @Override
    public List<DoctorVisit> findByAll(String text) {
        Query query = em.createQuery("SELECT object (dv) FROM DoctorVisit dv WHERE dv.patientID.name LIKE :txt OR dv.patientID.surname LIKE :txt OR dv.doctorID.name LIKE :txt OR dv.doctorID.surname LIKE :txt OR dv.remark LIKE :txt");
        query.setParameter("txt", "%" + text + "%");
        return (List<DoctorVisit>) query.getResultList();
    }
    
}
