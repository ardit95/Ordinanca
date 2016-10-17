package bl;

import ExceptionPackage.AppException;
import ejb.Patient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PatientRepository extends EntMngClass implements PatientInterface {

    public PatientRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public Patient create(Patient patient) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(patient);
            em.getTransaction().commit();
            return patient;
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")) {
                    throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
                }
            } else {
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void edit(Patient patient) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(patient);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")) {
                    throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
                }
            } else {
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void remove(Patient patient) {
        em.getTransaction().begin();
        em.remove(patient);
        em.getTransaction().commit();
    }

    @Override
    public List<Patient> findAll() {
        return em.createNamedQuery("Patient.findAll").getResultList();
    }

    @Override
    public List<Patient> findByAll(String text) {
        Query query = em.createQuery("SELECT object (p) FROM Patient p WHERE p.name LIKE :txt OR p.surname LIKE :txt OR p.parentName LIKE :txt OR p.gender LIKE :txt OR p.phone LIKE :txt OR p.email LIKE :txt OR p.placeOFBirth LIKE :txt OR p.allergies LIKE :txt");
        query.setParameter("txt", "%" + text + "%");
        return (List<Patient>) query.getResultList();
    }

}
