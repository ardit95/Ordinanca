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
@Table(name = "DoctorVisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorVisit.findAll", query = "SELECT d FROM DoctorVisit d"),
    @NamedQuery(name = "DoctorVisit.findByDoctorVisitID", query = "SELECT d FROM DoctorVisit d WHERE d.DoctorVisitID = :DoctorVisitID"),
    @NamedQuery(name = "DoctorVisit.findByDate", query = "SELECT d FROM DoctorVisit d WHERE d.date = :date"),
    @NamedQuery(name = "DoctorVisit.findBySumPrice", query = "SELECT d FROM DoctorVisit d WHERE d.sumPrice = :sumPrice"),
    @NamedQuery(name = "DoctorVisit.findByRemark", query = "SELECT d FROM DoctorVisit d WHERE d.remark = :remark"),
    @NamedQuery(name = "DoctorVisit.findByFinished", query = "SELECT d FROM DoctorVisit d WHERE d.finished = :finished")})
public class DoctorVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DoctorVisitID")
    private Integer DoctorVisitID;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SumPrice")
    private BigDecimal sumPrice;
    @Column(name = "Remark")
    private String remark;
    @Column(name = "Finished")
    private String finished;
    @JoinColumn(name = "DoctorID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff doctorID;
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID")
    @ManyToOne
    private Patient patientID;
    @JoinColumn(name = "StaffID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff staffID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "DoctorVisitID")
    private Collection<Diagnosis> diagnosisCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "DoctorVisitID")
    private Collection<DiagnosisForVisit> DiagnosisForVisitCollection;

    public DoctorVisit() {
    }

    public DoctorVisit(Integer DoctorVisitID) {
        this.DoctorVisitID = DoctorVisitID;
    }

    public DoctorVisit(Integer DoctorVisitID, Date date) {
        this.DoctorVisitID = DoctorVisitID;
        this.date = date;
    }

    public Integer getDoctorVisitID() {
        return DoctorVisitID;
    }

    public void setDoctorVisitID(Integer DoctorVisitID) {
        this.DoctorVisitID = DoctorVisitID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Staff getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Staff doctorID) {
        this.doctorID = doctorID;
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
    public Collection<Diagnosis> getDiagnosisCollection() {
        return diagnosisCollection;
    }

    public void setDiagnosisCollection(Collection<Diagnosis> diagnosisCollection) {
        this.diagnosisCollection = diagnosisCollection;
    }

    @XmlTransient
    public Collection<DiagnosisForVisit> getDiagnosisForVisitCollection() {
        return DiagnosisForVisitCollection;
    }

    public void setDiagnosisForVisitCollection(Collection<DiagnosisForVisit> DiagnosisForVisitCollection) {
        this.DiagnosisForVisitCollection = DiagnosisForVisitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DoctorVisitID != null ? DoctorVisitID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorVisit)) {
            return false;
        }
        DoctorVisit other = (DoctorVisit) object;
        if ((this.DoctorVisitID == null && other.DoctorVisitID != null) || (this.DoctorVisitID != null && !this.DoctorVisitID.equals(other.DoctorVisitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.DoctorVisit[ DoctorVisitID=" + DoctorVisitID + " ]";
    }
    
}
