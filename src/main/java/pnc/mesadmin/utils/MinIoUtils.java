package pnc.mesadmin.utils;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pnc.mesadmin.dto.SystemException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：MinIo工具类
 * 创建人：pjf
 * 创建时间：2020-09-11
 * 修改人：
 * 修改时间：
 */
@Component
public class MinIoUtils {
    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    /**
     * 文件上传
     * @param multipartFile
     */
    public String putObject(MultipartFile multipartFile){
        LocalDateTime localDateTime = LocalDateTime.now();

        String oFileName = multipartFile.getOriginalFilename();
        //获取文件后缀
        int index = oFileName.lastIndexOf(".");
        if(index <= 0){
            index = oFileName.length();
        }
        String suffix = oFileName.substring(index);

        String filename = oFileName.substring(0, index) + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-dd-MM_hh-mm-ss")) + suffix;

        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                    .object(filename)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            new SystemException("", "文件上传失败");
        }

        return filename;
    }

    /**
     * 文件下载
     * @param filename
     * @return
     */
    public InputStream getObject(String filename){
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(filename).build());
        } catch (Exception e) {
            e.printStackTrace();
            new SystemException("", "文件下载失败");
        }
        return null;
    }

    /**
     * 获取文件url
     * @param filename
     * @return
     */
    public String getUrl(String filename){
        try {
            return minioClient.getObjectUrl(bucketName, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
