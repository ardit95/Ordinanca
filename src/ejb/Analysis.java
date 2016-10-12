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
@Table(name = "analysis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT a FROM Analysis a"),
    @NamedQuery(name = "Analysis.findByAnalysisID", query = "SELECT a FROM Analysis a WHERE a.analysisID = :analysisID"),
    @NamedQuery(name = "Analysis.findByDate", query = "SELECT a FROM Analysis a WHERE a.date = :date"),
    @NamedQuery(name = "Analysis.findByTime", query = "SELECT a FROM Analysis a WHERE a.time = :time"),
    @NamedQuery(name = "Analysis.findByPrice", query = "SELECT a FROM Analysis a WHERE a.price = :price"),
    @NamedQuery(name = "Analysis.findByAnalysis", query = "SELECT a FROM Analysis a WHERE a.analysis = :analysis")})
public class Analysis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnalysisID")
    private Integer analysisID;
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
    @Column(name = "Analysis")
    private String analysis;
    @JoinColumn(name = "NotificationID", referencedColumnName = "NotificationID")
    @OneToOne(optional = false)
    private Notification notificationID;

    public Analysis() {
    }

    public Analysis(Integer analysisID) {
        this.analysisID = analysisID;
    }

    public Analysis(Integer analysisID, Date date, Date time, BigDecimal price) {
        this.analysisID = analysisID;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public Integer getAnalysisID() {
        return analysisID;
    }

    public void setAnalysisID(Integer analysisID) {
        this.analysisID = analysisID;
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

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
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
        hash += (analysisID != null ? analysisID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analysis)) {
            return false;
        }
        Analysis other = (Analysis) object;
        if ((this.analysisID == null && other.analysisID != null) || (this.analysisID != null && !this.analysisID.equals(other.analysisID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Analysis[ analysisID=" + analysisID + " ]";
    }
    
}
