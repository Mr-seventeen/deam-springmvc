package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

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
        System.out.println(1);
    }

    public static void nioTcpserver(int listenPort) throws IOException {
        //创建一个选择器
        Selector selector = Selector.open();
        //创建一个通道
        ServerSocketChannel listnChannel = ServerSocketChannel.open();
        //将通信通道指定端口
        listnChannel.socket().bind(new InetSocketAddress(listenPort));
        //设置通道为非阻塞
        listnChannel.configureBlocking(false);
        //将选择器注册到通道
        listnChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if(selector.select(3000) == 0){
                continue;
            }
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()){
                SelectionKey key = keyIter.next();
                if(key.isAcceptable()){

                }
                if(key.isReadable()){

                }
                if(key.isValid() && key.isWritable()){

                }
                keyIter.remove();
            }
        }
    }
}
