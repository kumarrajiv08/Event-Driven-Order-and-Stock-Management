package stock.project.stockservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rajiv.kumar
 */
@Entity
@Getter
@Setter
@Table(name = "stock_table")
public class StockData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stockId;

    @Column(columnDefinition="integer")
    private long productId;

    @Column(columnDefinition="integer")
    private int availableQuantity;
}
