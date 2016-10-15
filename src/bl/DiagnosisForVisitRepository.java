
package bl;

import ejb.DiagnosisForVisit;
import java.util.List;
import javax.persistence.EntityManager;
import ExceptionPackage.AppException;

public class DiagnosisForVisitRepository extends EntMngClass implements DiagnosisForVisitInterface{
     
    public DiagnosisForVisitRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public DiagnosisForVisit create(DiagnosisForVisit DiagnosisForVisit)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(DiagnosisForVisit);
        em.flush();
        em.getTransaction().commit();
        return DiagnosisForVisit;
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
    public void edit(DiagnosisForVisit DiagnosisForVisit)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(DiagnosisForVisit);
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
    public void remove(DiagnosisForVisit DiagnosisForVisit) {
        em.getTransaction().begin();
        em.remove(DiagnosisForVisit);
        em.getTransaction().commit();
    }

    @Override
    public List<DiagnosisForVisit> findAll() {
        return em.createNamedQuery("DiagnosisForVisitRepository.findAll").getResultList();
    }    
}

