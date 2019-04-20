package lombok;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.extern.slf4j.Slf4j;

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
        Student allArgsStudent = new Student("XDF0420","zhangyixing",1, "001", 1 , new Date());
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
