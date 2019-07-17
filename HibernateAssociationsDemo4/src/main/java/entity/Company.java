package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Robert Burek
 */

@Entity
@Table(name = "company")
@ToString
@NoArgsConstructor
//@AllArgsConstructor
public class Company {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Integer idCompany;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "value")
    private Integer value;

    @Getter
    @Setter
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company_detail")
    private CompanyDetail companyDetail;

    @Getter
    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "company",
            //           fetch = FetchType.EAGER,    // wszystkie dane również powiązane pobierane są od razu
            fetch = FetchType.LAZY,  // pobiera powiązane dane dopiero gdy chcemy ich użyć
            cascade = {CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private List<Property> properties;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company")
    private Set<Department> departments;

    public Company(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public void addProperty(Property property) {
        if (properties == null) {
            properties = new ArrayList<Property>();
        }
        properties.add(property);
        property.setCompany(this);
    }

    public void addDepartment(Department department) {
        if (departments == null) {
            departments = new HashSet<Department>();
        }
        departments.add(department);
    }
}