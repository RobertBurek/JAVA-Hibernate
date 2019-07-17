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
@Table(name = "department")
@NoArgsConstructor
@ToString
public class Department {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private Integer idDepartment;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    public Department(String name) {
        this.name = name;
    }
}
