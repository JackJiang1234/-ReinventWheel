import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author jack.jiang
 * @version 1.0
 * @date 2020/11/12 14:21
 */
public class KafkaConfig {
    //public final static String BROKER_LIST = "192.168.134.136:9093";
    public final static String BROKER_LIST = "81.68.253.72:9092";
    public final static String TOPIC = "topic-demo";
    public final static String GROUPID = "GROUP.DEMO";

    public static Properties initProducerConfig() {
        return initProducerConfig(StringSerializer.class, StringSerializer.class);
    }

    public static Properties initProducerConfig(Class<?> keySerializerClass, Class<?> valueSerializerClass) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.BROKER_LIST);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "java.producer.test");
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName());

        return properties;
    }

    public static Properties initConsumeConfig() {
        return initConsumeConfig(StringDeserializer.class, StringDeserializer.class);
    }

    public static Properties initConsumeConfig(Class<?> keyDeserializerClass, Class<?> valueDeserializerClass) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClass.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.BROKER_LIST);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfig.GROUPID);

        return properties;
    }
}
