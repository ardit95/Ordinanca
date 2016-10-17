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
@Table(name = "NumberOfStaff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NumberOfStaff.findAll", query = "SELECT n FROM NumberOfStaff n"),
    @NamedQuery(name = "NumberOfStaff.findByNumri", query = "SELECT n FROM NumberOfStaff n WHERE n.numri = :numri")})
public class NumberOfStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Numri")
    @Id
    private long numri;

    public NumberOfStaff() {
    }

    public long getNumri() {
        return numri;
    }

    public void setNumri(long numri) {
        this.numri = numri;
    }
    
}
