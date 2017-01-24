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
public class UtilisePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Vehicule")
    private int vehicule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Client")
    private int client;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public UtilisePK() {
    }

    public UtilisePK(int vehicule, int client, Date date) {
        this.vehicule = vehicule;
        this.client = client;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) vehicule;
        hash += (int) client;
        hash += (date != null ? date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtilisePK)) {
            return false;
        }
        UtilisePK other = (UtilisePK) object;
        if (this.vehicule != other.vehicule) {
            return false;
        }
        if (this.client != other.client) {
            return false;
        }
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.UtilisePK[ vehicule=" + vehicule + ", client=" + client + ", date=" + date + " ]";
    }
    
}
