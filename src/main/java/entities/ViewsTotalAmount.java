package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "total_amount")
public class ViewsTotalAmount {

    @Id
    private int p_total_amount;

}