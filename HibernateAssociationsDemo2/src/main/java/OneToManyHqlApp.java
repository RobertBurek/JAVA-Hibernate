import entity.Company;
import entity.CompanyDetail;
import entity.Property;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class OneToManyHqlApp {
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
        String getCompany = "SELECT c.name FROM Property AS p JOIN p.company AS c WHERE p.city='Sevilla'";
        String getCompany2 = "SELECT c.name FROM Property AS p JOIN p.company AS c JOIN c.companyDetail AS cd WHERE p" +
                ".city='Barcelona' AND cd.residence='Switzerland'";
        String getCompany3 = "SELECT c.name FROM Company AS c WHERE size(c.properties) > 4";

        //otwarcie sesji/transakcji
        session.beginTransaction();

        //zapytanie
        Query query = session.createQuery(getCompany3);
        List<String> cityList = query.getResultList();

        for (String city : cityList) {
            System.out.println(city);
        }

        //sprawdzenie kaskodowości relacjach OneToMany
//        Property property = session.get(Property.class, 5);
//        System.out.println(property.getCompany());


        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
