package java8;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author: qixiujuan
 * @date: 2019/4/24
 */
public class PredicateTest {

    /**
     * logger for PredicateTest
     */
    private static final Logger logger = LoggerFactory.getLogger(PredicateTest.class);

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T t : list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T t : list){
            c.accept(t);
        }
    }

    @Test
    public void mainTest(){
        //定义行为参数
        Predicate<String> nonEmptyStringPredicate = (String s) -> s.isEmpty();
        List<String> listOfString = Lists.newArrayList();
        List<String> nonEmpty = filter(listOfString, nonEmptyStringPredicate);

        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));

        /**
         *
         */
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h1 = f.andThen(g);
        Function<Integer, Integer> h2 = f.compose(g);
        int result1 = h1.apply(5);
        int result2 = h2.apply(5);
        logger.info("andThen:应用复合含税返回的结果：{}", result1);
        logger.info("compose:应用复合含税返回的结果：{}", result2);
    }
}
