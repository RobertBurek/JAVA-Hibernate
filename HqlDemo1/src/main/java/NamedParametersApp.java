import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Robert Burek
 */
public class NamedParametersApp {
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

        //parametry podane przez użytkownika
        String imiePracownika = "Steven";
        String nazwiskoPracownika = "King";
        int pensjaPracownika = 10000;


        //rozpoczniemy transakcję
        session.beginTransaction();

        //rozwiązanie przez konkatenację stringów
        String namedPar = "SELECT imie,nazwisko, pensja FROM Pracownik WHERE imie = '" + imiePracownika + "'" +
                " AND nazwisko = '" + nazwiskoPracownika + "'";

        Query query = session.createQuery(namedPar);
        List<Object[]> danePracownikowString = query.getResultList();

        //NAMED PARAMETERS - wyszukiwanie wybranych elementów danych podanych z zewnątrz
        String namedPar1 = "SELECT imie, nazwisko, pensja FROM Pracownik WHERE imie = :imie AND nazwisko = :nazwisko";
        String namedPar2 = "SELECT p.imie, p.nazwisko, p.pensja FROM Pracownik AS p WHERE p.pensja >= :pensja";


        //obiekt klasy Query
        Query queryNamedPar = session.createQuery(namedPar2);
//        queryNamedPar.setParameter("imie", imiePracownika);
//        queryNamedPar.setParameter("nazwisko", nazwiskoPracownika);
        queryNamedPar.setParameter("pensja", pensjaPracownika);

        //wykonanie zapytania, pobranie listy encji
        List<Object[]> danePracownikow = queryNamedPar.getResultList();

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //wyświetlanie pobranej listy w zależności od ilości pobranych danych
        int i = 1, j = 0;
        String dane;
        for (Object[] danePracownika : danePracownikow) {
            dane = "";
            for (j = 0; j < danePracownika.length; j++)
                dane = dane + ", " + danePracownika[j];
            System.out.println(i + ") Dane: " + dane);
            i++;
        }

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}
