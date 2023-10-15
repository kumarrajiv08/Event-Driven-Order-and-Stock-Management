package stock.project.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rajiv.kumar
 */
@Entity
@Getter
@Setter
@Table(name = "order_table")
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    @Column(columnDefinition="VARCHAR(255)")
    private String name;

    @Column(columnDefinition="VARCHAR(255)")
    private String status;

    @Column(columnDefinition="double precision")
    private double price;

}
