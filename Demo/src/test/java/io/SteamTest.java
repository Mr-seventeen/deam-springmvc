package io;

import java.io.*;
import java.util.stream.IntStream;

/**
 * @author: qixiujuan
 * @date: 2019/5/15
 */
public class SteamTest {

    public static void main(String[] args) {
        steamTestMethod();
    }

    private static void steamTestMethod(){
        String fileName = "C:"+ File.separator +"Users"+ File.separator +"qixiujuan"+ File.separator +"Desktop"+ File.separator +"qqqq.txt";
        String newfileName = "C:"+ File.separator +"Users"+ File.separator +"qixiujuan"+ File.separator +"Desktop"+ File.separator +"aaaa.txt";
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int a = 3;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
        try {
            fileReader = new FileReader(fileName);
            fileWriter = new FileWriter(newfileName);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            /*int ru = fileReader.read();
            System.out.println((char)ru);*/

            /*int ru = 0;
            while ((ru = fileReader.read()) != -1){
                System.out.println((char)ru);
            }*/
            char[] chars = new char[1024];
            int ru = 0;
            while ((ru = bufferedReader.read(chars)) != -1) {
                System.out.println(new String(chars, 0, ru));
                bufferedWriter.write(chars, 0, ru);
//                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
