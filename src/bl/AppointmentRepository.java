package bl;

import ejb.Appointment;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class AppointmentRepository extends EntMngClass implements AppointmentInterface{
     
    public AppointmentRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Appointment create(Appointment appointment)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(appointment);
        em.flush();
        em.getTransaction().commit();
        return appointment;
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
    public void edit(Appointment appointment)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(appointment);
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
    public void remove(Appointment appointment) {
        em.getTransaction().begin();
        em.remove(appointment);
        em.getTransaction().commit();
    }

    @Override
    public List<Appointment> findAll() {
        return em.createNamedQuery("AppointmentRepository.findAll").getResultList();
    }    
}
