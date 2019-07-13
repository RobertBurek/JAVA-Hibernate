import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class DeleteEntityApp {

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

        //wczytanie encji poprzez get na obiekcie sesion
        Pracownik pracownikDelete = session.get(Pracownik.class, 10);

        //usuwanie encji
        session.delete(pracownikDelete);

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
