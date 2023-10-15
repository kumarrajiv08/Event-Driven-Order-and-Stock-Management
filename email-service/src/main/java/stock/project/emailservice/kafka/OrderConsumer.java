package stock.project.emailservice.kafka;

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
    private void consume(OrderEvent orderEvent) {
        if(orderEvent.getStatus().equals("Failed")){
            LOGGER.info(String.format("Failure Email sent to customer %s", orderEvent.toString()));
        } else  LOGGER.info(String.format("Success Email sent to customer %s", orderEvent.toString()));

    }

}
