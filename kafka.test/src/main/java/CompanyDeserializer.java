import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author jack.jiang
 * @version 1.0
 * @date 2020/11/12 18:41
 */
public class CompanyDeserializer implements Deserializer<Company> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Company deserialize(String topic, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        if (bytes.length < 8) {
            throw new SerializationException("Size of data received by CompanyDeserializer is shorter than expected!");
        }

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        Supplier<String> parse = () -> {
            int len = buffer.getInt();
            byte[] contentBytes = new byte[len];
            buffer.get(contentBytes);
            return new String(contentBytes, StandardCharsets.UTF_8);
        };

        return new Company(parse.get(), parse.get());
    }

    @Override
    public void close() {

    }
}
