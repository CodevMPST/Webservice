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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vehicule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicule.findAll", query = "SELECT v FROM Vehicule v")
    , @NamedQuery(name = "Vehicule.findByIdVehicule", query = "SELECT v FROM Vehicule v WHERE v.idVehicule = :idVehicule")
    , @NamedQuery(name = "Vehicule.findByRfid", query = "SELECT v FROM Vehicule v WHERE v.rfid = :rfid")
    , @NamedQuery(name = "Vehicule.findByEtatBatterie", query = "SELECT v FROM Vehicule v WHERE v.etatBatterie = :etatBatterie")
    , @NamedQuery(name = "Vehicule.findByDisponibilite", query = "SELECT v FROM Vehicule v WHERE v.disponibilite = :disponibilite")
    , @NamedQuery(name = "Vehicule.findByLatitude", query = "SELECT v FROM Vehicule v WHERE v.latitude = :latitude")
    , @NamedQuery(name = "Vehicule.findByLongitude", query = "SELECT v FROM Vehicule v WHERE v.longitude = :longitude")})
public class Vehicule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVehicule")
    private Integer idVehicule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RFID")
    private int rfid;
    @Column(name = "etatBatterie")
    private Integer etatBatterie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Disponibilite")
    private String disponibilite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
    @OneToMany(mappedBy = "idVehicule")
    private Collection<Borne> borneCollection;
    @JoinColumn(name = "type_vehicule", referencedColumnName = "idType_vehicule")
    @ManyToOne(optional = false)
    private TypeVehicule typeVehicule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicule1")
    private Collection<Reservation> reservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicule1")
    private Collection<Utilise> utiliseCollection;

    public Vehicule() {
    }

    public Vehicule(Integer idVehicule) {
        this.idVehicule = idVehicule;
    }

    public Vehicule(Integer idVehicule, int rfid, String disponibilite) {
        this.idVehicule = idVehicule;
        this.rfid = rfid;
        this.disponibilite = disponibilite;
    }

    public Integer getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Integer idVehicule) {
        this.idVehicule = idVehicule;
    }

    public int getRfid() {
        return rfid;
    }

    public void setRfid(int rfid) {
        this.rfid = rfid;
    }

    public Integer getEtatBatterie() {
        return etatBatterie;
    }

    public void setEtatBatterie(Integer etatBatterie) {
        this.etatBatterie = etatBatterie;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
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

    @XmlTransient
    public Collection<Borne> getBorneCollection() {
        return borneCollection;
    }

    public void setBorneCollection(Collection<Borne> borneCollection) {
        this.borneCollection = borneCollection;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @XmlTransient
    public Collection<Utilise> getUtiliseCollection() {
        return utiliseCollection;
    }

    public void setUtiliseCollection(Collection<Utilise> utiliseCollection) {
        this.utiliseCollection = utiliseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehicule != null ? idVehicule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicule)) {
            return false;
        }
        Vehicule other = (Vehicule) object;
        if ((this.idVehicule == null && other.idVehicule != null) || (this.idVehicule != null && !this.idVehicule.equals(other.idVehicule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Vehicule[ idVehicule=" + idVehicule + " ]";
    }
    
}
