
package bl;

import ejb.DoctorVisitDetails;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class DoctorVisitDetailsRepository extends EntMngClass implements DoctorVisitDetailsInterface{
     
    public DoctorVisitDetailsRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public DoctorVisitDetails create(DoctorVisitDetails DoctorVisitDetails)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(DoctorVisitDetails);
        em.flush();
        em.getTransaction().commit();
        return DoctorVisitDetails;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kompani me këto vlera.Secila kompani duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kompani me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(DoctorVisitDetails DoctorVisitDetails)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(DoctorVisitDetails);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kompani me këto vlera.Secila kompani duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kompani me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(DoctorVisitDetails DoctorVisitDetails) {
        em.getTransaction().begin();
        em.remove(DoctorVisitDetails);
        em.getTransaction().commit();
    }

    @Override
    public List<DoctorVisitDetails> findAll() {
        return em.createNamedQuery("DoctorVisitDetailsRepository.findAll").getResultList();
    }    
}