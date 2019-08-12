package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.query.ProductQuery;
import cn.itsource.aisell.service.IProductService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/index")
    public String index(){
        return "product/product";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(ProductQuery query){
        return new UiPage(productService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editProduct")
    public Product beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Product product = productService.findOne(id);
            //把对应的关连对象设置为null
            //把有关系的对象移除
            product.setBrand(null);
            product.setUnit(null);
            product.setTypes(null);
            return product;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Product product,HttpServletRequest req){
        return saveOrUpdate(product,req);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editProduct") Product product,HttpServletRequest req){
        return saveOrUpdate(product,req);
    }

    private ResultJson saveOrUpdate(Product product,HttpServletRequest req){
        //下面是解决上传文件为空报错的问题
        MultipartFile fileImage = null;
        //确定你是否有上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart){
            //去拿到那个文件
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(req, MultipartHttpServletRequest.class);
            fileImage = multipartRequest.getFile("fileImage");
        }
        uploadImage(product, fileImage, req);
        ResultJson resultJson = new ResultJson();
        try {
           // int i = 1/0;
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

    //图片上传功能
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmssS");
    //图片上传功能
    public void uploadImage(Product product, MultipartFile fileImage, HttpServletRequest req){
        if(fileImage!=null) {
            String webapp = req.getServletContext().getRealPath("/");
            try {
                // 上传文件命名,拷贝到webapp的位置,设置pic到product
                Date date = new Date();
                // 大小图的路径+文件名称
                String fileName = "/image/upload/" + sdf.format(date) + ".png";
                String smallFileName = "/image/upload/" + sdf.format(date) + "_small.png";
                // 大小图的在服务器上面的物理路径
                File destFile = new File(webapp, fileName);
                File smallDestFile = new File(webapp, smallFileName);

                // 生成upload目录
                File parentFile = destFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();// 自动生成upload目录
                }

                // 把上传的临时图片，复制到当前项目的webapp路径
                //FileUtils.copyFile(upload, destFile);
                FileCopyUtils.copy(fileImage.getInputStream(), new FileOutputStream(destFile));
                // 处理缩略图
                //Thumbnails.of(upload).scale(0.1F).toFile(smallDestFile);
                Thumbnails.of(fileImage.getInputStream()).scale(0.1F).toFile(smallDestFile);
                // 把大小图的相对webapp的路径设置到数据库产品表里面
                product.setPic(fileName);
                product.setSmallpic(smallFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 我们需要返回一个结构:{success:true,msg:xxxx}
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultJson delete(Long id){
        ResultJson resultJson = new ResultJson();
        try {
            productService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

}