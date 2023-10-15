package stock.project.orderservice.service;

import org.springframework.stereotype.Service;
import stock.project.basedomains.dto.Order;
import stock.project.orderservice.entity.OrderData;
import stock.project.orderservice.repository.OrderRepository;

import java.util.Optional;

/**
 * @author rajiv.kumar
 */
@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public void saveOrder(final
                          Order order) {

        OrderData orderData = new OrderData();
        orderData.setOrderID(2);
        orderData.setName(order.getName());
        orderData.setStatus("Pending");
        orderData.setPrice(order.getPrice());
        orderRepository.save(orderData);
    }

    public void updateOrderStatus(final String status,
                                  final long productId) {

        Optional<OrderData> optionalProduct = orderRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            OrderData orderData = optionalProduct.get();
            orderData.setStatus(status);
            orderRepository.save(orderData);
        }
    }

    public boolean checkOrderStatus(final long orderId) {

        Optional<OrderData> optionalProduct = orderRepository.findById(orderId);

        return optionalProduct.isPresent() && optionalProduct.get().getStatus().equals("Success");

    }

}
