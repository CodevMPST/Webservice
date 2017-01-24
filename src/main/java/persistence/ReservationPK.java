/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author thiba
 */
@Embeddable
public class ReservationPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "vehicule")
    private int vehicule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client")
    private int client;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_reservation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReservation;

    public ReservationPK() {
    }

    public ReservationPK(int vehicule, int client, Date dateReservation) {
        this.vehicule = vehicule;
        this.client = client;
        this.dateReservation = dateReservation;
    }

    public int getVehicule() {
        return vehicule;
    }

    public void setVehicule(int vehicule) {
        this.vehicule = vehicule;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) vehicule;
        hash += (int) client;
        hash += (dateReservation != null ? dateReservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationPK)) {
            return false;
        }
        ReservationPK other = (ReservationPK) object;
        if (this.vehicule != other.vehicule) {
            return false;
        }
        if (this.client != other.client) {
            return false;
        }
        if ((this.dateReservation == null && other.dateReservation != null) || (this.dateReservation != null && !this.dateReservation.equals(other.dateReservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.ReservationPK[ vehicule=" + vehicule + ", client=" + client + ", dateReservation=" + dateReservation + " ]";
    }
    
}
