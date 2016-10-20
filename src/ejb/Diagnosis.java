/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ExceptionPackage.AppException;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARDITI
 */
@Entity
@Table(name = "diagnosis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnosis.findAll", query = "SELECT d FROM Diagnosis d"),
    @NamedQuery(name = "Diagnosis.findByDiagnosisID", query = "SELECT d FROM Diagnosis d WHERE d.diagnosisID = :diagnosisID"),
    @NamedQuery(name = "Diagnosis.findByComplaint", query = "SELECT d FROM Diagnosis d WHERE d.complaint = :complaint"),
    @NamedQuery(name = "Diagnosis.findByAnamnesis", query = "SELECT d FROM Diagnosis d WHERE d.anamnesis = :anamnesis"),
    @NamedQuery(name = "Diagnosis.findByExamination", query = "SELECT d FROM Diagnosis d WHERE d.examination = :examination"),
    @NamedQuery(name = "Diagnosis.findByTherapy", query = "SELECT d FROM Diagnosis d WHERE d.therapy = :therapy"),
    @NamedQuery(name = "Diagnosis.findByRecommendation", query = "SELECT d FROM Diagnosis d WHERE d.recommendation = :recommendation")})
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DiagnosisID")
    private Integer diagnosisID;
    @Basic(optional = false)
    @Column(name = "Complaint")
    private String complaint;
    @Column(name = "Anamnesis")
    private String anamnesis;
    @Basic(optional = false)
    @Column(name = "Examination")
    private String examination;
    @Column(name = "Therapy")
    private String therapy;
    @Column(name = "Recommendation")
    private String recommendation;

    public Diagnosis() {
    }
    
    public Diagnosis(String complaint, String examination,String anamnesis,String therapy,String recommendation) throws AppException{
        if(complaint.trim().isEmpty())
            throw new AppException("Complaint cannot be empty");
        if(examination.trim().isEmpty())
            throw new AppException("Examination cannot be empty");
        
        this.complaint = complaint;
        this.examination = examination;
        this.anamnesis=anamnesis;
        this.therapy=therapy;
        this.recommendation=recommendation;
    }

    public Integer getDiagnosisID() {
        return diagnosisID;
    }

    public void setDiagnosisID(Integer diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagnosisID != null ? diagnosisID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnosis)) {
            return false;
        }
        Diagnosis other = (Diagnosis) object;
        if ((this.diagnosisID == null && other.diagnosisID != null) || (this.diagnosisID != null && !this.diagnosisID.equals(other.diagnosisID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Diagnosis[ diagnosisID=" + diagnosisID + " ]";
    }
    
}
