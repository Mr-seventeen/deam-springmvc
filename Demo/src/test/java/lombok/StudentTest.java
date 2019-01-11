package lombok;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: qixiujuan
 * @Describe StudentTest
 * @Date: 2019/1/10
 * @Modified By：
 */
@Slf4j
@RunWith(JUnit4.class)
public class StudentTest {

    /*
    * @Setter
    * @Getter
    * @NoArgsConstructor
    * @AllArgConstructor
    * @RequiredArgsConstructor
    * */
    @Test
    public void testStudent(){
        log.info("---------------- begin ----------------");
        Student noArgsStudent = new Student();
        noArgsStudent.setName("qixiujuan");
        noArgsStudent.setCode("XDF0419");
        log.info("this is student and name is {} code is {}", noArgsStudent.getCode(), noArgsStudent.getName());
        Student allArgsStudent = new Student("XDF0420","zhangyixing",1, "001");
        log.info("this is allArgsStudent and name is {} code is {}", allArgsStudent.getCode(), allArgsStudent.getName());

        log.info("compara result is {}",noArgsStudent.equals(allArgsStudent));
    }


    @Test
    public void testCleanup(){
        try {

            this.newReadFile("E:\\t1.txt","E:\\t2.txt");
            this.originReadFile("E:\\t1.txt","E:\\t2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@Cleanup注解，无需手动关闭数据流
    * */
    private void newReadFile(String in, String out) throws IOException {
        @Cleanup
        FileInputStream inputStream = new FileInputStream(in);
        @Cleanup
        FileOutputStream outputStream = new FileOutputStream(out);
        byte[] b = new byte[65536];
        while (true){
            int r = inputStream.read(b);
            if(r == -1) break;;
            outputStream.write(b,0,r );
        }
    }
    /*原来的写法：需要将数据流都关闭*/
    private void  originReadFile(String in, String out) throws IOException {
        FileInputStream inputStream = new FileInputStream(in);
        try {
            FileOutputStream outputStream = new FileOutputStream(out);
            try {
                byte[] b = new byte[10000];
                while (true){
                    int r = inputStream.read(b);
                    if(r == -1) break;
                    outputStream.write(b,0,r);
                }
            } finally {
                if(outputStream != null) {
                    outputStream.close();
                }
            }
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
        }

    }
}
