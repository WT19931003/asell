package cn.itsource.aisell.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EasyPoiTest {

    // ctrl+alt+L
    @Test
    public void testHello() throws Exception {
        //准备导出的数据
        POIEmployee e1 = new POIEmployee();
        e1.setName("张三");
        e1.setEmail("san@qq.com");
        POIEmployee e2 = new POIEmployee();
        e2.setName("李四");
        e2.setEmail("li@qq.com");

        List<POIEmployee> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        /**
         * excel生成的工具类
         *  ExportParams：一些基本配置(不需要的)
         *  POIEmployee.class :导出的对象的类型
         *  list：要导出的数据
         */
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),
                POIEmployee.class, list);

        //写一个文件输出流，把内存中的excel文件写出去
        FileOutputStream fos = new FileOutputStream("employee.xlsx");
        workbook.write(fos);
        fos.close();
    }


    @Test
    public void testHello2() throws Exception {

        POIDepartment d1 = new POIDepartment();
        d1.setName("IT部");
        d1.setAddress("四川哈哈");
        POIDepartment d2 = new POIDepartment();
        d2.setName("公关部");
        d2.setAddress("四川呵呵");

        List<POIDepartment> list = new ArrayList<>();
        list.add(d1);
        list.add(d2);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),
                POIDepartment.class, list);

        //写一个文件输出流，把内存中的excel文件写出去
        FileOutputStream fos = new FileOutputStream("department.xlsx");
        workbook.write(fos);
        fos.close();

    }

    @Test
    public void testHaha() throws Exception {

        POIDepartment department = new POIDepartment();
        department.setName("IT部");
        department.setAddress("四川哈哈");

        //准备导出的数据
        POIEmployee e1 = new POIEmployee();
        e1.setName("张三");
        e1.setEmail("san@qq.com");
        e1.setAge(10);
        e1.setSex(true);
        e1.setHeadImage("5/1.jpg"); //头像
        e1.setDepartment(department);
        POIEmployee e2 = new POIEmployee();
        e2.setName("李四");
        e2.setEmail("li@qq.com");
        e2.setAge(20);
        e2.setSex(false);
        e2.setHeadImage("5/2.jpg"); //头像
        e2.setDepartment(department);

        List<POIEmployee> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        /**
         * excel生成的工具类
         *  ExportParams：一些基本配置(不需要的)
         *  POIEmployee.class :导出的对象的类型
         *  list：要导出的数据
         */
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工信息","第一张"),
                POIEmployee.class, list);

        //写一个文件输出流，把内存中的excel文件写出去
        FileOutputStream fos = new FileOutputStream("employee.xlsx");
        workbook.write(fos);
        fos.close();
    }


     @Test
      public void testImport() throws Exception{
         ImportParams params = new ImportParams();
         params.setTitleRows(1);
         params.setHeadRows(1);

         List<POIEmployee> list = ExcelImportUtil.importExcel(
                 new File("employee.xlsx"),
                 POIEmployee.class, params);


        list.forEach(e -> System.out.println(e));
      }

}
