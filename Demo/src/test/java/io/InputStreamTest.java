package io;

import java.io.*;

/**
 * @author: qixiujuan
 * @date: 2019/5/15
 */
public class InputStreamTest {

    public static void main(String[] args) {
        //字节流读入
        inputStreamMethod();
    }

    private static void inputStreamMethod(){

        String path = "C:"+ File.separator +"Users"+ File.separator +"qixiujuan"+ File.separator +"Desktop"+ File.separator +"qqqq.txt";
        String newPath = "C:"+ File.separator +"Users"+ File.separator +"qixiujuan"+ File.separator +"Desktop"+ File.separator +"dddd.txt";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            fileOutputStream = new FileOutputStream(newPath);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            /*int ch = 0;
            while ((ch = fileInputStream.read()) != -1) {
                System.out.println((char) ch);
            }*/

            /*int ch = 0;
            byte[] buf = new byte[1024];
            while ((ch = fileInputStream.read(buf)) != -1){
                System.out.print(new String(buf, 0 , ch));
                fileOutputStream.write(buf, 0, ch);
            }*/



            byte[] buf = new byte[fileInputStream.available()];
//            fileInputStream.read(buf);
//            fileOutputStream.write(buf);
            bufferedInputStream.read(buf);
            bufferedOutputStream.write(buf);
            System.out.println(new String(buf));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != fileOutputStream){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
