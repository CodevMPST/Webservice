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
@Table(name = "type_vehicule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeVehicule.findAll", query = "SELECT t FROM TypeVehicule t")
    , @NamedQuery(name = "TypeVehicule.findByIdTypevehicule", query = "SELECT t FROM TypeVehicule t WHERE t.idTypevehicule = :idTypevehicule")
    , @NamedQuery(name = "TypeVehicule.findByCategorie", query = "SELECT t FROM TypeVehicule t WHERE t.categorie = :categorie")
    , @NamedQuery(name = "TypeVehicule.findByTypeVehicule", query = "SELECT t FROM TypeVehicule t WHERE t.typeVehicule = :typeVehicule")})
public class TypeVehicule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idType_vehicule")
    private Integer idTypevehicule;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "categorie")
    private String categorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type_vehicule")
    private String typeVehicule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeVehicule")
    private Collection<Vehicule> vehiculeCollection;

    public TypeVehicule() {
    }

    public TypeVehicule(Integer idTypevehicule) {
        this.idTypevehicule = idTypevehicule;
    }

    public TypeVehicule(Integer idTypevehicule, String categorie, String typeVehicule) {
        this.idTypevehicule = idTypevehicule;
        this.categorie = categorie;
        this.typeVehicule = typeVehicule;
    }

    public Integer getIdTypevehicule() {
        return idTypevehicule;
    }

    public void setIdTypevehicule(Integer idTypevehicule) {
        this.idTypevehicule = idTypevehicule;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    @XmlTransient
    public Collection<Vehicule> getVehiculeCollection() {
        return vehiculeCollection;
    }

    public void setVehiculeCollection(Collection<Vehicule> vehiculeCollection) {
        this.vehiculeCollection = vehiculeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypevehicule != null ? idTypevehicule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeVehicule)) {
            return false;
        }
        TypeVehicule other = (TypeVehicule) object;
        if ((this.idTypevehicule == null && other.idTypevehicule != null) || (this.idTypevehicule != null && !this.idTypevehicule.equals(other.idTypevehicule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.TypeVehicule[ idTypevehicule=" + idTypevehicule + " ]";
    }
    
}
