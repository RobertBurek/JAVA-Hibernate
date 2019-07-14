import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class WhereApp {
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

        //WHERE - zawężanie wyników wyszukiwania, używamy nazwy pola encji nie nazwy kolumny z tabeli
        String where = "FROM Pracownik WHERE imie = 'Robert'";
        String where2 = "FROM Pracownik WHERE pensja >= 11000";
        String where3 = "FROM Pracownik WHERE ";
        String where4 = "pensja < 3000 OR pensja >= 13000";  //w zapytaniu where3+where4
        String where5 = "FROM Pracownik WHERE pensja is NULL";
        String where6 = "FROM Pracownik WHERE nazwisko IN ('Hutton','Errazuriz','Kowal')";


        //obiekt klasy Query
        Query query = session.createQuery(where6);

        //wykonanie zapytania, pobranie listy encji
        List<Pracownik> pracownicy = query.getResultList();

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //wyświetlanie pobranej listy
        int i = 1;
        for (Pracownik pracownik : pracownicy) {
            System.out.println(i + ") " + pracownik);
            i++;
        }

        //zamknięcie obiektu SessionFactory
        factory.close();

    }
}
