package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
/**
 * @Author: qixiujuan
 * @Describe ThreadTest
 * @Date: 2019/1/7
 * @Modified By：
 */
public class ThreadTest {
    /**
     * logger for ThreadTest
     */
    private static final Logger log = LoggerFactory.getLogger(ThreadTest.class);

    public void threadTest1() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
    }
}
