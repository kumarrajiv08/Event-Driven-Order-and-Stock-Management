package stock.project.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.project.stockservice.entity.StockData;

/**
 * @author rajiv.kumar
 */

public interface StockDataRepository extends JpaRepository<StockData, Long> {

    boolean existsByProductIdAndAvailableQuantityGreaterThan(final long productId,
                                                           final int availableQuantity);
}


