import entity.Company;
import entity.CompanyDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class CasadeRefreshApp {
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
        Company company = session.get(Company.class, 7);
        System.out.println("CompanyDetail przed zmianami: " + company.getCompanyDetail());
        CompanyDetail companyDetail = session.get(CompanyDetail.class, 6);
        companyDetail.setResidence("Canada");
        session.save(companyDetail);
        System.out.println("CompanyDetail po zmianie: "+session.get(CompanyDetail.class,6));

        //odświeżyliśmy bazę poprzez pobraną encję Company i przywróciliśmy w ten sposób ustawienia w niej zapisane
        // dot. CompanyDetail
        session.refresh(company);
        System.out.println("Po odświeżeniu: " + company.getCompanyDetail());

        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
