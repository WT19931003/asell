package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.domain.*;
import cn.itsource.aisell.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/util")
public class UtilController {
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;
    @Autowired
    private IProducttypeService producttypeService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IProductService productService;

    //拿到所有部门
    @RequestMapping("/dept")
    @ResponseBody
    public List<Department> deptList(){
        return departmentService.findAll();
    }

    //拿到登录用户的菜单(tree)
    @RequestMapping("/treeMenu")
    @ResponseBody
    public List<Menu> treeMenu(){
        return menuService.findTreeByLoginUser();
    }

    //拿到所有品牌
    @RequestMapping("/findAllBrand")
    @ResponseBody
    public List<Systemdictionarydetail> findAllBrand(){
        return systemdictionarydetailService.findAllBrand();
    }

    //拿到所有单位
    @RequestMapping("/findAllUnit")
    @ResponseBody
    public List<Systemdictionarydetail> findAllUnit(){
        return systemdictionarydetailService.findAllUnit();
    }

    @RequestMapping("/findChildren")
    @ResponseBody
    public List<Producttype> findChildren(){
       return producttypeService.findChildren();
    }

    @RequestMapping("/allSupplier")
    @ResponseBody
    public List<Supplier> allSupplier(){
       return supplierService.findAll();
    }
    @RequestMapping("/findBuyers")
    @ResponseBody
    public List<Employee> findBuyers(){
       return employeeService.findBuyers();
    }

    @RequestMapping("/findProducts")
    @ResponseBody
    public List<Product> findProducts(){
       return productService.findAll();
    }

}
