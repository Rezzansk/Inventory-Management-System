package entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int p_id;

    @Column(length = 500)
    private String p_title;
    private int p_purchase_price;
    private int p_sale_price;
    private long p_code;
    private int p_tax;
    private int p_unit;
    private int p_amount;
    @Column(length = 500)
    private String p_detail;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "products")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BasketProducts> basketProducts;

}
