package service;

import dao.Borne;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import dao.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dao.Station;

@RestController
public class Controlleur {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

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
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
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
        //NewHibernateUtil.getSessionFactory().close();
        return str;
    }

    @CrossOrigin()
    @GetMapping("/station")
    public String station(@RequestParam(value = "id", defaultValue = "1") int id) {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Station st where st.idStation = :id");
        query.setParameter("id", id);
        List<Station> list = query.list();
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

        return str;
    }
    
}
