import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class ManyToManyHqlApp {
    public static void main(String[] args) {

        //Konfiguracja i encje
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass((Property.class));
        conf.addAnnotatedClass((Department.class));
        conf.addAnnotatedClass((Trening.class));
        conf.addAnnotatedClass((Pracownik.class));

        //Utworzenie obiektu SessionFactory oraz Session
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        //otwarcie sesji/transakcji
        session.beginTransaction();

        //zapytania HQL i wyświetlenie danych
//        1)
        int ileT = 6;
        String getTrening = "SELECT t FROM Trening AS t WHERE size(t.pracownicy) >= :ileT";
        Query query = session.createQuery(getTrening);
        query.setParameter("ileT", ileT);
        List<Trening> treningList = query.getResultList();

        for (Trening trening : treningList) {
            System.out.println(trening);
            for (Pracownik pracownik : trening.getPracownicy()) {
                System.out.println("- " + pracownik);
            }
        }

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");

//        2)
        String course = "javascript";
        String getPracownik = "SELECT p FROM Pracownik AS p JOIN p.treningi AS t WHERE t.name = :kurs";
        Query query2 = session.createQuery(getPracownik);
        query2.setParameter("kurs", course);
        List<Pracownik> pracownikList = query2.getResultList();

        for (Pracownik pracownik : pracownikList) {
            System.out.println("- " + pracownik);
        }

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");

//        3)
        int ileTrening = 1;
        int maxPensja = 12000;
        String getPracownik2 = "SELECT p FROM Pracownik AS p WHERE size(p.treningi) = :ileT AND p.pensja <= :maxP";
        Query query3 = session.createQuery(getPracownik2);
        query3.setParameter("ileT", ileTrening);
        query3.setParameter("maxP", maxPensja);
        List<Pracownik> pracownikList2 = query3.getResultList();

        for (Pracownik pracownik : pracownikList2) {
            System.out.println("- " + pracownik);
        }

        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
