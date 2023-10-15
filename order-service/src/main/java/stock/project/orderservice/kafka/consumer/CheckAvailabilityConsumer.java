package stock.project.orderservice.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import stock.project.basedomains.dto.OrderEvent;
import stock.project.basedomains.dto.StockAvailabilityEvent;
import stock.project.orderservice.kafka.producer.OrderProducer;
import stock.project.orderservice.service.OrderService;

/**
 * @author rajiv.kumar
 */
@Service
public class CheckAvailabilityConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckAvailabilityConsumer.class);

    private OrderProducer orderProducer;
    private OrderService orderService;

    public CheckAvailabilityConsumer(final OrderProducer orderProducer,final OrderService orderService) {

        this.orderProducer = orderProducer;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.check.name}", groupId = "${spring.kafka.consumer.group-id}")
    private void consumer(StockAvailabilityEvent stockAvailabilityEvent) {

        LOGGER.info(String.format("4. Recieved stock result from stock service %s", stockAvailabilityEvent.toString()));
        boolean stockAvailable = stockAvailabilityEvent.isAvailable();
        if(stockAvailable) {
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrder(stockAvailabilityEvent.getOrder());
            orderEvent.setStatus("Success");
            orderEvent.setMessage("Order Placed!!!!!");
            orderService.updateOrderStatus("Success",Long.parseLong(stockAvailabilityEvent.getOrder().getProductId()));
            orderProducer.sendMessage(orderEvent, "order_topic");
        } else {
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrder(stockAvailabilityEvent.getOrder());
            orderEvent.setStatus("Failed");
            orderEvent.setMessage("Will notify.");
            orderService.updateOrderStatus("Failed",Long.parseLong(stockAvailabilityEvent.getOrder().getProductId()));
            orderProducer.sendMessage(orderEvent, "order_topic");
        }

    }
}
