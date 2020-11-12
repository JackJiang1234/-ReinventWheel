import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;

/**
 * @author jack.jiang
 * @version 1.0
 * @date 2020/11/12 18:41
 */
public class CompanySerializer implements Serializer<Company> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null) {
            return null;
        }
        byte[] name, address;
        Function<String, byte[]> convert = s -> s == null ? new byte[0] : s.getBytes(StandardCharsets.UTF_8);
        try {
            name = convert.apply(data.getName());
            address = convert.apply(data.getAddress());
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);

            return buffer.array();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public void close() {

    }
}
