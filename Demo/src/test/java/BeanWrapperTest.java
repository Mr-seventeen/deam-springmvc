import com.google.common.collect.Iterables;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

public class BeanWrapperTest {

    public static void main(String[] args) {
        User user = new User();
//        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user);
        BeanWrapper bw = new BeanWrapperImpl(user);
        bw.setPropertyValue("userName", "张三");
        System.out.println(user.getUserName());// 输出张三
        PropertyValue value = new PropertyValue("userName", "李四");
        bw.setPropertyValue(value);
        System.out.println(user.getUserName());// 输出李四
        PropertyValue value2 = new PropertyValue("age", "111");
        bw.setPropertyValue(value2);
//        new Iterable<>()

        List<List<String>> list = new ArrayList<List<String>>();
//        List<String> listNew = Iterables.concat(list);
    }

    public static class User {
        String userName;
        public String getUserName(){
            return userName;
        }
        public void setUserName(String userName){
            this.userName = userName;
        }


    }


}
