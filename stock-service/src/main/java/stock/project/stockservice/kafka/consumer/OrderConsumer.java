package stock.project.stockservice.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import stock.project.basedomains.dto.OrderEvent;

/**
 * @author rajiv.kumar
 */
@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    private void consumer(OrderEvent orderEvent) {

        LOGGER.info(String.format("6: Order event from stock service %s", orderEvent.toString()));
    }

}
