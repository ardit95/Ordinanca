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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "DiagnosisForVisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosisForVisit.findAll", query = "SELECT d FROM DiagnosisForVisit d"),
    @NamedQuery(name = "DiagnosisForVisit.findByDiagnosisForVisitID", query = "SELECT d FROM DiagnosisForVisit d WHERE d.DiagnosisForVisitID = :DiagnosisForVisitID"),
    @NamedQuery(name = "DiagnosisForVisit.findByPrice", query = "SELECT d FROM DiagnosisForVisit d WHERE d.price = :price")})
public class DiagnosisForVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DiagnosisForVisitID")
    private Integer DiagnosisForVisitID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Price")
    private double price;
    @JoinColumn(name = "DiagnosisID", referencedColumnName = "DiagnosisID")
    @ManyToOne(optional = false)
    private Diagnosis diagnosisID;
    @JoinColumn(name = "DoctorVisitID", referencedColumnName = "DoctorVisitID")
    @ManyToOne(optional = false)
    private DoctorVisit doctorVisitID;

    public DiagnosisForVisit() {
    }

    public DiagnosisForVisit(Diagnosis diagnosisID,DoctorVisit doctorVisitID,double price)throws AppException{
        if(diagnosisID==null)
            throw new AppException("The diagnosis cannot be null");
        if(doctorVisitID==null)
            throw new AppException("The doctorVisit cannot be null");
        if(price==0)
            throw new AppException("The price cannot be null");
        this.diagnosisID=diagnosisID;
        this.doctorVisitID=doctorVisitID;
        this.price=price;
        
    }

    public Integer getDiagnosisForVisitID() {
        return DiagnosisForVisitID;
    }

    public void setDiagnosisForVisitID(Integer DiagnosisForVisitID) {
        this.DiagnosisForVisitID = DiagnosisForVisitID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Diagnosis getDiagnosisID() {
        return diagnosisID;
    }

    public void setDiagnosisID(Diagnosis diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public DoctorVisit getDoctorVisitID() {
        return doctorVisitID;
    }

    public void setDoctorVisitID(DoctorVisit doctorVisitID) {
        this.doctorVisitID = doctorVisitID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DiagnosisForVisitID != null ? DiagnosisForVisitID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosisForVisit)) {
            return false;
        }
        DiagnosisForVisit other = (DiagnosisForVisit) object;
        if ((this.DiagnosisForVisitID == null && other.DiagnosisForVisitID != null) || (this.DiagnosisForVisitID != null && !this.DiagnosisForVisitID.equals(other.DiagnosisForVisitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.DiagnosisForVisit[ DiagnosisForVisitID=" + DiagnosisForVisitID + " ]";
    }

}
