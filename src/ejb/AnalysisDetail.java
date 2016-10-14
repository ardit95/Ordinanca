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
@Table(name = "AnalysisDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalysisDetail.findAll", query = "SELECT a FROM AnalysisDetail a"),
    @NamedQuery(name = "AnalysisDetail.findByAnalysisDetailID", query = "SELECT a FROM AnalysisDetail a WHERE a.AnalysisDetailID = :AnalysisDetailID"),
    @NamedQuery(name = "AnalysisDetail.findByPrice", query = "SELECT a FROM AnalysisDetail a WHERE a.price = :price")})
public class AnalysisDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnalysisDetailID")
    private Integer AnalysisDetailID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @JoinColumn(name = "AnalysisID", referencedColumnName = "AnalysisID")
    @ManyToOne(optional = false)
    private Analysis analysisID;
    @JoinColumn(name = "AnalysisVisitID", referencedColumnName = "AnalysisVisitID")
    @ManyToOne(optional = false)
    private AnalysisVisit AnalysisVisitID;

    public AnalysisDetail() {
    }

    public AnalysisDetail(Integer AnalysisDetailID) {
        this.AnalysisDetailID = AnalysisDetailID;
    }

    public Integer getAnalysisDetailID() {
        return AnalysisDetailID;
    }

    public void setAnalysisDetailID(Integer AnalysisDetailID) {
        this.AnalysisDetailID = AnalysisDetailID;
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
        return AnalysisVisitID;
    }

    public void setAnalysisVisitID(AnalysisVisit AnalysisVisitID) {
        this.AnalysisVisitID = AnalysisVisitID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (AnalysisDetailID != null ? AnalysisDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnalysisDetail)) {
            return false;
        }
        AnalysisDetail other = (AnalysisDetail) object;
        if ((this.AnalysisDetailID == null && other.AnalysisDetailID != null) || (this.AnalysisDetailID != null && !this.AnalysisDetailID.equals(other.AnalysisDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.AnalysisDetail[ AnalysisDetailID=" + AnalysisDetailID + " ]";
    }
    
}
