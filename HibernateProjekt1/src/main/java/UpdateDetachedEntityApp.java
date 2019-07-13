import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class UpdateDetachedEntityApp {

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

        //rozpoczniemy transakcję
        session.beginTransaction();

        //wczytanie encji do zmodyfikowania
        Pracownik pracownikUpdate = session.get(Pracownik.class, 9);

        //zakończenie transakcji
        session.getTransaction().commit();

        //wypisujemy dane pracownika do modyfikacji
        System.out.println("Dane pracownika: " + pracownikUpdate);

        //zmienaimy imię pracownikowi
        pracownikUpdate.setImie("Monika");

        System.out.println("Zmodyfikowane dane: " + pracownikUpdate);

        //ponowny proces zapisywania zmian poprzez update()
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.update(pracownikUpdate);
        session.getTransaction().commit();

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
