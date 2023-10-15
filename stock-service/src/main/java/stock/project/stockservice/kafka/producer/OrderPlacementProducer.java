package stock.project.stockservice.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import stock.project.basedomains.dto.StockAvailabilityEvent;

/**
 * @author rajiv.kumar
 */
@Service
public class OrderPlacementProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPlacementProducer.class);

    public OrderPlacementProducer(final KafkaTemplate<String, StockAvailabilityEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    private KafkaTemplate<String, StockAvailabilityEvent> kafkaTemplate;

    public void sendMessage(StockAvailabilityEvent event, String topicName) {

        LOGGER.info(String.format("3: Log from OrderPlacementProducer %s", event.toString()));
        Message<StockAvailabilityEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, topicName).build();
        kafkaTemplate.send(message);
    }
}
