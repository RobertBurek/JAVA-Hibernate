package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Robert Burek
 */

@Entity
@Table(name = "company_detail")
//@ToString
@NoArgsConstructor
//@AllArgsConstructor
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
    @Column(name = "employee_Number")
    private Integer employeeNumber;

    @Getter
    @Setter
    @OneToOne(mappedBy = "companyDetail", cascade = CascadeType.ALL) //nazwa pola z class Company odpowiadające
    // za relację z class CompanyDetail
    private Company company;

    @Override
    public String toString() {
        return "CompanyDetail{" +
                "idCompanyDetail=" + idCompanyDetail +
                ", residence='" + residence + '\'' +
                ", employeeNumber=" + employeeNumber +
                '}';
    }
}
