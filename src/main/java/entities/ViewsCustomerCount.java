package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "customer_count")
public class ViewsCustomerCount {

    @Id
    private int cu_count;

}
