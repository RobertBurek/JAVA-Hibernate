import entity.Company;
import entity.CompanyDetail;
import entity.Property;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class FetchTypesApp {
    public static void main(String[] args) {

        //Komfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass((Property.class));

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        //treść zapytania
        String getCompany = "SELECT c FROM Company AS c WHERE c.name='StrefaKursów'";

        //otwarcie sesji/transakcji
        session.beginTransaction();


        //pobranie danych po id i wypisanie
        System.out.println("Pobieranie danych po id.");
        Company company = session.get(Company.class, 40);
        System.out.println("Obiekt pobrany.");
        System.out.println(company);

        System.out.println("Nieruchomości: ");
        for (Property property : company.getProperties()) {
            System.out.println(property);
        }



        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
