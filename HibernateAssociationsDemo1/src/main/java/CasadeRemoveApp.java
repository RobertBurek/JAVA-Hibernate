import entity.Company;
import entity.CompanyDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class CasadeRemoveApp {

    public static void main(String[] args) {

        //Komfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        //otwarcie sesji/transakcji
        session.beginTransaction();

        //pobranie danych o danym id
        Company company = session.get(Company.class, 15);

        //usuwanie obiektu z bazy
        session.remove(company);


        //zakończenie sesji/transakcji
        session.getTransaction().commit();

        //wyświetlanie pobranej listy w zależności od ilości pobranych danych


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
