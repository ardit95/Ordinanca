/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.AnalysisForVisit;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "analysis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT a FROM Analysis a"),
    @NamedQuery(name = "Analysis.findByAnalysisID", query = "SELECT a FROM Analysis a WHERE a.analysisID = :analysisID"),
    @NamedQuery(name = "Analysis.findByAnalysis", query = "SELECT a FROM Analysis a WHERE a.analysis = :analysis"),
    @NamedQuery(name = "Analysis.findByResults", query = "SELECT a FROM Analysis a WHERE a.results = :results")})
public class Analysis implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analysisID")
    private Collection<AnalysisForVisit> AnalysisForVisitCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnalysisID")
    private Integer analysisID;
    @Basic(optional = false)
    @Column(name = "Analysis")
    private String analysis;
    @Basic(optional = false)
    @Column(name = "Results")
    private String results;

    public Analysis() {
    }

    public Analysis(Integer analysisID) {
        this.analysisID = analysisID;
    }

    public Analysis(Integer analysisID, String analysis, String results) {
        this.analysisID = analysisID;
        this.analysis = analysis;
        this.results = results;
    }

    public Integer getAnalysisID() {
        return analysisID;
    }

    public void setAnalysisID(Integer analysisID) {
        this.analysisID = analysisID;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    @XmlTransient
    public Collection<AnalysisForVisit> getAnalysisForVisitCollection() {
        return AnalysisForVisitCollection;
    }

    public void setAnalysisForVisitCollection(Collection<AnalysisForVisit> AnalysisForVisitCollection) {
        this.AnalysisForVisitCollection = AnalysisForVisitCollection;
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
