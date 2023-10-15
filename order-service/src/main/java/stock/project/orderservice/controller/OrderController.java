package stock.project.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stock.project.basedomains.dto.Order;
import stock.project.basedomains.dto.OrderEvent;
import stock.project.orderservice.kafka.producer.StockAvailabilityCheck;
import stock.project.orderservice.service.OrderService;

/**
 * @author rajiv.kumar
 */
@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    public OrderController(final StockAvailabilityCheck stockAvailabilityCheck,
                           final OrderService orderService) {

        this.stockAvailabilityCheck = stockAvailabilityCheck;
        this.orderService = orderService;

    }

    private StockAvailabilityCheck stockAvailabilityCheck;


    private OrderService orderService;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {

        OrderEvent orderEvent = new OrderEvent();
        order.setProductId("2");
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("order placed.");
        orderEvent.setOrder(order);

        orderService.saveOrder(order);
        stockAvailabilityCheck.sendMessage(orderEvent, "order-placement-topic");

        return "Order placed successfully...";
    }

    @GetMapping("/orders/{id}")
    public String placeOrder(@PathVariable("id") long orderId) {

        return orderService.checkOrderStatus(orderId) ? "Order Placed Successfully" : "Order not Placed";

    }


}
