package stock.project.stockservice.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import stock.project.basedomains.dto.OrderEvent;
import stock.project.basedomains.dto.StockAvailabilityEvent;
import stock.project.stockservice.kafka.producer.OrderPlacementProducer;
import stock.project.stockservice.repository.StockDataRepository;

/**
 * @author rajiv.kumar
 */
@Service
public class OrderPlacementConsumer {

    @Autowired
    public OrderPlacementConsumer(final OrderPlacementProducer orderPlacementProducer,
                                  final StockDataRepository stockDataRepository) {

        this.orderPlacementProducer = orderPlacementProducer;
        this.stockDataRepository = stockDataRepository;
    }

    private OrderPlacementProducer orderPlacementProducer;


    private StockDataRepository stockDataRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.check.name}", groupId = "${spring.kafka.consumer.group-id}")
    private void consumer(OrderEvent orderEvent) {

        LOGGER.info(String.format("2. Checking Stock  %s", orderEvent.toString()));
        //here it needs to check the database and based on that it will create StockAvailabilityEvent
        boolean isProductInStock = stockDataRepository.existsByProductIdAndAvailableQuantityGreaterThan(
                Long.parseLong(orderEvent.getOrder().getProductId()), 0);
        StockAvailabilityEvent stockAvailabilityEvent = new StockAvailabilityEvent();
        stockAvailabilityEvent.setOrder(orderEvent.getOrder());
        stockAvailabilityEvent.setAvailable(isProductInStock);
        orderPlacementProducer.sendMessage(stockAvailabilityEvent, "order_check_topic");
    }

}
