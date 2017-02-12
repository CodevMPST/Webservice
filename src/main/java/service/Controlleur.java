package service;

import dao.Borne;
import dao.Client;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import dao.NewHibernateUtil;
import dao.Reservation;
import dao.ReservationId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dao.Station;
import java.util.Date;

@RestController
public class Controlleur {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong resCounter = new AtomicLong();

    @CrossOrigin()
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @CrossOrigin()
    @RequestMapping("/stations")
    public String stations() {
        int nbFree;
        int nbTotal;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        Query query = session.createQuery("from Station");
        List<Station> list = query.list();
        String str ="{ \"stations\": [";
        for(Station st : list) {
            nbFree=0;
            nbTotal=0;
            str+="{";
            str+="\"id\": "+st.getIdStation()+",";
            str+="\"latitude\": "+st.getLatitude()+",";
            str+="\"longitude\": "+st.getLongitude()+",";
            Query query2 = session.createQuery("from Borne b where b.station = :st");
            query2.setParameter("st", st);
            
            List<Borne> list2 = query2.list();
            for(Borne b : list2) {
                nbTotal++;
                if(!b.getEtatBorne()){
                    nbFree++;
                }
            }
            str+=" \"nbCar\": "+nbFree+",";
            str+=" \"nbMaxCar\": "+nbTotal+"";
            str+="},";          
        }
        str = str.substring(0, str.length()-1);
        str+="]}";
        session.getTransaction().commit();
        session.close();
        return str;
    }

    @CrossOrigin()
    @RequestMapping("/station")
    public String station(@RequestParam(value = "id") int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Station st where st.idStation = :id");
        query.setParameter("id", id);
        List<Station> list = query.list();
        if (list.size()>0) {
        Station st = list.get(0);
        String str="{";
        str+="\"id\": "+st.getIdStation()+",";
        str+="\"latitude\": "+st.getLatitude()+",";
        str+="\"longitude\": "+st.getLongitude()+",";
        str+="\"adresse\": \""+st.getAdresse()+"\",";
        str+="\"numero\": "+st.getNumero()+",";
        str+="\"ville\": \""+st.getVille()+"\",";
        str+="\"codePostal\": "+st.getCodePostal()+",";
        str+="\"bornes\": [";
        for(Borne b : st.getBornes()) {
            str+="{\"idBorne\": "+b.getIdBorne()+",";
            str+="\"etatBorne\": "+b.getEtatBorne()+",";
            str+="\"vehicule\": ";
            if(b.getEtatBorne()){
                str+="null";
            }
            else {
                str+="{\"idVehicule\": "+b.getVehicule().getIdVehicule()+",";
                str+="\"rfid\": "+b.getVehicule().getRfid()+",";
                str+="\"etatBatterie\": "+b.getVehicule().getEtatBatterie()+",";
                str+="\"typeVehicule\": {";
                str+="\"categorie\": \""+b.getVehicule().getTypeVehicule().getCategorie()+"\",";
                str+="\"type\": \""+b.getVehicule().getTypeVehicule().getTypeVehicule()+"\"}";
                str+="}";
            }
        str+="},";
        }
        str = str.substring(0, str.length()-1);
        str+="]}";
        session.getTransaction().commit();
        session.close();
        return str;
        }
        else {
            NewHibernateUtil.getSessionFactory().close();
            return "Erreur : id invalide ou inexistant";
        }
    }
    
    @CrossOrigin()
    @RequestMapping("/reserver")
    public String reserver(@RequestParam(value = "idClient") int idClient, @RequestParam(value = "idBorne") int idBorne) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Borne b where b.idBorne = :idb");
        query.setParameter("idb", idBorne);
        List<Borne> list = query.list();
        if (list.size()<1) {    
            session.close();
            return "Erreur : idBorne invalide ou inexistant";
        }
        Borne b = list.get(0);
        Query query2 = session.createQuery("from Client c where c.idClient = :idc");
        query2.setParameter("idc", idClient);
        List<Client> list2 = query2.list();
        
        if (list2.size()<1) {
            session.close();
            return "Erreur : idClient invalide ou inexistant";
        }
        Client c = list2.get(0);
        if (b.getEtatBorne() == true) {
            session.close();
            return "Erreur : borne non occupée";
        }
        Query query4 = session.createQuery("from Reservation r where r.client.idClient = :idc");
        query4.setParameter("idc", idClient);
        List<Client> list3 = query4.list();
        if (list3.size()>0){
            session.close();
            return "Erreur : Le client a déjà reservé une voiture";
        }
        Reservation res = new Reservation(new ReservationId(b.getVehicule().getIdVehicule(), c.getIdClient(), new Date()), c, b.getVehicule());
        session.save(res);
        Query query3 = session.createQuery("update Borne as b set "+
                "etatBorne = 1, " +
                "idVehicule = NULL " +
                "where idBorne = :idBorne");
        query3.setParameter("idBorne", idBorne);
        int result = query3.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "";
    }
    
    @CrossOrigin()
    @RequestMapping("/restituer")
    public String restituer(@RequestParam(value = "idClient") int idClient, @RequestParam(value = "idBorne") int idBorne) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Reservation r where r.client.idClient = :idClient");
        query.setParameter("idClient", idClient);
        List<Reservation> list = query.list();
        if (list.size()<1) {
            return "Erreur : Aucune réservation associée à ce client";
        }
        Reservation r = list.get(0);
        Query query2 = session.createQuery("update Borne as b set "+
                "etatBorne = 0, " +
                "idVehicule = :idVehicule " +
                "where idBorne = :idBorne");
        query2.setParameter("idBorne", idBorne);
        query2.setParameter("idVehicule", r.getVehicule().getIdVehicule());
        int result = query2.executeUpdate();
        Query query3 = session.createQuery("delete Reservation r where r.client.idClient = :idClient");
        query3.setParameter("idClient", idClient);
        int result2 = query3.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "";
    }
    
    @CrossOrigin()
    @RequestMapping("/creerclient")
    public String creerClient(@RequestParam(value = "nom") String nom, @RequestParam(value = "prenom") String prenom) {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Client c = new Client(nom, prenom);
        session.save(c);
        session.getTransaction().commit();
        return "";
    }
}
