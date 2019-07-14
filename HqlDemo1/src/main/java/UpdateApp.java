import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by Robert Burek
 */
public class UpdateApp {
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
        int idPracownika = 22;
        int pensjaPracownika = 7777;
        String nazwiskoPracownika = "Nowak";


        //rozpoczniemy transakcję
        session.beginTransaction();


        //UPDATE - zmiana jednej danej u wybranego pracownika z użyciem prarametru opisanego
        String upDate = "UPDATE Pracownik AS p SET p.pensja = :pensjaPrac WHERE p.id_pracownik = :idPrac";
        String upDate2 = "UPDATE Pracownik AS p SET p.nazwisko = :nazwiskoPrac WHERE p.id_pracownik = :idPrac";


        //obiekt klasy Query
        Query queryNamedPar = session.createQuery(upDate2);
//        queryNamedPar.setParameter("pensjaPrac", pensjaPracownika);
        queryNamedPar.setParameter("idPrac", idPracownika);
        queryNamedPar.setParameter("nazwiskoPrac", nazwiskoPracownika);

        //wykonanie zmian
        queryNamedPar.executeUpdate();

        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}