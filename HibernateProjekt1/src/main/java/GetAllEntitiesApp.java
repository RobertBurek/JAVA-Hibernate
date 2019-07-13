import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class GetAllEntitiesApp {
    public static void main(String[] args) {

        //stworzyć obiekt Configuration
        Configuration conf = new Configuration();

        //wczytanie pliku konfiguracyjnego (hibernate.cfg.xml)
        conf.configure("hibernate.cfg.xml");

        // wczytanie adnotacji klasy encji
        conf.addAnnotatedClass(Pracownik.class);

        //stworzyć obiekt SessionFactory
        SessionFactory factory = conf.buildSessionFactory();

        //pobierać sesję
        Session session = factory.getCurrentSession();

        //rozpoczniemy transakcję
        session.beginTransaction();

        //wczytanie wszystkich encji poprzez createQuery() na obiekcie sesion
        List<Pracownik> listapracownikow = session.createQuery("from Pracownik").getResultList();

        for (Pracownik pracownik:listapracownikow) {
            System.out.println(pracownik);
        }

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
