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
@Table(name = "PATIENTRECORDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patientrecords.findAll", query = "SELECT p FROM Patientrecords p")
    , @NamedQuery(name = "Patientrecords.findById", query = "SELECT p FROM Patientrecords p WHERE p.id = :id")
    , @NamedQuery(name = "Patientrecords.findByPatientid", query = "SELECT p FROM Patientrecords p WHERE p.patientid = :patientid")
    , @NamedQuery(name = "Patientrecords.findByRecordid", query = "SELECT p FROM Patientrecords p WHERE p.recordid = :recordid")})
public class Patientrecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PATIENTID")
    private int patientid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECORDID")
    private int recordid;

    public Patientrecords() {
    }

    public Patientrecords(Integer id) {
        this.id = id;
    }

    public Patientrecords(Integer id, int patientid, int recordid) {
        this.id = id;
        this.patientid = patientid;
        this.recordid = recordid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
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
        if (!(object instanceof Patientrecords)) {
            return false;
        }
        Patientrecords other = (Patientrecords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Patientrecords[ id=" + id + " ]";
    }
    
}
