package stock.project.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.project.orderservice.entity.OrderData;

/**
 * @author rajiv.kumar
 */
public interface OrderRepository extends JpaRepository<OrderData, Long> {

}
