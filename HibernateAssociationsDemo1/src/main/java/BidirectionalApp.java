import entity.Company;
import entity.CompanyDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class BidirectionalApp {
    public static void main(String[] args) {

        //Komfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();


        //Utworzenie obiektów
//        Company company = new Company();
//        company.setName("PZUSA");
//        company.setValue(25);
//        CompanyDetail detail = new CompanyDetail();
//        detail.setResidence("Poland2");
//        detail.setEmployeeNumber(23);
//        company.setCompanyDetail(detail);
//        detail.setCompany(company);

        //otwarcie sesji/transakcji
        session.beginTransaction();

        //zapisanie obiektów do bazy
//        session.persist(detail);

        //odczyt z bazy
        CompanyDetail detail = session.get(CompanyDetail.class, 15);
        Company company = session.get(Company.class, 19);

        //usuwanie elementów poprzez powiązanie
        session.remove(detail);
        session.remove(company);


        //zakończenie sesji/transakcji
        session.getTransaction().commit();

        //wyświetlanie danych company pobranych poprzez zależność dwukierunkową
//        System.out.println(detail.getCompany());

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
