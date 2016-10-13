/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "AnamnesisExaminationComplaint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnamnesisExaminationComplaint.findAll", query = "SELECT a FROM AnamnesisExaminationComplaint a"),
    @NamedQuery(name = "AnamnesisExaminationComplaint.findByAnamnesisExaminationComplaintID", query = "SELECT a FROM AnamnesisExaminationComplaint a WHERE a.AnamnesisExaminationComplaintID = :AnamnesisExaminationComplaintID"),
    @NamedQuery(name = "AnamnesisExaminationComplaint.findByComplaintTitle", query = "SELECT a FROM AnamnesisExaminationComplaint a WHERE a.complaintTitle = :complaintTitle"),
    @NamedQuery(name = "AnamnesisExaminationComplaint.findByComplaint", query = "SELECT a FROM AnamnesisExaminationComplaint a WHERE a.complaint = :complaint"),
    @NamedQuery(name = "AnamnesisExaminationComplaint.findByAnamnesis", query = "SELECT a FROM AnamnesisExaminationComplaint a WHERE a.anamnesis = :anamnesis"),
    @NamedQuery(name = "AnamnesisExaminationComplaint.findByExamination", query = "SELECT a FROM AnamnesisExaminationComplaint a WHERE a.examination = :examination")})
public class AnamnesisExaminationComplaint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnamnesisExaminationComplaintID")
    private Integer AnamnesisExaminationComplaintID;
    @Basic(optional = false)
    @Column(name = "ComplaintTitle")
    private String complaintTitle;
    @Basic(optional = false)
    @Column(name = "Complaint")
    private String complaint;
    @Basic(optional = false)
    @Column(name = "Anamnesis")
    private String anamnesis;
    @Basic(optional = false)
    @Column(name = "Examination")
    private String examination;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "AnamnesisExaminationComplaintID")
    private Collection<DoctorVisit> DoctorVisitCollection;

    public AnamnesisExaminationComplaint() {
    }

    public AnamnesisExaminationComplaint(Integer AnamnesisExaminationComplaintID) {
        this.AnamnesisExaminationComplaintID = AnamnesisExaminationComplaintID;
    }

    public AnamnesisExaminationComplaint(Integer AnamnesisExaminationComplaintID, String complaintTitle, String complaint, String anamnesis, String examination) {
        this.AnamnesisExaminationComplaintID = AnamnesisExaminationComplaintID;
        this.complaintTitle = complaintTitle;
        this.complaint = complaint;
        this.anamnesis = anamnesis;
        this.examination = examination;
    }

    public Integer getAnamnesisExaminationComplaintID() {
        return AnamnesisExaminationComplaintID;
    }

    public void setAnamnesisExaminationComplaintID(Integer AnamnesisExaminationComplaintID) {
        this.AnamnesisExaminationComplaintID = AnamnesisExaminationComplaintID;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
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

    @XmlTransient
    public Collection<DoctorVisit> getDoctorVisitCollection() {
        return DoctorVisitCollection;
    }

    public void setDoctorVisitCollection(Collection<DoctorVisit> DoctorVisitCollection) {
        this.DoctorVisitCollection = DoctorVisitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (AnamnesisExaminationComplaintID != null ? AnamnesisExaminationComplaintID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnamnesisExaminationComplaint)) {
            return false;
        }
        AnamnesisExaminationComplaint other = (AnamnesisExaminationComplaint) object;
        if ((this.AnamnesisExaminationComplaintID == null && other.AnamnesisExaminationComplaintID != null) || (this.AnamnesisExaminationComplaintID != null && !this.AnamnesisExaminationComplaintID.equals(other.AnamnesisExaminationComplaintID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.AnamnesisExaminationComplaint[ AnamnesisExaminationComplaintID=" + AnamnesisExaminationComplaintID + " ]";
    }
    
}
