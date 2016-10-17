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
@Table(name = "logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findByLogsID", query = "SELECT l FROM Logs l WHERE l.logsID = :logsID"),
    @NamedQuery(name = "Logs.findByTimeStamp", query = "SELECT l FROM Logs l WHERE l.timeStamp = :timeStamp"),
    @NamedQuery(name = "Logs.findByType", query = "SELECT l FROM Logs l WHERE l.type = :type"),
    @NamedQuery(name = "Logs.findByMessage", query = "SELECT l FROM Logs l WHERE l.message = :message"),
    @NamedQuery(name = "Logs.findBySeen", query = "SELECT l FROM Logs l WHERE l.seen = :seen")})
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LogsID")
    private Integer logsID;
    @Basic(optional = false)
    @Column(name = "timeStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @Column(name = "Message")
    private String message;
    @Column(name = "Seen")
    private String seen;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Staff username;

    public Logs() {
    }

    public Logs(Integer logsID) {
        this.logsID = logsID;
    }

    public Logs(Integer logsID, Date timeStamp, String type, String message) {
        this.logsID = logsID;
        this.timeStamp = timeStamp;
        this.type = type;
        this.message = message;
    }

    public Integer getLogsID() {
        return logsID;
    }

    public void setLogsID(Integer logsID) {
        this.logsID = logsID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Staff getUsername() {
        return username;
    }

    public void setUsername(Staff username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logsID != null ? logsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.logsID == null && other.logsID != null) || (this.logsID != null && !this.logsID.equals(other.logsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Logs[ logsID=" + logsID + " ]";
    }
    
}
