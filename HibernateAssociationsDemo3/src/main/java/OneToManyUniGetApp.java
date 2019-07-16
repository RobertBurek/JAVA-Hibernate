import entity.Company;
import entity.CompanyDetail;
import entity.Department;
import entity.Property;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;

/**
 * Created by Robert Burek
 */
public class OneToManyUniGetApp {
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

        //odczyt danych powiązanych zależnością
        Set<Department> departments = company.getDepartments();

        //Wypisanie zbioru danyc
        for (Department department : departments) {
            System.out.println(department);
        }

        //odczytanie danych poprzez tabelę powiązaną relacją
        Department department = session.get(Department.class, 4);
        System.out.println("Department nr 4: " + department);


        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }

}
