import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class ManyToManyGetApp {
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

        // Odczyt danych z bazy
        Trening treningBD = session.get(Trening.class, 4);

        //wypisujemy dane pobranego obiektu i dane powiązane
        System.out.println((treningBD));
        for (Pracownik pracownik : treningBD.getPracownicy()) {
            System.out.println(pracownik);
        }

        //pobranie listy powiązanej i wyświetlenie powiązanych z nią danych
//        1) listę treningów i na jej podstawie wyświetlimy przypisanych pracowników
        Query query = session.createQuery("FROM Trening");

        List<Trening> treningList = query.getResultList();

        for (Trening trening : treningList) {
            System.out.println("\n" + trening);
            for (Pracownik pracownik : trening.getPracownicy()) {
                System.out.println("- " + pracownik);
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
//        2) listę pracowników i na jej podstawie wyświetlimy przypisanych pracowników

        Query queryP = session.createQuery("FROM Pracownik WHERE nazwisko='Nowak'");

        List<Pracownik> pracownikList = queryP.getResultList();

        for (Pracownik pracownik : pracownikList) {
            System.out.println("\n" + pracownik);
            for (Trening trening : pracownik.getTreningi()) {
                System.out.println("- " + trening);
            }
        }


        //dodatkowe sprawdzenie zapisu ManyToMany w zależności od kolejnosci tworzenia obiektów
//        Trening treningNew = new Trening("phyton training");
//        Pracownik pracownik1 = session.get(Pracownik.class,119);
//        Pracownik pracownik2 = session.get(Pracownik.class,127);
//        Pracownik pracownik3 = session.get(Pracownik.class,126);
//        pracownik1.addTrening(treningNew);
//        treningNew.addPracownik(pracownik2);
//        treningNew.addPracownik(pracownik3);
//        session.persist(pracownik1);
//        session.persist(treningNew);

        //zakończenie sesji/transakcji
        session.getTransaction().commit();


        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
