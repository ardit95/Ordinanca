package bl;

import ExceptionPackage.AppException;
import ejb.Message;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MessageRepository extends EntMngClass implements MessageInterface{
    
    public MessageRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Message create(Message message)throws AppException {
        try{
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
            return message;
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
    public void edit(Message message) throws AppException {
        try{
            em.getTransaction().begin();
            em.merge(message);
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
    public void remove(Message message) {
        em.getTransaction().begin();
        em.remove(message);
        em.getTransaction().commit();
    }

    @Override
    public List<Message> findAll() {
        return em.createNamedQuery ("Message.findAll").getResultList();
    }
    
}

