package stock.project.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author rajiv.kumar
 */
public class StockCheckAvailabilityConfig {
    @Value("${spring.kafka.topic.check.name}")
    private String topicName;

    @Bean
    public NewTopic topic() {

        return TopicBuilder.name(topicName).build();
    }
}
