/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ExceptionPackage.AppException;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "DoctorVisit.findByTimeStamp", query = "SELECT d FROM DoctorVisit d WHERE d.timeStamp = :timeStamp"),
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
    @Column(name = "timeStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SumPrice")
    private double sumPrice=0;
    @Column(name = "Remark")
    private String remark;
    @Column(name = "Finished")
    private String finished="No";
    @JoinColumn(name = "DoctorID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff doctorID;
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID")
    @ManyToOne
    private Patient patientID;
    @JoinColumn(name = "StaffID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff staffID;

    public DoctorVisit() {
    }
    
    public DoctorVisit(String remark,Staff doctor,Staff registererID)throws AppException{
        if(doctor==null)
            throw new AppException("The doctor cannot be null");
        if(registererID==null)
            throw new AppException("The registerer cannot be null");
        this.remark=remark;
        doctorID=doctor;
        staffID=registererID;
    }

    public DoctorVisit(Integer DoctorVisitID) {
        this.DoctorVisitID = DoctorVisitID;
    }

    public Integer getDoctorVisitID() {
        return DoctorVisitID;
    }

    public void setDoctorVisitID(Integer DoctorVisitID) {
        this.DoctorVisitID = DoctorVisitID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp){
        this.timeStamp = timeStamp;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
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
