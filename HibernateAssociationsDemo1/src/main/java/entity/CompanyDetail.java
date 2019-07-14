package entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Robert Burek
 */

@Entity
@Table(name = "company_detail")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetail {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company_detail")
    private Integer idCompanyDetail;

    @Getter
    @Setter
    @Column(name = "residence")
    private String residence;

    @Getter
    @Setter
    @Column(name = "employeeNumber")
    private Integer employeeNumber;

}
