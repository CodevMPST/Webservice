package service;

import com.google.gson.Gson;
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
    public String station() {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Station");
        List<Station> list = query.list();
        String str ="{ \"stations\": [";
        for(Station st : list) {
            str+="{ \"id\": "+st.getIdStation()+"},";
            str+="{ \"latitude\": "+st.getLatitude()+"},";
            str+="{ \"longitude\": "+st.getLongitude()+"},";
        }
        str = str.substring(0, str.length()-1);
        str+="]}";
        session.getTransaction().commit();
        //NewHibernateUtil.getSessionFactory().close();
        return str;
    }

}
