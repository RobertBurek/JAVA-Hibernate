import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class ManyToManyDeleteApp {
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

        // Odczyt danych z bazy
//        Pracownik pracownik = session.get(Pracownik.class, 11);
        Trening trening = session.get(Trening.class, 20);

        //usunięcie z bazy
//        session.delete(pracownik);
        session.delete(trening);


        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
