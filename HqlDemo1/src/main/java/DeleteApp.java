import entity.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by Robert Burek
 */
public class DeleteApp {
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
        int idPracownika = 56;
        String nazwiskoPracownika = "Nowak";


        //rozpoczniemy transakcję
        session.beginTransaction();


        //UPDATE - zmiana jednej danej u wybranego pracownika z użyciem prarametru opisanego
        String delete = "DELETE Pracownik AS p WHERE p.id_pracownik = :idPrac";
        String delete2 = "DELETE Pracownik AS p WHERE p.nazwisko = :nazwiskoPrac";


        //obiekt klasy Query z dodanym parametrem
        Query queryNamedPar = session.createQuery(delete2);
//        queryNamedPar.setParameter("idPrac", idPracownika);
        queryNamedPar.setParameter("nazwiskoPrac", nazwiskoPracownika);

        //wykonanie zmian
        int ileDekete = queryNamedPar.executeUpdate();
        System.out.println("Usunięto: " + ileDekete);


        //zakończenie sesji poprzez pobranie transacji i wykonując metodę commit()
        session.getTransaction().commit();

        //zamknięcie obiektu SessionFactory
        factory.close();
    }
}