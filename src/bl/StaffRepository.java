package bl;

import ExceptionPackage.AppException;
import ejb.Logs;
import ejb.Staff;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class StaffRepository extends EntMngClass implements StaffInterface{
    
    public StaffRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Staff create(Staff staff)throws AppException {
        try{
            em.getTransaction().begin();
            em.persist(staff);
            em.getTransaction().commit();
            return staff;
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
    public void edit(Staff staff) throws AppException {
        try{
            em.getTransaction().begin();
            em.merge(staff);
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
    public void remove(Staff staff) {
        em.getTransaction().begin();
        em.remove(staff);
        em.getTransaction().commit();
    }

    @Override
    public List<Staff> findAll() {
        return em.createNamedQuery ("Staff.findAll").getResultList();
    }

    
}
