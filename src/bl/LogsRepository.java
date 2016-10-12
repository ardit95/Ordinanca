package bl;

import ExceptionPackage.AppException;
import ejb.Logs;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LogsRepository extends EntMngClass implements LogsInterface{
    
    public LogsRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Logs create(Logs logs)throws AppException {
        try{
            em.getTransaction().begin();
            em.persist(logs);
            em.getTransaction().commit();
            return logs;
            }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(Logs logs) throws AppException {
        try{
            em.getTransaction().begin();
            em.merge(logs);
            em.getTransaction().commit();
            }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(Logs logs) {
        em.getTransaction().begin();
        em.remove(logs);
        em.getTransaction().commit();
    }

    @Override
    public List<Logs> findAll() {
        return em.createNamedQuery ("Logs.findAll").getResultList();
    }
    
    @Override
    public Date findDate(){
        Query query=em.createNativeQuery("SELECT CURRENT_TIMESTAMP");
        return (Date)query.getSingleResult();
    }
}

