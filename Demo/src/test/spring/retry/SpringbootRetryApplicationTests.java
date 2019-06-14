package retry;

import com.retry.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: qixiujuan
 * @date: 2019/5/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-servlet.xml"})
public class SpringbootRetryApplicationTests {

    /**
     * logger for SpringbootRetryApplicationTests
     */
    private static final Logger logger = LoggerFactory.getLogger(SpringbootRetryApplicationTests.class);

    @Autowired
    private PayService payService;

    @Test
    public void payTest(){
        int store = payService.minGoodsnum(-1);
        logger.info("库存为:{}", store);
    }
}
