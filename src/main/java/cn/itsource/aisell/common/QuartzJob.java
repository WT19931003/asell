package cn.itsource.aisell.common;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("quartzJob")
public class QuartzJob {


    public void work(){
        System.out.println(new Date().toLocaleString());
    }
}
