package java8;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import java8.impl.AppleGreenColorPredicate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: qixiujuan
 * @date: 2019/4/22
 */
public class AppleMainTest {

    /**
     * logger for AppleMainTest
     */
    private static final Logger logger = LoggerFactory.getLogger(AppleMainTest.class);

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate applePredicate) {
        List<Apple> listResult = Lists.newArrayList();
        for (Apple a : inventory) {
            if (applePredicate.test(a)) {
                listResult.add(a);
            }
        }
        return listResult;
    }


    @Test
    public  void testQuery1(){
        List<Apple> appleList = Arrays.asList(new Apple(100, "red"), new Apple(200, "green"));

        /**
         * 第一种实现
         */
        ApplePredicate applePredicate = new AppleGreenColorPredicate();
        List<Apple> result1 = filterApples(appleList, applePredicate);
        logger.info("查出结果是{}", result1);
        /**
         * 第二种实现-- 匿名类
         */
        List<Apple> result2 = filterApples(appleList, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150;
            }
        });
        logger.info("查出结果是{}", result2);

//        List<Apple> result3 = filterApples(appleList, (Apple apple) -> "red".equals(apple.getColor()));

    }


}
