package dao;
// Generated 25 janv. 2017 00:17:46 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TypeVehicule generated by hbm2java
 */
public class TypeVehicule  implements java.io.Serializable {


     private Integer idTypeVehicule;
     private String categorie;
     private String typeVehicule;
     private Set<Vehicule> vehicules = new HashSet<Vehicule>(0);

    public TypeVehicule() {
    }

	
    public TypeVehicule(String categorie, String typeVehicule) {
        this.categorie = categorie;
        this.typeVehicule = typeVehicule;
    }
    public TypeVehicule(String categorie, String typeVehicule, Set<Vehicule> vehicules) {
       this.categorie = categorie;
       this.typeVehicule = typeVehicule;
       this.vehicules = vehicules;
    }
   
    public Integer getIdTypeVehicule() {
        return this.idTypeVehicule;
    }
    
    public void setIdTypeVehicule(Integer idTypeVehicule) {
        this.idTypeVehicule = idTypeVehicule;
    }
    public String getCategorie() {
        return this.categorie;
    }
    
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getTypeVehicule() {
        return this.typeVehicule;
    }
    
    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }
    public Set<Vehicule> getVehicules() {
        return this.vehicules;
    }
    
    public void setVehicules(Set<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }




}


