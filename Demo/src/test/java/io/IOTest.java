package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;

/**
 * @author: qixiujuan
 * @date: 2019/4/17
 */
public class IOTest {

    /**
     * logger for IOTest
     */
    private static final Logger logger = LoggerFactory.getLogger(IOTest.class);

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        ByteBuffer byteBuffer = new ByteBuffer(1,1,1,1);
    }
}
