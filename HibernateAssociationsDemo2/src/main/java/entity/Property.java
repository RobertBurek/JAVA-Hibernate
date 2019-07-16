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
@NoArgsConstructor
@ToString
@Table(name = "property")
public class Property {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property")

    private Integer idProperty;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Getter
    @Setter
    @Column(name = "room_number")
    private Integer roomNumber;

    public Property(String city, Integer roomNumber) {
        this.city = city;
        this.roomNumber = roomNumber;
    }
}
