package cn.itsource.aisell.common;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

     @Test
      public void testTimer2() throws Exception{
        //Java中做定时器的对象
         //delay:延时多时间执行   period:每过多少时间执行
         Timer timer = new Timer();
         timer.schedule(new TimerTask() { //子线程
             @Override
             public void run() {
                 System.out.println(new Date().toLocaleString());
             }
         }, 0, 1000);

         Thread.sleep(10000);
      }

    @Test
    public void testTimer() throws Exception{
        //有一段代码，每过一秒执行一次
        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(new Date().toLocaleString());
        }
    }
}
