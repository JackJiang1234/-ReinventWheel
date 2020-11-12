import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;

/**
 * @author jack.jiang
 * @version 1.0
 * @date 2020/11/12 14:21
 */
public class CompanyConsumerTest {
    public static void main(String[] args) {
        KafkaConsumer<String, Company> consumer
                = new KafkaConsumer<>(KafkaConfig.initConsumeConfig(StringDeserializer.class, CompanyDeserializer.class));
        consumer.subscribe(Collections.singletonList(KafkaConfig.TOPIC));
        System.out.println("consume start...");
        while (true) {
            ConsumerRecords<String, Company> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Company> record : records) {
                System.out.println(record.value());
            }
        }
    }
}
