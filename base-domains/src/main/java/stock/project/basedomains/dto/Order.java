package stock.project.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rajiv.kumar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String productId;

    private String name;

    private int qty;

    private double price;
}
