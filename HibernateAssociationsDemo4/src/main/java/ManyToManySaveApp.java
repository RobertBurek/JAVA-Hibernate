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
        Trening trening1 = new Trening("manualNEW training");
        Trening trening2= new Trening("sportNEW training");
        Trening trening3 = new Trening("calculateNEW training");

        Pracownik pracownik = new Pracownik("Robert", "Nowak", 11500);
        Pracownik pracownik1 = new Pracownik("Monika", "Kowalska", 11500);

        trening.addPracownik(pracownik);
        trening.addPracownik(pracownik1);

        Trening trening1DB = session.get(Trening.class, 3);
        Trening trening2DB = session.get(Trening.class, 4);
        Trening trening3DB = session.get(Trening.class, 2);

        Pracownik pracownikDB = session.get(Pracownik.class, 14);

        pracownikDB.addTrening(trening1DB);
        pracownikDB.addTrening(trening2DB);
        pracownikDB.addTrening(trening3DB);

        pracownik.addTrening(trening1);
        pracownik.addTrening(trening2);
        pracownik.addTrening(trening3);

        //zapis do bazy danych
        session.persist(trening);
        session.persist(pracownikDB);


        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
