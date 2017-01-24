/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thiba
 */
@Entity
@Table(name = "utilise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilise.findAll", query = "SELECT u FROM Utilise u")
    , @NamedQuery(name = "Utilise.findByVehicule", query = "SELECT u FROM Utilise u WHERE u.utilisePK.vehicule = :vehicule")
    , @NamedQuery(name = "Utilise.findByClient", query = "SELECT u FROM Utilise u WHERE u.utilisePK.client = :client")
    , @NamedQuery(name = "Utilise.findByDate", query = "SELECT u FROM Utilise u WHERE u.utilisePK.date = :date")})
public class Utilise implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UtilisePK utilisePK;
    @JoinColumn(name = "Client", referencedColumnName = "idClient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Client client1;
    @JoinColumn(name = "Vehicule", referencedColumnName = "idVehicule", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vehicule vehicule1;
    @JoinColumn(name = "borne_depart", referencedColumnName = "idBorne")
    @ManyToOne(optional = false)
    private Borne borneDepart;
    @JoinColumn(name = "borne_arrivee", referencedColumnName = "idBorne")
    @ManyToOne
    private Borne borneArrivee;

    public Utilise() {
    }

    public Utilise(UtilisePK utilisePK) {
        this.utilisePK = utilisePK;
    }

    public Utilise(int vehicule, int client, Date date) {
        this.utilisePK = new UtilisePK(vehicule, client, date);
    }

    public UtilisePK getUtilisePK() {
        return utilisePK;
    }

    public void setUtilisePK(UtilisePK utilisePK) {
        this.utilisePK = utilisePK;
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

    public Borne getBorneDepart() {
        return borneDepart;
    }

    public void setBorneDepart(Borne borneDepart) {
        this.borneDepart = borneDepart;
    }

    public Borne getBorneArrivee() {
        return borneArrivee;
    }

    public void setBorneArrivee(Borne borneArrivee) {
        this.borneArrivee = borneArrivee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utilisePK != null ? utilisePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilise)) {
            return false;
        }
        Utilise other = (Utilise) object;
        if ((this.utilisePK == null && other.utilisePK != null) || (this.utilisePK != null && !this.utilisePK.equals(other.utilisePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Utilise[ utilisePK=" + utilisePK + " ]";
    }
    
}
