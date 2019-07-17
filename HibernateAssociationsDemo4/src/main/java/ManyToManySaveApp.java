import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class ManyToManySaveApp {
    public static void main(String[] args) {

        //Konfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass((Property.class));
        conf.addAnnotatedClass((Department.class));
        conf.addAnnotatedClass((Trening.class));
        conf.addAnnotatedClass((Pracownik.class));

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        //otwarcie sesji/transakcji
        session.beginTransaction();

        // tworzenie obiektów
        Trening trening = new Trening("sales training");

        Pracownik pracownik = new Pracownik("Robert", "Nowak", 11500);
        Pracownik pracownik1 = new Pracownik("Monika", "Kowalska", 11500);

        trening.addPracownik(pracownik);
        trening.addPracownik(pracownik1);

        //zapis do bazy danych
        session.persist(trening);

        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
