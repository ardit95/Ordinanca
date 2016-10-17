/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "AnalysisForVisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalysisForVisit.findAll", query = "SELECT a FROM AnalysisForVisit a"),
    @NamedQuery(name = "AnalysisForVisit.findByAnalysisForVisitID", query = "SELECT a FROM AnalysisForVisit a WHERE a.AnalysisForVisitID = :AnalysisForVisitID"),
    @NamedQuery(name = "AnalysisForVisit.findByPrice", query = "SELECT a FROM AnalysisForVisit a WHERE a.price = :price")})
public class AnalysisForVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnalysisForVisitID")
    private Integer AnalysisForVisitID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @JoinColumn(name = "AnalysisID", referencedColumnName = "AnalysisID")
    @ManyToOne(optional = false)
    private Analysis analysisID;
    @JoinColumn(name = "AnalysisVisitID", referencedColumnName = "AnalysisVisitID")
    @ManyToOne(optional = false)
    private AnalysisVisit analysisVisitID;

    public AnalysisForVisit() {
    }

    public AnalysisForVisit(Integer AnalysisForVisitID) {
        this.AnalysisForVisitID = AnalysisForVisitID;
    }

    public Integer getAnalysisForVisitID() {
        return AnalysisForVisitID;
    }

    public void setAnalysisForVisitID(Integer AnalysisForVisitID) {
        this.AnalysisForVisitID = AnalysisForVisitID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Analysis getAnalysisID() {
        return analysisID;
    }

    public void setAnalysisID(Analysis analysisID) {
        this.analysisID = analysisID;
    }

    public AnalysisVisit getAnalysisVisitID() {
        return analysisVisitID;
    }

    public void setAnalysisVisitID(AnalysisVisit analysisVisitID) {
        this.analysisVisitID = analysisVisitID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (AnalysisForVisitID != null ? AnalysisForVisitID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnalysisForVisit)) {
            return false;
        }
        AnalysisForVisit other = (AnalysisForVisit) object;
        if ((this.AnalysisForVisitID == null && other.AnalysisForVisitID != null) || (this.AnalysisForVisitID != null && !this.AnalysisForVisitID.equals(other.AnalysisForVisitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.AnalysisForVisit[ AnalysisForVisitID=" + AnalysisForVisitID + " ]";
    }

}
