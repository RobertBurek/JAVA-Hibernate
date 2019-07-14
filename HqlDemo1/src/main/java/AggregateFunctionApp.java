import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by Robert Burek
 */
public class AggregateFunctionApp {

    public static void main(String[] args) {

        //stworzyć obiekt Configuration
        Configuration conf = new Configuration();

        //wczytanie pliku konfiguracyjnego (hibernate.cfg.xml)
        conf.configure("hibernate.cfg.xml");

        // wczytanie adnotacji klasy encji
        conf.addAnnotatedClass(Pracownik.class);

        //stworzyć obiekt SessionFactory
        SessionFactory factory = conf.buildSessionFactory();

        //pobierać sesję
        Session session = factory.getCurrentSession();

        //rozpoczniemy transakcję
        session.beginTransaction();

        //AGGREGATE FUNCTION - agregacja funkcji
        String aggregateAVG = "SELECT AVG(p.pensja) FROM Pracownik AS p";
        String aggregateSUM = "SELECT SUM(p.pensja) FROM Pracownik AS p";
        String aggregateMIN = "SELECT MIN(p.pensja) FROM Pracownik AS p";
        String aggregateMAX = "SELECT MAX(p.pensja) FROM Pracownik AS p";
        String aggregateAVGn = "SELECT AVG(p.pensja) FROM Pracownik AS p WHERE p.id_pracownik > 80 AND p.id_pracownik" +
                " < 90";
        String aggregateCOUNT = "SELECT COUNT(p) FROM Pracownik AS p";


        //obiekt klasy Query
        Query queryAgg = session.createQuery(aggregateCOUNT);

        //wykonanie zapytania, pobranie listy encji
        Long resultAGV = (Long) queryAgg.getSingleResult();

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //wyświetlanie pobranej listy w zależności od ilości pobranych danych
        System.out.println("Wynik zapytania: " + resultAGV);

        //zamknięcie obiektu SessionFactory
        factory.close();

    }
}