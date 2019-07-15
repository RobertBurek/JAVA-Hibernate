import entity.Company;
import entity.CompanyDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Robert Burek
 */
public class CascadeApp {

    public static void main(String[] args) {

        //Komfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        //Tworzenie obiektów encji
        Company company = new Company();
        company.setName("KGHM");
        company.setValue(1000000000);
        CompanyDetail companyDetail = new CompanyDetail();
        companyDetail.setResidence("Poland");
        companyDetail.setEmployeeNumber(15000);
        company.setCompanyDetail(companyDetail);


        //otwarcie sesji/transakcji
        session.beginTransaction();

        //zapisanie encji do tabel w bazie
        session.persist(company);
//        session.save(companyDetail);


        //zakończenie sesji/transakcji
        session.getTransaction().commit();

        //wyświetlanie pobranej listy w zależności od ilości pobranych danych


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
