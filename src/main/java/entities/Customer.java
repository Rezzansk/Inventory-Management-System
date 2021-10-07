package entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cu_id;

    private String cu_name;
    private String cu_surname;
    private String cu_company_title;
    private long cu_code;
    private int cu_status;
    private int cu_tax_number;
    private String cu_tax_administration;
    @Column(length = 500)
    private String cu_address;
    private String cu_mobile;
    private String cu_phone;
    @Column(length = 500)
    private String cu_email;
    @Column(length = 32)
    private String cu_password;


    //@OneToMany(cascade = CascadeType.DETACH)
    //private Set<PurchaseOrder>  purchaseOrders;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BasketProducts> basketProducts;

}
