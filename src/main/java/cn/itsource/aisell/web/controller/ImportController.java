package cn.itsource.aisell.web.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import cn.itsource.aisell.common.EmployeeExcelVerifyHandler;
import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.service.IDepartmentService;
import cn.itsource.aisell.service.IEmployeeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 专用于导入的Controller
 */
@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private EmployeeExcelVerifyHandler employeeExcelVerifyHandler;

    @RequestMapping("/index")
    public String index(){
        return "import";
    }

    @RequestMapping("/employeeXlsx")
    public String employeeXlsx(MultipartFile empFile, HttpServletResponse response) throws Exception {
        //准备导入的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        // need:需要  verfiy:核实,验证
        params.setNeedVerfiy(true); //需要验证
        //加入自定义验证
        params.setVerifyHandler(employeeExcelVerifyHandler);

        //excel导入的一个结果
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(
                empFile.getInputStream(),
                Employee.class, params);
        //引入正确的list（正常保存）
        result.getList().forEach(e ->{
            // System.out.println(e+","+e.getDepartment());
            //根据部门名称拿到它的部门，再放到对应的员工中
            Department dept = departmentService.findByName(e.getDepartment().getName());
            e.setDepartment(dept);
            //给一个默认密码
            e.setPassword("123");
            employeeService.save(e);
        });
//        //引入错误的list
//        result.getFailList().forEach(e ->{
//            System.out.println("错误的："+e);
//        });
        //如果有错误，就直接导出错误文件到前台
        // Verfiy:检验 Fail：失败
        if(result.isVerfiyFail()){
            //如果验证失败，代码到这里面来
            //失败的文件已经准备好了
            Workbook failWorkbook = result.getFailWorkbook();
            //把这个文件导出
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx");
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream ouputStream = response.getOutputStream();
            failWorkbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }

        return "import";
    }




    /**
     * SpringMVC数据接收使用：MultipartFile
     * @param empFile
     * @return
     */
      /*
        System.out.println(empFile.getName()); //empFile 上传的控件名称
        System.out.println(empFile.getOriginalFilename()); //文件名称
        System.out.println(empFile.getSize()); //文件大小
        System.out.println(empFile.getContentType()); //类型 application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
        */
      /*
    @RequestMapping("/haha")
    public String haha(MultipartFile empFile) throws Exception {

        //准备导入的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        //params.setHeadRows(1);

        List<Employee> list = ExcelImportUtil.importExcel(
                empFile.getInputStream(),
                Employee.class,
                params);

        list.forEach(e ->{
           // System.out.println(e+","+e.getDepartment());
            //根据部门名称拿到它的部门，再放到对应的员工中
            Department dept = departmentService.findByName(e.getDepartment().getName());
            e.setDepartment(dept);
            //给一个默认密码
            e.setPassword("123");
            employeeService.save(e);
        });

        return "import";
    }
    */
}
