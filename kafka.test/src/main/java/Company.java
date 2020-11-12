/**
 * @author jack.jiang
 * @version 1.0
 * @date 2020/11/12 18:37
 */

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Company {
    private String name;
    private String address;
}
