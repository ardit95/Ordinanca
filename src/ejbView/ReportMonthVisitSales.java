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
@Table(name = "report_month_visit_sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportMonthVisitSales.findAll", query = "SELECT r FROM ReportMonthVisitSales r"),
    @NamedQuery(name = "ReportMonthVisitSales.findByViti", query = "SELECT r FROM ReportMonthVisitSales r WHERE r.viti = :viti"),
    @NamedQuery(name = "ReportMonthVisitSales.findByMuaji", query = "SELECT r FROM ReportMonthVisitSales r WHERE r.muaji = :muaji"),
    @NamedQuery(name = "ReportMonthVisitSales.findByEmriiDoktorit", query = "SELECT r FROM ReportMonthVisitSales r WHERE r.emriiDoktorit = :emriiDoktorit"),
    @NamedQuery(name = "ReportMonthVisitSales.findByMbiemriiDoktorit", query = "SELECT r FROM ReportMonthVisitSales r WHERE r.mbiemriiDoktorit = :mbiemriiDoktorit"),
    @NamedQuery(name = "ReportMonthVisitSales.findByNumriiVizitave", query = "SELECT r FROM ReportMonthVisitSales r WHERE r.numriiVizitave = :numriiVizitave"),
    @NamedQuery(name = "ReportMonthVisitSales.findByShitje", query = "SELECT r FROM ReportMonthVisitSales r WHERE r.shitje = :shitje")})
public class ReportMonthVisitSales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Viti")
    private Integer viti;
    @Column(name = "Muaji")
    @Id
    private Integer muaji;
    @Column(name = "Emri_i_Doktorit")
    private String emriiDoktorit;
    @Column(name = "Mbiemri_i_Doktorit")
    private String mbiemriiDoktorit;
    @Basic(optional = false)
    @Column(name = "Numri_i_Vizitave")
    private long numriiVizitave;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Shitje")
    private BigDecimal shitje;

    public ReportMonthVisitSales() {
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

    public String getEmriiDoktorit() {
        return emriiDoktorit;
    }

    public void setEmriiDoktorit(String emriiDoktorit) {
        this.emriiDoktorit = emriiDoktorit;
    }

    public String getMbiemriiDoktorit() {
        return mbiemriiDoktorit;
    }

    public void setMbiemriiDoktorit(String mbiemriiDoktorit) {
        this.mbiemriiDoktorit = mbiemriiDoktorit;
    }

    public long getNumriiVizitave() {
        return numriiVizitave;
    }

    public void setNumriiVizitave(long numriiVizitave) {
        this.numriiVizitave = numriiVizitave;
    }

    public BigDecimal getShitje() {
        return shitje;
    }

    public void setShitje(BigDecimal shitje) {
        this.shitje = shitje;
    }
    
}
