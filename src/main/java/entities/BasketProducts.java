package entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BasketProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bp_id;

    //private String bp_admin;
    //private int bp_product;
    private int bp_amount;
    private long bp_code;
    private boolean bp_status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_cuId")
    @ToString.Exclude
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_pId")
    @ToString.Exclude
    private Product products;


}
