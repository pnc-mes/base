package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import pnc.mesadmin.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by PNC on 2017/6/3.
 */
@Controller
@Scope("prototype")
@RequestMapping("/file")
public class FileUploadController {
    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping(value="/springUpload",method= RequestMethod.POST)
    @ResponseBody
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException {

        /*String fileName = "";

        //读取配置文件
        InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");

        Properties props = new Properties();

        props.load(in);

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());

        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;

            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null) {

                    fileName = file.getOriginalFilename();
                    fileName = CommonUtils.getRandomNumber() + fileName.substring(fileName.lastIndexOf("."));

                    String path = request.getSession().getServletContext().getRealPath("/") + props.get("upload_url") + fileName;

                    File file1 = new File(path);
                    if(!file1.exists()){
                        file1.mkdirs();
                    }

                    //上传
                    file.transferTo(file1);
                }
            }
        }

        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/"
                + props.get("upload_url") + "/" + fileName;
*/
        return "";
    }
}
