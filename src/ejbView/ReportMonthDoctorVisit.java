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
@Table(name = "report_month_doctorvisit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportMonthDoctorVisit.findAll", query = "SELECT r FROM ReportMonthDoctorVisit r"),
    @NamedQuery(name = "ReportMonthDoctorVisit.findByViti", query = "SELECT r FROM ReportMonthDoctorVisit r WHERE r.viti = :viti"),
    @NamedQuery(name = "ReportMonthDoctorVisit.findByMuaji", query = "SELECT r FROM ReportMonthDoctorVisit r WHERE r.muaji = :muaji"),
    @NamedQuery(name = "ReportMonthDoctorVisit.findByNumriiVizitave", query = "SELECT r FROM ReportMonthDoctorVisit r WHERE r.numriiVizitave = :numriiVizitave"),
    @NamedQuery(name = "ReportMonthDoctorVisit.findByNumriiPacienteve", query = "SELECT r FROM ReportMonthDoctorVisit r WHERE r.numriiPacienteve = :numriiPacienteve")})
public class ReportMonthDoctorVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Viti")
    private Integer viti;
    @Column(name = "Muaji")
    @Id
    private Integer muaji;
    @Basic(optional = false)
    @Column(name = "Numri_i_Vizitave")
    private long numriiVizitave;
    @Basic(optional = false)
    @Column(name = "Numri_i_Pacienteve")
    private long numriiPacienteve;

    public ReportMonthDoctorVisit() {
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

    public long getNumriiVizitave() {
        return numriiVizitave;
    }

    public void setNumriiVizitave(long numriiVizitave) {
        this.numriiVizitave = numriiVizitave;
    }

    public long getNumriiPacienteve() {
        return numriiPacienteve;
    }

    public void setNumriiPacienteve(long numriiPacienteve) {
        this.numriiPacienteve = numriiPacienteve;
    }
    
}
