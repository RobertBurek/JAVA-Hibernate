import entity.Company;
import entity.CompanyDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class OneToOneHqlApp {
    public static void main(String[] args) {

        //Komfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        //Tworzenie pytania
        String select = "SELECT c FROM Company c";
        String where = "SELECT c FROM Company AS c JOIN c.companyDetail AS cd WHERE cd.residence='Italy'";
        String sum = "SELECT SUM(c.value) FROM Company AS c JOIN c.companyDetail AS cd WHERE cd.residence='Poland'";
        String orderBy = "SELECT c.name FROM CompanyDetail AS cd JOIN cd.company AS c WHERE cd.employeeNumber<35000 " +
                "ORDER BY c.value";


        //otwarcie sesji/transakcji
        session.beginTransaction();

        Query query = session.createQuery(orderBy);

//        List<Company> resultList = query.getResultList();
//        Long result = (Long) query.getSingleResult();
        List<String> resultList = query.getResultList();


        //zakończenie sesji/transakcji
        session.getTransaction().commit();

        //wyświetlanie pobranej listy w zależności od ilości pobranych danych
//        int i = 1;
//        for (Company c : resultList) {
//            System.out.println(i + ") " + c);
//            i++;
//        }
//        System.out.println("Wartość wszystkich firm: " + result);
//        System.out.println("Duże firmy (>35000e): " + result);
        int i = 1;
        for (String c : resultList) {
            System.out.println(i + ") " + c);
            i++;
        }

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
