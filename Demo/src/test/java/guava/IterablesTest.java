package guava;



import com.google.common.base.*;
import com.google.common.collect.*;
import lombok.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.limit;
import static com.google.common.collect.Lists.newArrayList;
import static org.spockframework.util.CollectionUtil.listOf;

/**
 * @Author: qixiujuan
 * @Describe IterablesTest
 * @Date: 2019/1/10
 * @Modified By：
 */
@Slf4j
public class IterablesTest {

    public static void main(String[] args) {

        List<List<String>> list = new ArrayList<List<String>>();
        Iterables.concat(list);

        String a = "aaacccddd";
        String b = "aaabbbccc";
        Strings.isNullOrEmpty(a);
        Strings.commonSuffix(a,b);

        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            log.info(item);
        }
        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> map = Splitter.onPattern("[;,]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for(Map.Entry<String,String> entry : map.entrySet()){
            log.info(String.format("%s=%s", entry.getKey(),entry.getValue()));
        }

        Map<String, String> map2= new HashMap<String, String>();
        map2.put("a","b");
        map2.put("c","d");
        String[] arg = new String[]{"hello","world"};
        log.info("" + Joiner.on("-").join(arg));

//        ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");
//        ImmutableMap<String, String> map = ImmutableMap.of("aa","aa","bbb","bbb");
//        ImmutableList<String> list2 = listOf("a","b","c","d");

//        Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
//        Lists.newArrayList();

        List<String> names = listOf("Aleksander", "Jaran", "Integrasco", "Guava", "Java");
        List<String> includ = listOf("Guava", "Java");
        Iterable<String> filtered1 = filter(names, or(or(equalTo("Aleksander"),equalTo("Jaran")), lengthLessThan(5)));
        Iterable<String> filtered2 = filter(names, and(or(or(equalTo("Aleksander"),equalTo("Jaran")), lengthLessThan(5)),in(includ)));
        log.info("this is filtered {}", filtered1);
        log.info("this is filtered2 {}", filtered2);


        String num = CharMatcher.DIGIT.retainFrom("this is number 0-9 0123456789");
        log.info("result is num {}", num);

//        List<Student> listStu = newArrayList();
        List<Student> listStu = listOf(
                new Student("001","abd",1,"granld"),
                new Student("009","罗小黑",2,"grandl"),
                new Student("007","乔巴",2,"grandl"),
                new Student("003","路飞",2,"grandl"));
//        Ordering.from();

        Set<String> nameSet = Sets.newHashSet();
    }

    private  static class LengthLessThanPredicat implements Predicate<String>{

        private int length;

        private LengthLessThanPredicat(int length){
            this.length = length;
        }
        @Override
        public boolean apply(String input) {
            return input.length() < length;
        }
    }

    public static Predicate<String> lengthLessThan(int length){
        return new LengthLessThanPredicat(length);
    }


}
