package bl;

import ExceptionPackage.AppException;
import ejb.Notification;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NotificationRepository extends EntMngClass implements NotificationInterface{
    
    public NotificationRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Notification create(Notification notification)throws AppException {
        try{
            em.getTransaction().begin();
            em.persist(notification);
            em.getTransaction().commit();
            return notification;
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
    public void edit(Notification notification) throws AppException {
        try{
            em.getTransaction().begin();
            em.merge(notification);
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
    public void remove(Notification notification) {
        em.getTransaction().begin();
        em.remove(notification);
        em.getTransaction().commit();
    }

    @Override
    public List<Notification> findAll() {
        return em.createNamedQuery ("Notification.findAll").getResultList();
    }
    
}

