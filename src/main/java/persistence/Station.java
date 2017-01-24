/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thiba
 */
@Entity
@Table(name = "station")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s")
    , @NamedQuery(name = "Station.findByIdStation", query = "SELECT s FROM Station s WHERE s.idStation = :idStation")
    , @NamedQuery(name = "Station.findByLatitude", query = "SELECT s FROM Station s WHERE s.latitude = :latitude")
    , @NamedQuery(name = "Station.findByLongitude", query = "SELECT s FROM Station s WHERE s.longitude = :longitude")
    , @NamedQuery(name = "Station.findByAdresse", query = "SELECT s FROM Station s WHERE s.adresse = :adresse")
    , @NamedQuery(name = "Station.findByNumero", query = "SELECT s FROM Station s WHERE s.numero = :numero")
    , @NamedQuery(name = "Station.findByVille", query = "SELECT s FROM Station s WHERE s.ville = :ville")
    , @NamedQuery(name = "Station.findByCodePostal", query = "SELECT s FROM Station s WHERE s.codePostal = :codePostal")})
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStation")
    private Integer idStation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Size(max = 200)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "numero")
    private Integer numero;
    @Size(max = 100)
    @Column(name = "ville")
    private String ville;
    @Column(name = "code_postal")
    private Integer codePostal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "station")
    private Collection<Borne> borneCollection;

    public Station() {
    }

    public Station(Integer idStation) {
        this.idStation = idStation;
    }

    public Station(Integer idStation, BigDecimal latitude, BigDecimal longitude) {
        this.idStation = idStation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    @XmlTransient
    public Collection<Borne> getBorneCollection() {
        return borneCollection;
    }

    public void setBorneCollection(Collection<Borne> borneCollection) {
        this.borneCollection = borneCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStation != null ? idStation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.idStation == null && other.idStation != null) || (this.idStation != null && !this.idStation.equals(other.idStation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Station[ idStation=" + idStation + " ]";
    }
    
}
