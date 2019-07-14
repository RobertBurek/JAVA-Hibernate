import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class FromApp {
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

        //FROM - skąd będą brane dane dla zapytania (jakie tabele będą w zapytaniu
        String from = "FROM Pracownik";
        String from2 = "from Pracownik";

        //obiekt klasy Query
        Query query = session.createQuery(from2);

        //wykonanie zapytania, pobranie listy encji
        List<Pracownik> pracownicy = query.getResultList();

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //wyświetlanie pobranej listy
        int i=1;
        for (Pracownik pracownik:pracownicy) {
            System.out.println(i+") "+pracownik);
            i++;
        }

        //zamknięcie obiektu SessionFactory
        factory.close();

    }
}
