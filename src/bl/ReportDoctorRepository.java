package bl;

import ejbView.ReportDoctor;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class ReportDoctorRepository extends EntMngClass implements ReportDoctorInterface {

    public ReportDoctorRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public ReportDoctor create(ReportDoctor reportDoctor) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(reportDoctor);
            em.flush();
            em.getTransaction().commit();
            return reportDoctor;
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
    public void edit(ReportDoctor reportDoctor) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(reportDoctor);
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
    public void remove(ReportDoctor reportDoctor) {
        em.getTransaction().begin();
        em.remove(reportDoctor);
        em.getTransaction().commit();
    }

    @Override
    public List<ReportDoctor> findAll() {
        return em.createNamedQuery("ReportDoctor.findAll").getResultList();
    }
}
