package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "product_count")
public class ViewsProductCount {

    @Id
    private int pr_count;

}