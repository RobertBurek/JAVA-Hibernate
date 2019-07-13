import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class UpdateManagedEntityApp {

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

        //wypisujemy dane pracownika do modyfikacji
        System.out.println("Dane pracownika: " + pracownikUpdate);

        //zmianę danych dokonujemy w czasie trwania sesji
        //zmiana zostanie od razu naniesiona w bazie,
        //niewymaga dodatkowego zapisywania
        pracownikUpdate.setImie("Antonina");
        pracownikUpdate.setPensja(12500);

        //zakończenie transakcji
        session.getTransaction().commit();

        //wypisujemy dane pracownika po modyfikacji
        System.out.println("Zmodyfikowane dane: " + pracownikUpdate);

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
