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
@Table(name = "report_doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportDoctor.findAll", query = "SELECT r FROM ReportDoctor r"),
    @NamedQuery(name = "ReportDoctor.findByViti", query = "SELECT r FROM ReportDoctor r WHERE r.viti = :viti"),
    @NamedQuery(name = "ReportDoctor.findByMuaji", query = "SELECT r FROM ReportDoctor r WHERE r.muaji = :muaji"),
    @NamedQuery(name = "ReportDoctor.findByEmriiDoktorit", query = "SELECT r FROM ReportDoctor r WHERE r.emriiDoktorit = :emriiDoktorit"),
    @NamedQuery(name = "ReportDoctor.findByMbiemriiDoktorit", query = "SELECT r FROM ReportDoctor r WHERE r.mbiemriiDoktorit = :mbiemriiDoktorit"),
    @NamedQuery(name = "ReportDoctor.findByNumriiVizitave", query = "SELECT r FROM ReportDoctor r WHERE r.numriiVizitave = :numriiVizitave")})
public class ReportDoctor implements Serializable {

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

    public ReportDoctor() {
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
    
}
