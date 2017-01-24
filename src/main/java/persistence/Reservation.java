/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author thiba
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r")
    , @NamedQuery(name = "Reservation.findByVehicule", query = "SELECT r FROM Reservation r WHERE r.reservationPK.vehicule = :vehicule")
    , @NamedQuery(name = "Reservation.findByClient", query = "SELECT r FROM Reservation r WHERE r.reservationPK.client = :client")
    , @NamedQuery(name = "Reservation.findByDateReservation", query = "SELECT r FROM Reservation r WHERE r.reservationPK.dateReservation = :dateReservation")
    , @NamedQuery(name = "Reservation.findByDateEcheance", query = "SELECT r FROM Reservation r WHERE r.dateEcheance = :dateEcheance")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservationPK reservationPK;
    @Column(name = "date_echeance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEcheance;
    @JoinColumn(name = "client", referencedColumnName = "idClient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Client client1;
    @JoinColumn(name = "vehicule", referencedColumnName = "idVehicule", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vehicule vehicule1;

    public Reservation() {
    }

    public Reservation(ReservationPK reservationPK) {
        this.reservationPK = reservationPK;
    }

    public Reservation(int vehicule, int client, Date dateReservation) {
        this.reservationPK = new ReservationPK(vehicule, client, dateReservation);
    }

    public ReservationPK getReservationPK() {
        return reservationPK;
    }

    public void setReservationPK(ReservationPK reservationPK) {
        this.reservationPK = reservationPK;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Client getClient1() {
        return client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public Vehicule getVehicule1() {
        return vehicule1;
    }

    public void setVehicule1(Vehicule vehicule1) {
        this.vehicule1 = vehicule1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationPK != null ? reservationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationPK == null && other.reservationPK != null) || (this.reservationPK != null && !this.reservationPK.equals(other.reservationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Reservation[ reservationPK=" + reservationPK + " ]";
    }
    
}
