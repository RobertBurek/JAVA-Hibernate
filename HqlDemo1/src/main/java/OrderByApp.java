import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class OrderByApp {
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

        //ORDER BY - sortowanie wybranych elementów danych
        String orderBy = "SELECT prac.imie, prac.nazwisko FROM Pracownik AS prac ORDER BY prac.imie";
        String orderBy2 = "SELECT prac.imie, prac.nazwisko FROM Pracownik AS prac ORDER BY prac.nazwisko DESC";
        String orderBy3 = "SELECT prac.nazwisko, prac.pensja FROM Pracownik AS prac ORDER BY prac.pensja DESC";
        String orderBy4 = "SELECT prac.imie, prac.nazwisko, prac.pensja FROM Pracownik AS prac ORDER BY prac.pensja ASC";


        //obiekt klasy Query
        Query query = session.createQuery(orderBy4);

        //wykonanie zapytania, pobranie listy encji
        List<Object[]> imieNazwiskoPracownikow = query.getResultList();

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //wyświetlanie pobranej listy w zależności od ilości pobranych danych
        int i = 1, j = 0;
        String dane;
        for (Object[] imieNazwiskoPracownika : imieNazwiskoPracownikow) {
            dane = "";
            for (j = 0; j < imieNazwiskoPracownika.length; j++)
                dane = dane + ", " + imieNazwiskoPracownika[j];
            System.out.println(i + ") Dane: " + dane);
            i++;
        }

        //zamknięcie obiektu SessionFactory
        factory.close();

    }
}
