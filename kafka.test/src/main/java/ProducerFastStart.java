import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.LocalDateTime;

/**
 * @author jack.jiang
 * @version 1.0
 * @date 2020/11/10 17:57
 */
public class ProducerFastStart {

    public static void main(String[] args) throws InterruptedException {
        KafkaProducer<String, String> producer =
                new KafkaProducer<>(KafkaConfig.initProducerConfig());

        //testFireAndForget(producer);
        //testSync(producer);
        testAsync(producer);

        producer.close();

        Thread.sleep(2000);
        System.out.println("end!");
    }

    //fire and forget
    private static void testFireAndForget(KafkaProducer<String, String> producer) {
        try {
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConfig.TOPIC, "hello, kafka!" + LocalDateTime.now());
                producer.send(record);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //sync
    private static void testSync(KafkaProducer<String, String> producer) {
        try {
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConfig.TOPIC, "hello, kafka!" + LocalDateTime.now());
                System.out.println(producer.send(record).get());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void testAsync(KafkaProducer<String, String> producer) {
        try {
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConfig.TOPIC, "hello, kafka!" + LocalDateTime.now());
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.println(metadata);
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
