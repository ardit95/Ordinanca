package bl;

import ExceptionPackage.AppException;
import ejb.Message;
import ejb.Staff;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class MessageRepository extends EntMngClass implements MessageInterface {

    public MessageRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public Message create(Message message) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
            return message;
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")){
                    throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
                }
            } else {
                em.getTransaction().rollback();
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void edit(Message message) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(message);
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
    public void remove(Message message) {
        em.getTransaction().begin();
        em.remove(message);
        em.getTransaction().commit();
    }

    @Override
    public List<Message> findAll() {
        return em.createNamedQuery("Message.findAll").getResultList();
    }

    @Override
    public List<Message> findByReciever(Staff currentUser) {
        Query query = em.createQuery("SELECT object(message) FROM Message message WHERE message.username.username = :receiver AND message.seen='No'");
        query.setParameter("receiver", currentUser.getUsername());
        return (List<Message>) query.getResultList();
    }

    @Override
    public List<Message> findBySenderAndReciever(Staff reciever, Staff sender) {
        Query query = em.createQuery("SELECT Object (message) FROM Message message WHERE message.username.username = :reciever AND message.doctorID.username = :sender ORDER BY message.timeStamp desc ");
        query.setParameter("sender", sender.getUsername());
        query.setParameter("reciever", reciever.getUsername());
        return (List<Message>) query.getResultList();
    }

    @Override
    public int countUnseenMessagesForUser(Staff currentUser) {
        Query query = em.createQuery("SELECT Object(message) FROM Message message WHERE message.username.username = :currentU AND message.seen='No'");
        query.setParameter("currentU", currentUser.getUsername());
        try{
            return query.getResultList().size();
        }catch(NullPointerException npe){
            return 0;
        }
    }

    @Override
    public int countMessagesForUser(Staff currentUser) {
        Query query = em.createQuery("SELECT Object(message) FROM Message message WHERE message.username.username = :currentU  ");
        query.setParameter("currentU", currentUser.getUsername());
        return query.getResultList().size();
    }
    
    @Override
    public int countUnseenMessagesForSpecificUser(Staff currentUser,Staff messageFrom){
        Query query = em.createQuery("SELECT Object(message) FROM Message message WHERE message.username.username = :currentU AND message.doctorID.username = :messageF AND message.seen='No' ");
        query.setParameter("currentU", currentUser.getUsername());
        query.setParameter("messageF",messageFrom.getUsername());
        return query.getResultList().size();
    }
    
    @Override
    public List<Message> checkUnseenMessages(Staff currentUser){
        Query query=em.createQuery("SELECT Object (message) FROM Message message WHERE message.username.username= :currentU AND message.seen='No'");
        query.setParameter("currentU", currentUser.getUsername());
        return (List <Message>)query.getResultList();
    }
    
    

}
