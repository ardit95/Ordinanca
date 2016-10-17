package bl;

import ejb.DoctorVisit;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

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
        return em.createNamedQuery("DoctorVisitRepository.findAll").getResultList();
    }
}
