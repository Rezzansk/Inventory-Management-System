package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "total_sale_value")
public class ViewsTotalSaleValue {

    @Id
    private int p_total_sale;

}