import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class SelectApp {
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

        //SELECT - wybrane elementy danych - podajemy w pytaniu nazwy pól z encji nie z tabeli
        String select = "SELECT imie, nazwisko FROM Pracownik";
        String select1 = "SELECT nazwisko, imie FROM Pracownik";
        String select2 = "SELECT imie, nazwisko FROM Pracownik WHERE pensja > 15000";
        //z wykorzystanie aliasów  AS (opcjonaly zapis bez AS)
        String select3 = "SELECT prac.imie, prac.nazwisko FROM Pracownik AS prac WHERE pensja > 15000";
        String select4 = "SELECT prac.imie, prac.nazwisko FROM Pracownik prac WHERE pensja > 15000";
        String select5 = "SELECT nazwisko, pensja FROM Pracownik WHERE imie = 'Robert'";

        //obiekt klasy Query
        Query query = session.createQuery(select5);

        //wykonanie zapytania, pobranie listy encji
        List<Object[]> imieNazwiskoPracownikow = query.getResultList();

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //wyświetlanie pobranej listy
        int i=1;
        for (Object[] imieNazwiskoPracownika:imieNazwiskoPracownikow) {
            System.out.println(i+") Dane: "+imieNazwiskoPracownika[0]+", " + imieNazwiskoPracownika[1]);
            i++;
        }

        //zamknięcie obiektu SessionFactory
        factory.close();

    }
}
