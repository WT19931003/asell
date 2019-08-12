package cn.itsource.aisell.common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

public class ExcelTest {

    //要求:创建一个excel(里面装一个99乘法表)
     @Test
      public void testWirter() throws Exception{
         //在内存中创建了一个excel文件
         SXSSFWorkbook workbook = new SXSSFWorkbook();
         //为这个文件创建一张表
         Sheet sheet = workbook.createSheet("99"); //类似于table
         //为这张表创建行
         for (int i = 1; i <= 9 ; i++) {
             Row row = sheet.createRow(i-1); //类似于以前的tr
             //为一行创建列(每一个格子)
             for (int j = 1; j <= i ; j++) {
                 Cell cell = row.createCell(j-1);
                 //在格子中添加相应的值
                 cell.setCellValue(i+"*"+j+"="+(i*j));
             }
         }
         //写一个文件输出流，把内存中的excel文件写出去
         FileOutputStream fos = new FileOutputStream("99.xlsx");
         workbook.write(fos);
         fos.close();
     }


     //要求:读取一个文件
     @Test
      public void testRead() throws Exception{
         //拿到这个工作本
         Workbook wb = WorkbookFactory.create(new File("emp.xlsx"));
         //读取到第一张表
         Sheet sheet = wb.getSheetAt(0);
         //1.获取到总行数（最后一行）
         int lastRowNum = sheet.getLastRowNum();
         for (int i = 2; i <= lastRowNum; i++) {
             //2.拿到每一行
             Row row = sheet.getRow(i);
             //3.拿到对应的行的总列数
             short lastCellNum = row.getLastCellNum();
             //4.遍历这个总列数
             for (int j = 0; j < lastCellNum; j++) {
                 Cell cell = row.getCell(j);
                 System.out.print(cell.getStringCellValue() +" ");
             }
             System.out.println();
         }

     }
}
