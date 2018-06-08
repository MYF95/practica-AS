/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author myf19
 */
@Entity
@Table(name = "PATIENTLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patientlist.findAll", query = "SELECT p FROM Patientlist p")
    , @NamedQuery(name = "Patientlist.findById", query = "SELECT p FROM Patientlist p WHERE p.id = :id")
    , @NamedQuery(name = "Patientlist.findByUserid", query = "SELECT p FROM Patientlist p WHERE p.userid = :userid")
    , @NamedQuery(name = "Patientlist.findByPatientid", query = "SELECT p FROM Patientlist p WHERE p.patientid = :patientid")})
public class Patientlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PATIENTID")
    private int patientid;

    public Patientlist() {
    }

    public Patientlist(Integer id) {
        this.id = id;
    }

    public Patientlist(Integer id, int userid, int patientid) {
        this.id = id;
        this.userid = userid;
        this.patientid = patientid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patientlist)) {
            return false;
        }
        Patientlist other = (Patientlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Patientlist[ id=" + id + " ]";
    }
    
}
