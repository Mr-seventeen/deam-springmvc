package guava;



import com.google.common.base.*;
import com.google.common.collect.*;
import lombok.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Iterables.filter;
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

    @Test
    public void test(){
        List<Student> listStu = listOf(
                new Student("001","abd",1,"granld",1, new Date()),
                new Student("002","罗小黑",2,"grandl",2, new Date()),
                new Student("004","路飞",2,"grandl",3, new Date()));

        Ordering<Student> orderingBig = new Ordering<Student>() {
            @Override
            public int compare(Student left, Student right) {
                if(left.getStatus() == 3 && right.getStatus() == 3){
                    log.info("******************该处对比");
//                    return right.getBeginTime().compareTo(left.getBeginTime());
                    if(left.getBeginTime().after(right.getBeginTime())){
                        return -1;
                    } else {
                        return 1;
                    }
//                    return right.getBeginTime().compareTo(left.getBeginTime());
                    /**
                     *  a < b -1
                     *  a = b  0
                     *  a > b 1
                     *
                     */
                } else {
                    return 0;
                }
            }
        };

        listStu = orderingBig.sortedCopy(listStu);

        for(Student s : listStu){
            log.info("s : "+ s.getCode() +"  "+ s.getGrande().toString()+"  "+s.getName()+"  "+ s.getStatus()+"  "+s.getBeginTime());
        }
    }

    @Test
    public void testString(){
//        log.info("组合参数是[{}]", combinationString("111","333","ggg"));
        String delFiles = "1,2,3,4";
        List<Integer> delFileId = Lists.newArrayList();
        if(!StringUtils.isEmpty(delFiles)){
//            Splitter.onPattern(",").split(delFiles).iterator(x -> {delFileId.add(x);});
        }
        log.info(delFiles);
    }


    private String combinationString(Object... params) {
        Joiner joiner = Joiner.on("-").skipNulls();
        return joiner.join(params);

       /* StringBuffer sbKey = new StringBuffer();
        for (Object param : params) {
            if (param != null) {
                sbKey.append("-");
                sbKey.append(param);
            }
        }
        sbKey.deleteCharAt(0);
        return sbKey.toString();*/
    }
}
