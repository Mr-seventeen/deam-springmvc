package commons.lang;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: qixiujuan
 * @date: 2019/4/17
 */
public class ArrayUtilsTest {

    /**
     * logger for ArrayUtilsTest
     */
    private static final Logger logger = LoggerFactory.getLogger(ArrayUtilsTest.class);

    public static void main(String[] args) {
        int[] sumSubarray = new int[]{1,2,3,4};
        int[] result = ArrayUtils.subarray(sumSubarray, 1,5);

        logger.info("返回结果：{}", result);

    }

}
