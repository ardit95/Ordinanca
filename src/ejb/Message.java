/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ExceptionPackage.AppException;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByMessageID", query = "SELECT m FROM Message m WHERE m.messageID = :messageID"),
    @NamedQuery(name = "Message.findByTimeStamp", query = "SELECT m FROM Message m WHERE m.timeStamp = :timeStamp"),
    @NamedQuery(name = "Message.findByMessage", query = "SELECT m FROM Message m WHERE m.message = :message"),
    @NamedQuery(name = "Message.findBySeen", query = "SELECT m FROM Message m WHERE m.seen = :seen")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MessageID")
    private Integer messageID;
    @Basic(optional = false)
    @Column(name = "timeStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Basic(optional = false)
    @Column(name = "Message")
    private String message;
    @Column(name = "Seen")
    private String seen="No";
    @JoinColumn(name = "DoctorID", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff doctorID;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff username;

    public Message() {
    }

    public Message(Staff sender, Staff reciever, String message) throws AppException {
        if (sender == null) {
            throw new AppException("The sender cannot be null.");
        }
        if (reciever == null) {
            throw new AppException("The reciever cannot be null.");
        }
        if (message.trim().length() > 500) {
            throw new AppException("The message cannot contain more than 500 letters.");
        }
        doctorID = sender;
        username = reciever;
        this.message = message;
    }

    public Message(Integer messageID) {
        this.messageID = messageID;
    }

    public Message(Integer messageID, Date timeStamp, String message) {
        this.messageID = messageID;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public Integer getMessageID() {
        return messageID;
    }

    public void setMessageID(Integer messageID) {
        this.messageID = messageID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public Staff getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Staff doctorID) {
        this.doctorID = doctorID;
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
        hash += (messageID != null ? messageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageID == null && other.messageID != null) || (this.messageID != null && !this.messageID.equals(other.messageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Message[ messageID=" + messageID + " ]";
    }

}
