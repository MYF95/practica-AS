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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author myf19
 */
@Entity
@Table(name = "RECORDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Records.findAll", query = "SELECT r FROM Records r")
    , @NamedQuery(name = "Records.findById", query = "SELECT r FROM Records r WHERE r.id = :id")
    , @NamedQuery(name = "Records.findByDni", query = "SELECT r FROM Records r WHERE r.dni = :dni")
    , @NamedQuery(name = "Records.findByInfo", query = "SELECT r FROM Records r WHERE r.info = :info")
    , @NamedQuery(name = "Records.findByDate", query = "SELECT r FROM Records r WHERE r.date = :date")})
public class Records implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "DNI")
    private String dni;
    @Size(max = 255)
    @Column(name = "INFO")
    private String info;
    @Column(name = "DATE")
    private String date;

    public Records() {
    }

    public Records(Integer id) {
        this.id = id;
    }

    public Records(Integer id, String dni) {
        this.id = id;
        this.dni = dni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        if (!(object instanceof Records)) {
            return false;
        }
        Records other = (Records) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Records[ id=" + id + " ]";
    }
    
}
