package guava;



import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.limit;
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
        Lists.newArrayList();

        List<String> names = listOf("Aleksander", "Jaran", "Integrasco", "Guava", "Java");

//        Iterable<String> filtered = filter(names, or(or(equalTo("Aleksander"),equalTo("Jaran")), lengthLessThan(5)));
        Iterable<String> filtered = filter(names, or(or(equalTo("Aleksander"),equalTo("Jaran")), lengthLessThan(5)));

        log.info("this is filtered {}", filtered);

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
