/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
 * @author ARDITI
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByAppointmentID", query = "SELECT a FROM Appointment a WHERE a.appointmentID = :appointmentID"),
    @NamedQuery(name = "Appointment.findByName", query = "SELECT a FROM Appointment a WHERE a.name = :name"),
    @NamedQuery(name = "Appointment.findByDate", query = "SELECT a FROM Appointment a WHERE a.date = :date"),
    @NamedQuery(name = "Appointment.findByTime", query = "SELECT a FROM Appointment a WHERE a.time = :time"),
    @NamedQuery(name = "Appointment.findByType", query = "SELECT a FROM Appointment a WHERE a.type = :type")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AppointmentID")
    private Integer appointmentID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Column(name = "Type")
    private String type;
    @JoinColumn(name = "DoctorID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff doctorID;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff username;

    public Appointment() {
    }

    public Appointment(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Appointment(Integer appointmentID, String name, Date date, Date time) {
        this.appointmentID = appointmentID;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentID != null ? appointmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointmentID == null && other.appointmentID != null) || (this.appointmentID != null && !this.appointmentID.equals(other.appointmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Appointment[ appointmentID=" + appointmentID + " ]";
    }
    
}
