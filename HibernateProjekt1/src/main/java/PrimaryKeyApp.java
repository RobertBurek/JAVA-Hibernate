import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class PrimaryKeyApp {
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

        //tworzenie 3 obiektów-Pracownik
        Pracownik pracownik1 = new Pracownik();
        pracownik1.setImie("Anna");
        pracownik1.setNazwisko("Kowal");
        pracownik1.setPensja(3500);

        Pracownik pracownik2 = new Pracownik();
        pracownik2.setImie("Roman");
        pracownik2.setNazwisko("Kowalski");
        pracownik2.setPensja(5600);

        Pracownik pracownik3 = new Pracownik();
        pracownik3.setImie("Marianna");
        pracownik3.setNazwisko("Zawada");
        pracownik3.setPensja(8400);

        //rozpoczniemy transakcję
        session.beginTransaction();

        //zapisanie pracowników
        session.save(pracownik1);
        session.save(pracownik2);
        session.save(pracownik3);

        //zakończenie transakcji
        session.getTransaction().commit();

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
