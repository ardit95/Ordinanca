/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARDITI
 */
@Entity
@Table(name = "doctorvisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctorvisit.findAll", query = "SELECT d FROM Doctorvisit d"),
    @NamedQuery(name = "Doctorvisit.findByDoctorVisitID", query = "SELECT d FROM Doctorvisit d WHERE d.doctorVisitID = :doctorVisitID"),
    @NamedQuery(name = "Doctorvisit.findByDate", query = "SELECT d FROM Doctorvisit d WHERE d.date = :date"),
    @NamedQuery(name = "Doctorvisit.findByTime", query = "SELECT d FROM Doctorvisit d WHERE d.time = :time"),
    @NamedQuery(name = "Doctorvisit.findByPrice", query = "SELECT d FROM Doctorvisit d WHERE d.price = :price"),
    @NamedQuery(name = "Doctorvisit.findByTherapy", query = "SELECT d FROM Doctorvisit d WHERE d.therapy = :therapy"),
    @NamedQuery(name = "Doctorvisit.findByRecommendation", query = "SELECT d FROM Doctorvisit d WHERE d.recommendation = :recommendation")})
public class DoctorVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DoctorVisitID")
    private Integer doctorVisitID;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Time")
    @Temporal(TemporalType.TIME)
    private Date time;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "Therapy")
    private String therapy;
    @Column(name = "Recommendation")
    private String recommendation;
    @JoinColumn(name = "AnamnesisExaminationComplaintID", referencedColumnName = "AnamnesisExaminationComplaintID")
    @ManyToOne(optional = false)
    private AnamnesisExaminationComplaint anamnesisExaminationComplaintID;
    @JoinColumn(name = "NotificationID", referencedColumnName = "NotificationID")
    @OneToOne(optional = false)
    private Notification notificationID;

    public DoctorVisit() {
    }

    public DoctorVisit(Integer doctorVisitID) {
        this.doctorVisitID = doctorVisitID;
    }

    public DoctorVisit(Integer doctorVisitID, Date date, Date time, BigDecimal price) {
        this.doctorVisitID = doctorVisitID;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public Integer getDoctorVisitID() {
        return doctorVisitID;
    }

    public void setDoctorVisitID(Integer doctorVisitID) {
        this.doctorVisitID = doctorVisitID;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public AnamnesisExaminationComplaint getAnamnesisExaminationComplaintID() {
        return anamnesisExaminationComplaintID;
    }

    public void setAnamnesisExaminationComplaintID(AnamnesisExaminationComplaint anamnesisExaminationComplaintID) {
        this.anamnesisExaminationComplaintID = anamnesisExaminationComplaintID;
    }

    public Notification getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Notification notificationID) {
        this.notificationID = notificationID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doctorVisitID != null ? doctorVisitID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorVisit)) {
            return false;
        }
        DoctorVisit other = (DoctorVisit) object;
        if ((this.doctorVisitID == null && other.doctorVisitID != null) || (this.doctorVisitID != null && !this.doctorVisitID.equals(other.doctorVisitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Doctorvisit[ doctorVisitID=" + doctorVisitID + " ]";
    }
    
}
