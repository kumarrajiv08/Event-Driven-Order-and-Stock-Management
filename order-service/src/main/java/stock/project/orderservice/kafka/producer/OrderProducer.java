package stock.project.orderservice.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import stock.project.basedomains.dto.OrderEvent;

/**
 * @author rajiv.kumar
 */
@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic newTopic;

    public OrderProducer(final NewTopic newTopic,
                         final KafkaTemplate<String, OrderEvent> kafkaTemplate) {

        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendMessage(OrderEvent event, String topicName) {

        LOGGER.info(String.format("5 : Order Event %s", event.toString()));
        Message<OrderEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, newTopic.name()).build();
        kafkaTemplate.send(message);
    }


}
