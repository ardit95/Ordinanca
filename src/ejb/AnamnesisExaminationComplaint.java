/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ARDITI
 */
@Entity
@Table(name = "anamnesisexaminationcomplaint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findAll", query = "SELECT a FROM Anamnesisexaminationcomplaint a"),
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findByAnamnesisExaminationComplaintID", query = "SELECT a FROM Anamnesisexaminationcomplaint a WHERE a.anamnesisExaminationComplaintID = :anamnesisExaminationComplaintID"),
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findByDate", query = "SELECT a FROM Anamnesisexaminationcomplaint a WHERE a.date = :date"),
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findByTime", query = "SELECT a FROM Anamnesisexaminationcomplaint a WHERE a.time = :time"),
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findByComplaintTitle", query = "SELECT a FROM Anamnesisexaminationcomplaint a WHERE a.complaintTitle = :complaintTitle"),
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findByComplaint", query = "SELECT a FROM Anamnesisexaminationcomplaint a WHERE a.complaint = :complaint"),
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findByAnamnesis", query = "SELECT a FROM Anamnesisexaminationcomplaint a WHERE a.anamnesis = :anamnesis"),
    @NamedQuery(name = "Anamnesisexaminationcomplaint.findByExamination", query = "SELECT a FROM Anamnesisexaminationcomplaint a WHERE a.examination = :examination")})
public class AnamnesisExaminationComplaint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnamnesisExaminationComplaintID")
    private Integer anamnesisExaminationComplaintID;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Time")
    @Temporal(TemporalType.TIME)
    private Date time;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anamnesisExaminationComplaintID")
    private Collection<DoctorVisit> doctorvisitCollection;

    public AnamnesisExaminationComplaint() {
    }

    public AnamnesisExaminationComplaint(Integer anamnesisExaminationComplaintID) {
        this.anamnesisExaminationComplaintID = anamnesisExaminationComplaintID;
    }

    public AnamnesisExaminationComplaint(Integer anamnesisExaminationComplaintID, Date date, Date time, String complaintTitle, String complaint, String anamnesis, String examination) {
        this.anamnesisExaminationComplaintID = anamnesisExaminationComplaintID;
        this.date = date;
        this.time = time;
        this.complaintTitle = complaintTitle;
        this.complaint = complaint;
        this.anamnesis = anamnesis;
        this.examination = examination;
    }

    public Integer getAnamnesisExaminationComplaintID() {
        return anamnesisExaminationComplaintID;
    }

    public void setAnamnesisExaminationComplaintID(Integer anamnesisExaminationComplaintID) {
        this.anamnesisExaminationComplaintID = anamnesisExaminationComplaintID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
    public Collection<DoctorVisit> getDoctorvisitCollection() {
        return doctorvisitCollection;
    }

    public void setDoctorvisitCollection(Collection<DoctorVisit> doctorvisitCollection) {
        this.doctorvisitCollection = doctorvisitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anamnesisExaminationComplaintID != null ? anamnesisExaminationComplaintID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnamnesisExaminationComplaint)) {
            return false;
        }
        AnamnesisExaminationComplaint other = (AnamnesisExaminationComplaint) object;
        if ((this.anamnesisExaminationComplaintID == null && other.anamnesisExaminationComplaintID != null) || (this.anamnesisExaminationComplaintID != null && !this.anamnesisExaminationComplaintID.equals(other.anamnesisExaminationComplaintID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Anamnesisexaminationcomplaint[ anamnesisExaminationComplaintID=" + anamnesisExaminationComplaintID + " ]";
    }
    
}
