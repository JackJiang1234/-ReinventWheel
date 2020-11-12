import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


/**
 * @author jack.jiang
 * @version 1.0
 * @date 2020/11/12 14:21
 */
public class CompanyProducerTest {
    public static void main(String[] args) throws InterruptedException {
        KafkaProducer<String, Company> producer =
                new KafkaProducer<>(KafkaConfig.initProducerConfig(StringSerializer.class, CompanySerializer.class));

        testFireAndForget(producer);
        producer.close();

        Thread.sleep(2000);
        System.out.println("end!");
    }

    //fire and forget
    private static void testFireAndForget(KafkaProducer<String, Company> producer) {
        try {
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, Company> record = new ProducerRecord<>(KafkaConfig.TOPIC, new Company("jack", "changsha"));
                producer.send(record);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
