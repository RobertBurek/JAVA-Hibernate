import entity.Company;
import entity.CompanyDetail;
import entity.Department;
import entity.Property;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by Robert Burek
 */
public class OneToManyUniDeleteApp {
    public static void main(String[] args) {

        //Konfiguracja i encje
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
        //1)
//        Department department = session.get(Department.class, 4);
//        System.out.println(department);
        //2)
//        Company company = session.get(Company.class, 41);
//        System.out.println((company));
        //3)
        Query query = session.createQuery("DELETE Department AS d WHERE d.idDepartment=:idDelete");
        query.setParameter("idDelete", 1);
        int deleteRows = query.executeUpdate();
        System.out.println("Ilość usuniętych elmentów: " + deleteRows);


        //usunięcie danych powiązanych zależnością
        //1)
//        session.delete(department);
        //2)
//        for (Department department : company.getDepartments()) {
//            if ((department.getName()).equals("HR")) {
//                company.getDepartments().remove(department);
//                session.delete(department);
//            }
//        }


        //odczytanie danych poprzez tabelę powiązaną relacją
//        Department department = session.get(Department.class, 4);
//        System.out.println("Department nr 4: " + department);


        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
