package bl;

import ejb.AnalysisDetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ExceptionPackage.AppException;

public class AnalysisDetailRepository extends EntMngClass implements AnalysisDetailInterface{
     
    public AnalysisDetailRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public AnalysisDetail create(AnalysisDetail analysisDetail)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(analysisDetail);
        em.flush();
        em.getTransaction().commit();
        return analysisDetail;
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
    public void edit(AnalysisDetail analysisDetail)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(analysisDetail);
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
    public void remove(AnalysisDetail analysisDetail) {
        em.getTransaction().begin();
        em.remove(analysisDetail);
        em.getTransaction().commit();
    }

    @Override
    public List<AnalysisDetail> findAll() {
        return em.createNamedQuery("AnalysisDetail.findAll").getResultList();
    }    
}
