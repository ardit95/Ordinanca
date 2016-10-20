/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbView;

import java.io.Serializable;
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
@Table(name = "report_month_analysisvisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportMonthAnalysisVisit.findAll", query = "SELECT r FROM ReportMonthAnalysisVisit r"),
    @NamedQuery(name = "ReportMonthAnalysisVisit.findByViti", query = "SELECT r FROM ReportMonthAnalysisVisit r WHERE r.viti = :viti"),
    @NamedQuery(name = "ReportMonthAnalysisVisit.findByMuaji", query = "SELECT r FROM ReportMonthAnalysisVisit r WHERE r.muaji = :muaji"),
    @NamedQuery(name = "ReportMonthAnalysisVisit.findByNumriiAnalizave", query = "SELECT r FROM ReportMonthAnalysisVisit r WHERE r.numriiAnalizave = :numriiAnalizave"),
    @NamedQuery(name = "ReportMonthAnalysisVisit.findByNumriiPacienteve", query = "SELECT r FROM ReportMonthAnalysisVisit r WHERE r.numriiPacienteve = :numriiPacienteve")})
public class ReportMonthAnalysisVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Viti")
    private Integer viti;
    @Column(name = "Muaji")
    @Id
    private Integer muaji;
    @Basic(optional = false)
    @Column(name = "Numri_i_Analizave")
    private long numriiAnalizave;
    @Basic(optional = false)
    @Column(name = "Numri_i_Pacienteve")
    private long numriiPacienteve;

    public ReportMonthAnalysisVisit() {
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

    public long getNumriiAnalizave() {
        return numriiAnalizave;
    }

    public void setNumriiAnalizave(long numriiAnalizave) {
        this.numriiAnalizave = numriiAnalizave;
    }

    public long getNumriiPacienteve() {
        return numriiPacienteve;
    }

    public void setNumriiPacienteve(long numriiPacienteve) {
        this.numriiPacienteve = numriiPacienteve;
    }
    
}
