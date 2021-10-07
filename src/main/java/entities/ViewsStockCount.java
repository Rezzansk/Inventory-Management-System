package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "stock_count")
public class ViewsStockCount {

    @Id
    private int p_total_price;

}