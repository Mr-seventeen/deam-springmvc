package java8;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author: qixiujuan
 * @date: 2019/4/22
 */
@Data
@Builder
@AllArgsConstructor
public class Apple {

    private Integer weight;
    private String color;
}
