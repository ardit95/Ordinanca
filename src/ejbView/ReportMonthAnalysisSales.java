/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbView;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARDITI
 */
@Entity
@Table(name = "report_month_analysis_sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportMonthAnalysisSales.findAll", query = "SELECT r FROM ReportMonthAnalysisSales r"),
    @NamedQuery(name = "ReportMonthAnalysisSales.findByViti", query = "SELECT r FROM ReportMonthAnalysisSales r WHERE r.viti = :viti"),
    @NamedQuery(name = "ReportMonthAnalysisSales.findByMuaji", query = "SELECT r FROM ReportMonthAnalysisSales r WHERE r.muaji = :muaji"),
    @NamedQuery(name = "ReportMonthAnalysisSales.findByEmriiLaborantit", query = "SELECT r FROM ReportMonthAnalysisSales r WHERE r.emriiLaborantit = :emriiLaborantit"),
    @NamedQuery(name = "ReportMonthAnalysisSales.findByMbiemriiLaborantit", query = "SELECT r FROM ReportMonthAnalysisSales r WHERE r.mbiemriiLaborantit = :mbiemriiLaborantit"),
    @NamedQuery(name = "ReportMonthAnalysisSales.findByNumriiAnalizave", query = "SELECT r FROM ReportMonthAnalysisSales r WHERE r.numriiAnalizave = :numriiAnalizave"),
    @NamedQuery(name = "ReportMonthAnalysisSales.findByShitje", query = "SELECT r FROM ReportMonthAnalysisSales r WHERE r.shitje = :shitje")})
public class ReportMonthAnalysisSales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Viti")
    private Integer viti;
    @Column(name = "Muaji")
    @Id
    private Integer muaji;
    @Column(name = "Emri_i_Laborantit")
    private String emriiLaborantit;
    @Column(name = "Mbiemri_i_Laborantit")
    private String mbiemriiLaborantit;
    @Basic(optional = false)
    @Column(name = "Numri_i_Analizave")
    private long numriiAnalizave;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Shitje")
    private BigDecimal shitje;

    public ReportMonthAnalysisSales() {
    }

    public Integer getViti() {
        return viti;
    }

    public void setViti(Integer viti) {
        this.viti = viti;
    }

    public Integer getMuaji() {
        return muaji;
    }

    public void setMuaji(Integer muaji) {
        this.muaji = muaji;
    }

    public String getEmriiLaborantit() {
        return emriiLaborantit;
    }

    public void setEmriiLaborantit(String emriiLaborantit) {
        this.emriiLaborantit = emriiLaborantit;
    }

    public String getMbiemriiLaborantit() {
        return mbiemriiLaborantit;
    }

    public void setMbiemriiLaborantit(String mbiemriiLaborantit) {
        this.mbiemriiLaborantit = mbiemriiLaborantit;
    }

    public long getNumriiAnalizave() {
        return numriiAnalizave;
    }

    public void setNumriiAnalizave(long numriiAnalizave) {
        this.numriiAnalizave = numriiAnalizave;
    }

    public BigDecimal getShitje() {
        return shitje;
    }

    public void setShitje(BigDecimal shitje) {
        this.shitje = shitje;
    }
    
}
