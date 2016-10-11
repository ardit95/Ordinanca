/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByNotificationID", query = "SELECT n FROM Notification n WHERE n.notificationID = :notificationID"),
    @NamedQuery(name = "Notification.findByDate", query = "SELECT n FROM Notification n WHERE n.date = :date"),
    @NamedQuery(name = "Notification.findByTime", query = "SELECT n FROM Notification n WHERE n.time = :time"),
    @NamedQuery(name = "Notification.findByTypeOfVisit", query = "SELECT n FROM Notification n WHERE n.typeOfVisit = :typeOfVisit"),
    @NamedQuery(name = "Notification.findBySeen", query = "SELECT n FROM Notification n WHERE n.seen = :seen")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NotificationID")
    private Integer notificationID;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Basic(optional = false)
    @Column(name = "TypeOfVisit")
    private String typeOfVisit;
    @Column(name = "Seen")
    private String seen;
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID")
    @ManyToOne(optional = false)
    private Patient patientID;
    @JoinColumn(name = "DoctorID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff doctorID;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff username;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "notificationID")
    private Doctorvisit doctorvisit;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "notificationID")
    private Analysis analysis;

    public Notification() {
    }

    public Notification(Integer notificationID) {
        this.notificationID = notificationID;
    }

    public Notification(Integer notificationID, Date date, Date time, String typeOfVisit) {
        this.notificationID = notificationID;
        this.date = date;
        this.time = time;
        this.typeOfVisit = typeOfVisit;
    }

    public Integer getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
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

    public String getTypeOfVisit() {
        return typeOfVisit;
    }

    public void setTypeOfVisit(String typeOfVisit) {
        this.typeOfVisit = typeOfVisit;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public Patient getPatientID() {
        return patientID;
    }

    public void setPatientID(Patient patientID) {
        this.patientID = patientID;
    }

    public Staff getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Staff doctorID) {
        this.doctorID = doctorID;
    }

    public Staff getUsername() {
        return username;
    }

    public void setUsername(Staff username) {
        this.username = username;
    }

    public Doctorvisit getDoctorvisit() {
        return doctorvisit;
    }

    public void setDoctorvisit(Doctorvisit doctorvisit) {
        this.doctorvisit = doctorvisit;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationID != null ? notificationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationID == null && other.notificationID != null) || (this.notificationID != null && !this.notificationID.equals(other.notificationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Notification[ notificationID=" + notificationID + " ]";
    }
    
}
