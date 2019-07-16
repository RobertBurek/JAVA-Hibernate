import entity.Company;
import entity.CompanyDetail;
import entity.Department;
import entity.Property;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class OneToManyUniSaveApp {
    public static void main(String[] args) {

        //Komfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass((Property.class));
        conf.addAnnotatedClass((Department.class));

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        //otwarcie sesji/transakcji
        session.beginTransaction();

        //zapytanie do bazy
        Company company = session.get(Company.class, 41);
        System.out.println(company);

        Department department1 = new Department("sales");
        Department department2 = new Department("production");
        Department department3 = new Department("HR");
        Department department4 = new Department("marketing");

        company.addDepartment(department1);
        company.addDepartment(department2);
        company.addDepartment(department3);


        session.persist(company);

//        for (Property property : companyDB.getProperties()) {
//            System.out.println(property);
//        }

        //usuwanie poprzez iterowanie obietków i wyszukanie właściwego do usunięcia
        //gdy jest więcej obiektów spełniających warunek - usunie wszystkie
        //lepiej usuwać po id
//        for (Property property : companyDB.getProperties()) {
//            if ("Gdynia".equals(property.getCity())) {
//                session.delete((property));
//            }
//        }

        //sprawdzamy czy działa kaskadowość przy zapisie poprze obiekt powiązany zależnością
        company.addDepartment(department4);
        session.persist(department4);


        //sprawdzenie kaskodowości relacjach OneToMany
//        Property property = session.get(Property.class, 5);
//        System.out.println(property.getCompany());


        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
