package guava;



import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.out.println(item);
        }
        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> map = Splitter.onPattern("[;,]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
        }

        Map<String, String> map2= new HashMap<String, String>();
        map2.put("a","b");
        map2.put("c","d");
        String[] arg = new String[]{"hello","world"};
        log.info("" + Joiner.on("-").join(arg));
    }
}
