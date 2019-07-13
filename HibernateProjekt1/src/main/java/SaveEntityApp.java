import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class SaveEntityApp {

    public static void main(String[] args) {

        //stworzyć obiekt Configuration
        Configuration conf = new Configuration();

        //wczytanie pliku konfiguracyjnego (hibernate.cfg.xml)
        conf.configure("hibernate.cfg.xml");

        // wczytanie adnotacji klasy encji
        conf.addAnnotatedClass(Pracownik.class);

        //stworzyć obiekt SEssionFactory
        SessionFactory factory = conf.buildSessionFactory();

        //pobierać sesję
        Session session = factory.getCurrentSession();

        //tworzenie obiektu Pracownik
        Pracownik pracownik = new Pracownik();
        pracownik.setId_pracownik(1);
        pracownik.setImie("Robert");
        pracownik.setNazwisko("Nowak");
        pracownik.setPensja(3500);

        //rozpoczniemy transakcję
        session.beginTransaction();

        //zapisanie pracownika
        session.save(pracownik);

        //zakończenie transakcji
        session.getTransaction().commit();

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
