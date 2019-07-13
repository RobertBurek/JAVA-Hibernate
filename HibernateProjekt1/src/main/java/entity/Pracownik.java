package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Robert Burek
 */
@Entity
@Table(name="pracownicy")
public class Pracownik {

    @Id
    @Column(name="id_pracownik")
    private Integer id_pracownik;

    @Column(name="imie")
    private String imie;

    @Column(name="nazwisko")
    private String nazwisko;

    @Column(name="pensja")
    private Integer pensja;

    public Pracownik() {
    }

    public Integer getId_pracownik() {
        return id_pracownik;
    }

    public void setId_pracownik(Integer id_pracownik) {
        this.id_pracownik = id_pracownik;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getPensja() {
        return pensja;
    }

    public void setPensja(Integer pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "id_pracownik=" + id_pracownik +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pensja=" + pensja +
                '}';
    }
}
