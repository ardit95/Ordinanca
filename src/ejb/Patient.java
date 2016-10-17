/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByPatientID", query = "SELECT p FROM Patient p WHERE p.patientID = :patientID"),
    @NamedQuery(name = "Patient.findByName", query = "SELECT p FROM Patient p WHERE p.name = :name"),
    @NamedQuery(name = "Patient.findBySurname", query = "SELECT p FROM Patient p WHERE p.surname = :surname"),
    @NamedQuery(name = "Patient.findByParentName", query = "SELECT p FROM Patient p WHERE p.parentName = :parentName"),
    @NamedQuery(name = "Patient.findByGender", query = "SELECT p FROM Patient p WHERE p.gender = :gender"),
    @NamedQuery(name = "Patient.findByDateOfBirth", query = "SELECT p FROM Patient p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Patient.findByPlaceOFBirth", query = "SELECT p FROM Patient p WHERE p.placeOFBirth = :placeOFBirth"),
    @NamedQuery(name = "Patient.findByCity", query = "SELECT p FROM Patient p WHERE p.city = :city"),
    @NamedQuery(name = "Patient.findByPhone", query = "SELECT p FROM Patient p WHERE p.phone = :phone"),
    @NamedQuery(name = "Patient.findByEmail", query = "SELECT p FROM Patient p WHERE p.email = :email"),
    @NamedQuery(name = "Patient.findByAllergies", query = "SELECT p FROM Patient p WHERE p.allergies = :allergies")})
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PatientID")
    private Integer patientID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "ParentName")
    private String parentName;
    @Basic(optional = false)
    @Column(name = "Gender")
    private String gender;
    @Basic(optional = false)
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "PlaceOFBirth")
    private String placeOFBirth;
    @Column(name = "City")
    private String city;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Allergies")
    private String allergies;
    @OneToMany(mappedBy = "patientID")
    private Collection<DoctorVisit> doctorvisitCollection;
    @OneToMany(mappedBy = "patientID")
    private Collection<AnalysisVisit> analysisvisitCollection;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne
    private Staff username;

    public Patient() {
    }

    public Patient(Integer patientID) {
        this.patientID = patientID;
    }

    public Patient(Integer patientID, String name, String surname, String parentName, String gender, Date dateOfBirth) {
        this.patientID = patientID;
        this.name = name;
        this.surname = surname;
        this.parentName = parentName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public String getPlaceOFBirth() {
        return placeOFBirth;
    }

    public void setPlaceOFBirth(String placeOFBirth) {
        this.placeOFBirth = placeOFBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    @XmlTransient
    public Collection<DoctorVisit> getDoctorvisitCollection() {
        return doctorvisitCollection;
    }

    public void setDoctorvisitCollection(Collection<DoctorVisit> doctorvisitCollection) {
        this.doctorvisitCollection = doctorvisitCollection;
    }

    @XmlTransient
    public Collection<AnalysisVisit> getAnalysisvisitCollection() {
        return analysisvisitCollection;
    }

    public void setAnalysisvisitCollection(Collection<AnalysisVisit> analysisvisitCollection) {
        this.analysisvisitCollection = analysisvisitCollection;
    }

    public Staff getUsername() {
        return username;
    }

    public void setUsername(Staff username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientID != null ? patientID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.patientID == null && other.patientID != null) || (this.patientID != null && !this.patientID.equals(other.patientID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Patient[ patientID=" + patientID + " ]";
    }

}
