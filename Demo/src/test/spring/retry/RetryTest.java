package retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author: qixiujuan
 * @date: 2019/4/19
 */
@Slf4j
public class RetryTest {

    public static void main(String[] args) {
        /**
         * spring-retry 重试
         */
        RetryTemplate retryTemplate = new RetryTemplate();
        try {
            Integer result = retryTemplate.execute(new RetryCallback<Integer, Throwable>() {
                @Override
                public Integer doWithRetry(RetryContext retryContext) throws Throwable {
                    log.info("循环执行");
                    testRedo();
                    return 1;
                }
            }, new RecoveryCallback<Integer>() {
                @Override
                public Integer recover(RetryContext retryContext) throws Exception {
                    log.info("异常");
                    return 0;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private static void testRedo() {
        log.info("执行代码");
        throw new RuntimeException();
    }
}
