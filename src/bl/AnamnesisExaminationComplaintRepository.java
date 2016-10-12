package bl;

import ejb.AnamnesisExaminationComplaint;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class AnamnesisExaminationComplaintRepository extends EntMngClass implements AnamnesisExaminationComplaintInterface{
     
    public AnamnesisExaminationComplaintRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public AnamnesisExaminationComplaint create(AnamnesisExaminationComplaint aec)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(aec);
        em.flush();
        em.getTransaction().commit();
        return aec;
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
    public void edit(AnamnesisExaminationComplaint aec)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(aec);
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
    public void remove(AnamnesisExaminationComplaint aec) {
        em.getTransaction().begin();
        em.remove(aec);
        em.getTransaction().commit();
    }

    @Override
    public List<AnamnesisExaminationComplaint> findAll() {
        return em.createNamedQuery("AnamnesisExaminationComplaint.findAll").getResultList();
    }    
}
