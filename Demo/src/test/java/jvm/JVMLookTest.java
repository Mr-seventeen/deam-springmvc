package jvm;

/**
 * @Author: qixiujuan
 * @Describe JVMLookTest
 * @Date: 2019/1/17
 * @Modified By：
 */

public class JVMLookTest {


    public static void main(String[] args) {
        try {
            Class.forName("jvm.JVMLookTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    class Student {
        private String name;

        Student(String name) {
            this.name = name;
        }
    }
}
