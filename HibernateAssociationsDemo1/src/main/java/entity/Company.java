package entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Robert Burek
 */

@Entity
@Table(name = "company")
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_company_detail")
    private CompanyDetail companyDetail;

}