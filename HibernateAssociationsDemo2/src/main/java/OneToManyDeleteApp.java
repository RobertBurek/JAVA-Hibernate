import entity.Company;
import entity.CompanyDetail;
import entity.Property;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by Robert Burek
 */
public class OneToManyDeleteApp {
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

        //zapytanie
        Query query = session.createQuery(getCompany);
        Company companyDB = (Company) query.getSingleResult();

        System.out.println(companyDB);

        for (Property property : companyDB.getProperties()) {
            System.out.println(property);
        }

        //usuwanie poprzez iterowanie obietków i wyszukanie właściwego do usunięcia
        //gdy jest więcej obiektów spełniających warunek - usunie wszystkie
        //lepiej usuwać po id
        for (Property property : companyDB.getProperties()) {
            if ("Gdynia".equals(property.getCity())) {
                session.delete((property));
            }
        }

        Property property = session.get(Property.class, 3);
        session.delete(property);


        //sprawdzenie kaskodowości relacjach OneToMany
//        Property property = session.get(Property.class, 5);
//        System.out.println(property.getCompany());


        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
