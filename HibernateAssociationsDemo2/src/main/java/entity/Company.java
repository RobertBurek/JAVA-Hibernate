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
//    @OneToOne(cascade = CascadeType.PERSIST)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company_detail")
    private CompanyDetail companyDetail;

    @Getter
    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Property> properties;

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
}