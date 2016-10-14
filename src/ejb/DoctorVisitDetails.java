/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DoctorVisitDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorVisitDetails.findAll", query = "SELECT d FROM DoctorVisitDetails d"),
    @NamedQuery(name = "DoctorVisitDetails.findByDoctorVisitDetailID", query = "SELECT d FROM DoctorVisitDetails d WHERE d.DoctorVisitDetailID = :DoctorVisitDetailID"),
    @NamedQuery(name = "DoctorVisitDetails.findByPrice", query = "SELECT d FROM DoctorVisitDetails d WHERE d.price = :price"),
    @NamedQuery(name = "DoctorVisitDetails.findByComplaint", query = "SELECT d FROM DoctorVisitDetails d WHERE d.complaint = :complaint"),
    @NamedQuery(name = "DoctorVisitDetails.findByAnamnesis", query = "SELECT d FROM DoctorVisitDetails d WHERE d.anamnesis = :anamnesis"),
    @NamedQuery(name = "DoctorVisitDetails.findByExamination", query = "SELECT d FROM DoctorVisitDetails d WHERE d.examination = :examination"),
    @NamedQuery(name = "DoctorVisitDetails.findByTherapy", query = "SELECT d FROM DoctorVisitDetails d WHERE d.therapy = :therapy"),
    @NamedQuery(name = "DoctorVisitDetails.findByRecommendation", query = "SELECT d FROM DoctorVisitDetails d WHERE d.recommendation = :recommendation")})
public class DoctorVisitDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DoctorVisitDetailID")
    private Integer DoctorVisitDetailID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
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
    @JoinColumn(name = "DoctorVisitID", referencedColumnName = "DoctorVisitID")
    @ManyToOne(optional = false)
    private DoctorVisit DoctorVisitID;

    public DoctorVisitDetails() {
    }

    public DoctorVisitDetails(Integer DoctorVisitDetailID) {
        this.DoctorVisitDetailID = DoctorVisitDetailID;
    }

    public DoctorVisitDetails(Integer DoctorVisitDetailID, String complaint, String examination) {
        this.DoctorVisitDetailID = DoctorVisitDetailID;
        this.complaint = complaint;
        this.examination = examination;
    }

    public Integer getDoctorVisitDetailID() {
        return DoctorVisitDetailID;
    }

    public void setDoctorVisitDetailID(Integer DoctorVisitDetailID) {
        this.DoctorVisitDetailID = DoctorVisitDetailID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public DoctorVisit getDoctorVisitID() {
        return DoctorVisitID;
    }

    public void setDoctorVisitID(DoctorVisit DoctorVisitID) {
        this.DoctorVisitID = DoctorVisitID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DoctorVisitDetailID != null ? DoctorVisitDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorVisitDetails)) {
            return false;
        }
        DoctorVisitDetails other = (DoctorVisitDetails) object;
        if ((this.DoctorVisitDetailID == null && other.DoctorVisitDetailID != null) || (this.DoctorVisitDetailID != null && !this.DoctorVisitDetailID.equals(other.DoctorVisitDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.DoctorVisitDetails[ DoctorVisitDetailID=" + DoctorVisitDetailID + " ]";
    }
    
}
