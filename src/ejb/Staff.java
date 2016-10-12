/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findByUsername", query = "SELECT s FROM Staff s WHERE s.username = :username"),
    @NamedQuery(name = "Staff.findBySalt", query = "SELECT s FROM Staff s WHERE s.salt = :salt"),
    @NamedQuery(name = "Staff.findByName", query = "SELECT s FROM Staff s WHERE s.name = :name"),
    @NamedQuery(name = "Staff.findBySurname", query = "SELECT s FROM Staff s WHERE s.surname = :surname"),
    @NamedQuery(name = "Staff.findByGender", query = "SELECT s FROM Staff s WHERE s.gender = :gender"),
    @NamedQuery(name = "Staff.findByDateOfBirth", query = "SELECT s FROM Staff s WHERE s.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Staff.findByEducation", query = "SELECT s FROM Staff s WHERE s.education = :education"),
    @NamedQuery(name = "Staff.findBySpecialization", query = "SELECT s FROM Staff s WHERE s.specialization = :specialization"),
    @NamedQuery(name = "Staff.findByRole", query = "SELECT s FROM Staff s WHERE s.role = :role"),
    @NamedQuery(name = "Staff.findByNumberOfLogins", query = "SELECT s FROM Staff s WHERE s.numberOfLogins = :numberOfLogins"),
    @NamedQuery(name = "Staff.findByStatus", query = "SELECT s FROM Staff s WHERE s.status = :status")})
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Lob
    @Column(name = "Password")
    private byte[] password;
    @Basic(optional = false)
    @Column(name = "salt")
    private String salt;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "Gender")
    private String gender;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "Education")
    private String education;
    @Column(name = "Specialization")
    private String specialization;
    @Column(name = "Role")
    private String role;
    @Column(name = "NumberOfLogins")
    private Integer numberOfLogins;
    @Column(name = "Status")
    private String status;

    public Staff() {
    }

    public Staff(String username) {
        this.username = username;
    }

    public Staff(String username, byte[] password, String salt, String name, String surname, String gender) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getNumberOfLogins() {
        return numberOfLogins;
    }

    public void setNumberOfLogins(Integer numberOfLogins) {
        this.numberOfLogins = numberOfLogins;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Staff[ username=" + username + " ]";
    }
    
}
