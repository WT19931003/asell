package cn.itsource.aisell.common;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.StringWriter;

public class VelocityTest {

     @Test
      public void testHello() throws Exception{
         //准备模板技术引擎
         VelocityEngine ve = new VelocityEngine();
         //准备相应的模板  1.找到模板
         Template template = ve.getTemplate("temp/hello.vm","UTF-8");
         //准备上下文
         VelocityContext velocityContext = new VelocityContext();
         //准备数据  2.准备数据
        // velocityContext.put("txt", "你好, 世界!");
         velocityContext.put("domain", "Employee");

         //一个String的流进行相应的输出
         //3.流(什么流输出)
         StringWriter stringWriter = new StringWriter();
         template.merge(velocityContext, stringWriter);
         System.out.println(stringWriter.toString());
      }
}
