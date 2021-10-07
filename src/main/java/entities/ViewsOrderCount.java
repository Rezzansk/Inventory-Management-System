package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "order_count")
public class ViewsOrderCount {

    @Id
    private int or_count;

}