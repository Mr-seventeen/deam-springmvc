package concurrent;

import java.util.List;
import java.util.concurrent.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: qixiujuan
 * @Describe FutureTest
 * @Date: 2019/1/18
 * @Modified By：
 */
@Slf4j
@RunWith(JUnit4.class)
public class FutureTest {

    public static void testMain() {
//        List<Future<?>> fautreList = Lists.newArrayList();

        try {
            Future<List<String>> listName = Executors.newCachedThreadPool().submit(
                    new Callable<List<String>>() {
                        @Override
                        public List<String> call(){
                            return getNames();
//                            return Collections.singletonList("name");
                        }
                    }
            );

            if(listName.isDone()){
                log.info("加载完毕");
            } else {
                log.error("加载中。。。");
//                log.info("listName 的数量为{}",listName.get().size());
            }
            Thread.sleep(1000);
            if(listName.isDone()){
                log.info("**2**加载完毕");
//                log.info("**2**listName 的数量为{}",listName.get().size());
            } else {
                log.error("**2**加载中。。。");
            }
            Thread.sleep(4000);
            if(listName.isDone()){
                log.info("**3**加载完毕");
            } else {
                log.error("**3**加载中。。。");
            }
            log.info("listName 的数量为{}",listName.get().size());
        } catch (Exception e){
            log.error("there have error {}", e);
        } finally {
            log.info("the main is end");
        }

        /*Server server = new Server();3
        FutureData<String> futureData = server.getString();

        //先执行其他操作s
        String hello = "hello";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print(hello + " " + futureData.getData());*/


    }

    public static List<String> getNames() {
        try {
            Thread.sleep(10000);
            log.info("getNames 加载完毕------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList("小明","小红","小张");
    }

   /* public class Server {
        public FutureData<String> getString() {
            final FutureData<String> data = new FutureData<>();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    data.setData("world");
                }
            }).start();

            return data;
        }
    }*/


   @Test
   public void getUserAgeTest() throws InterruptedException, TimeoutException, ExecutionException {
        log.info("开始获取用户年龄。。。");
       Future<Integer> task = Executors.newCachedThreadPool().submit(
               new Callable<Integer>() {
                   @Override
                   public Integer call() throws InterruptedException {
                       Thread.sleep(3000);
                       return 10;
                   }
               }
       );
       if(task.isDone()){
           task.get(2, TimeUnit.DAYS.SECONDS);
       }
//       log.info("***当前任务是否执行完毕{}，得到的用户年龄是{}",task.isDone());
       Integer trueAge = task.get();
       log.info("&&&当前任务是否执行完毕{}，得到的用户年龄是{}",task.isDone(), trueAge);

   }

   @Test
   public void getFutureTask() throws Exception{
       log.info("开始执行。。。");
       Integer value = 1;
       FutureTask task = new FutureTask<Integer>(new Runnable() {
           @Override
           public void run() {
               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               log.info("运行中。。。");
           }
       },value);
//       task.run();
       log.info("当前完成状态{},当前value的值{}",task.isDone(),value);
       log.info("执行状态：{}",task.isDone());


       task.get();
   }




}
