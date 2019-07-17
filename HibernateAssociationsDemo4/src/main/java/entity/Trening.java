package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert Burek
 */
@Entity
@Table(name = "trening")
@ToString
@NoArgsConstructor
public class Trening {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trening")
    private Integer idTrening;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinTable(name = "pracownik_trening",
            joinColumns = @JoinColumn(name = "id_trening"),
            inverseJoinColumns = @JoinColumn(name = "id_pracownik"))
    private List<Pracownik> pracownicy;


    public Trening(String name) {
        this.name = name;
    }

    public void addPracownik(Pracownik pracownik) {
        if (pracownicy == null) {
            pracownicy = new ArrayList<Pracownik>();
        }
        pracownicy.add(pracownik);
    }

}
