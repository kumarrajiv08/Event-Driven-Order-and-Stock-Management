package stock.project.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author rajiv.kumar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StockAvailabilityEvent {
    private Order order;
    private boolean isAvailable;

}
