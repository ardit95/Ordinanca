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
 * @author Besniku
 */
@Entity
@Table(name = "DoctorVisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorVisit.findAll", query = "SELECT d FROM DoctorVisit d"),
    @NamedQuery(name = "DoctorVisit.findByDoctorVisitID", query = "SELECT d FROM DoctorVisit d WHERE d.DoctorVisitID = :DoctorVisitID"),
    @NamedQuery(name = "DoctorVisit.findByDate", query = "SELECT d FROM DoctorVisit d WHERE d.date = :date"),
    @NamedQuery(name = "DoctorVisit.findByTime", query = "SELECT d FROM DoctorVisit d WHERE d.time = :time"),
    @NamedQuery(name = "DoctorVisit.findByPrice", query = "SELECT d FROM DoctorVisit d WHERE d.price = :price"),
    @NamedQuery(name = "DoctorVisit.findByTherapy", query = "SELECT d FROM DoctorVisit d WHERE d.therapy = :therapy"),
    @NamedQuery(name = "DoctorVisit.findByRecommendation", query = "SELECT d FROM DoctorVisit d WHERE d.recommendation = :recommendation")})
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
    private AnamnesisExaminationComplaint AnamnesisExaminationComplaintID;
    @JoinColumn(name = "NotificationID", referencedColumnName = "NotificationID")
    @OneToOne(optional = false)
    private Notification notificationID;

    public DoctorVisit() {
    }

    public DoctorVisit(Integer DoctorVisitID) {
        this.DoctorVisitID = DoctorVisitID;
    }

    public DoctorVisit(Integer DoctorVisitID, Date date, Date time, BigDecimal price) {
        this.DoctorVisitID = DoctorVisitID;
        this.date = date;
        this.time = time;
        this.price = price;
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
        return AnamnesisExaminationComplaintID;
    }

    public void setAnamnesisExaminationComplaintID(AnamnesisExaminationComplaint AnamnesisExaminationComplaintID) {
        this.AnamnesisExaminationComplaintID = AnamnesisExaminationComplaintID;
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
