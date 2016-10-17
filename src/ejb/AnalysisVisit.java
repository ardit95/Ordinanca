/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Besniku
 */
@Entity
@Table(name = "AnalysisVisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalysisVisit.findAll", query = "SELECT a FROM AnalysisVisit a"),
    @NamedQuery(name = "AnalysisVisit.findByAnalysisVisitID", query = "SELECT a FROM AnalysisVisit a WHERE a.AnalysisVisitID = :AnalysisVisitID"),
    @NamedQuery(name = "AnalysisVisit.findByTimeStamp", query = "SELECT a FROM AnalysisVisit a WHERE a.timeStamp = :timeStamp"),
    @NamedQuery(name = "AnalysisVisit.findBySumPrice", query = "SELECT a FROM AnalysisVisit a WHERE a.sumPrice = :sumPrice"),
    @NamedQuery(name = "AnalysisVisit.findByRemark", query = "SELECT a FROM AnalysisVisit a WHERE a.remark = :remark"),
    @NamedQuery(name = "AnalysisVisit.findByFinished", query = "SELECT a FROM AnalysisVisit a WHERE a.finished = :finished")})
public class AnalysisVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnalysisVisitID")
    private Integer AnalysisVisitID;
    @Basic(optional = false)
    @Column(name = "timeStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SumPrice")
    private BigDecimal sumPrice;
    @Column(name = "Remark")
    private String remark;
    @Column(name = "Finished")
    private String finished;
    @JoinColumn(name = "LaboratorTechnicianID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff laboratorTechnicianID;
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID")
    @ManyToOne
    private Patient patientID;
    @JoinColumn(name = "StaffID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff staffID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "AnalysisVisitID")
    private Collection<AnalysisForVisit> analysisforvisitCollection;

    public AnalysisVisit() {
    }

    public AnalysisVisit(Integer AnalysisVisitID) {
        this.AnalysisVisitID = AnalysisVisitID;
    }

    public AnalysisVisit(Integer AnalysisVisitID, Date timeStamp) {
        this.AnalysisVisitID = AnalysisVisitID;
        this.timeStamp = timeStamp;
    }

    public Integer getAnalysisVisitID() {
        return AnalysisVisitID;
    }

    public void setAnalysisVisitID(Integer AnalysisVisitID) {
        this.AnalysisVisitID = AnalysisVisitID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public Staff getLaboratorTechnicianID() {
        return laboratorTechnicianID;
    }

    public void setLaboratorTechnicianID(Staff laboratorTechnicianID) {
        this.laboratorTechnicianID = laboratorTechnicianID;
    }

    public Patient getPatientID() {
        return patientID;
    }

    public void setPatientID(Patient patientID) {
        this.patientID = patientID;
    }

    public Staff getStaffID() {
        return staffID;
    }

    public void setStaffID(Staff staffID) {
        this.staffID = staffID;
    }

    @XmlTransient
    public Collection<AnalysisForVisit> getAnalysisforvisitCollection() {
        return analysisforvisitCollection;
    }

    public void setAnalysisforvisitCollection(Collection<AnalysisForVisit> analysisforvisitCollection) {
        this.analysisforvisitCollection = analysisforvisitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (AnalysisVisitID != null ? AnalysisVisitID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnalysisVisit)) {
            return false;
        }
        AnalysisVisit other = (AnalysisVisit) object;
        if ((this.AnalysisVisitID == null && other.AnalysisVisitID != null) || (this.AnalysisVisitID != null && !this.AnalysisVisitID.equals(other.AnalysisVisitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.AnalysisVisit[ AnalysisVisitID=" + AnalysisVisitID + " ]";
    }
    
}
