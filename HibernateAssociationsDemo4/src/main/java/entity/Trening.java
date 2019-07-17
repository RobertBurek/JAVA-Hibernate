package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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


    public Trening(String name) {
        this.name = name;
    }

}
