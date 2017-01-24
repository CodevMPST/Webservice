/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thiba
 */
@Entity
@Table(name = "borne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Borne.findAll", query = "SELECT b FROM Borne b")
    , @NamedQuery(name = "Borne.findByIdBorne", query = "SELECT b FROM Borne b WHERE b.idBorne = :idBorne")
    , @NamedQuery(name = "Borne.findByEtatBorne", query = "SELECT b FROM Borne b WHERE b.etatBorne = :etatBorne")})
public class Borne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBorne")
    private Integer idBorne;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etatBorne")
    private boolean etatBorne;
    @JoinColumn(name = "station", referencedColumnName = "idStation")
    @ManyToOne(optional = false)
    private Station station;
    @JoinColumn(name = "idVehicule", referencedColumnName = "idVehicule")
    @ManyToOne
    private Vehicule idVehicule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "borneDepart")
    private Collection<Utilise> utiliseCollection;
    @OneToMany(mappedBy = "borneArrivee")
    private Collection<Utilise> utiliseCollection1;

    public Borne() {
    }

    public Borne(Integer idBorne) {
        this.idBorne = idBorne;
    }

    public Borne(Integer idBorne, boolean etatBorne) {
        this.idBorne = idBorne;
        this.etatBorne = etatBorne;
    }

    public Integer getIdBorne() {
        return idBorne;
    }

    public void setIdBorne(Integer idBorne) {
        this.idBorne = idBorne;
    }

    public boolean getEtatBorne() {
        return etatBorne;
    }

    public void setEtatBorne(boolean etatBorne) {
        this.etatBorne = etatBorne;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Vehicule getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Vehicule idVehicule) {
        this.idVehicule = idVehicule;
    }

    @XmlTransient
    public Collection<Utilise> getUtiliseCollection() {
        return utiliseCollection;
    }

    public void setUtiliseCollection(Collection<Utilise> utiliseCollection) {
        this.utiliseCollection = utiliseCollection;
    }

    @XmlTransient
    public Collection<Utilise> getUtiliseCollection1() {
        return utiliseCollection1;
    }

    public void setUtiliseCollection1(Collection<Utilise> utiliseCollection1) {
        this.utiliseCollection1 = utiliseCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBorne != null ? idBorne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Borne)) {
            return false;
        }
        Borne other = (Borne) object;
        if ((this.idBorne == null && other.idBorne != null) || (this.idBorne != null && !this.idBorne.equals(other.idBorne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Borne[ idBorne=" + idBorne + " ]";
    }
    
}
