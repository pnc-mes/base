package pnc.mesadmin.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import pnc.mesadmin.dto.SystemException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by xfxi on 2017/9/2.1
 */
public class FastfdsUtils {

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient1 storageClient1;
    private static Properties props;

    static {
        System.out.println("---");

        try {
            //读取配置文件
            InputStream in = FastfdsUtils.class.getClassLoader().getResourceAsStream("fastdfs.properties");
            props = new Properties();
            props.load(in);
            /*props.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, "80");
            props.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, "no");
            props.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, "FastDFS1234567890");
            props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "10.4.1.62:22122");*/

            ClientGlobal.initByProperties(props);

           /* trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);*/
            TrackerGroup trackerGroup = ClientGlobal.g_tracker_group;

            trackerClient = new TrackerClient(trackerGroup);

            //trackerServer = trackerClient.getTrackerServer();

            storageServer = trackerClient.getStoreStorage(trackerServer);

            storageClient1 = new StorageClient1(trackerServer, storageServer);

        } catch (Exception e) {
            System.out.println("---");
            e.printStackTrace();
        }
    }

    //文件上传
    public static Map<String, String> upload(HttpServletRequest request, String suffix_) throws SystemException {

        System.out.println("---");

        Map<String, String> map = new HashMap<String, String>();

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {

                    String fileName = "";

                    fileName = file.getOriginalFilename();

                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);


                    String[] suffixs = suffix_.split(",");
                    boolean f = true;
                    for (String s : suffixs) {
                        if (s.equals(suffix)) {
                            f = false;
                        }
                    }
                    if (f) {
                        throw new SystemException("EEEE", "上传文件不符合");
                    }

                    //fileName = CommonUtils.getRandomNumber() + suffix;

                    //设置元信息
                    NameValuePair[] metaList = new NameValuePair[3];
                    metaList[0] = new NameValuePair("fileName", fileName);
                    metaList[1] = new NameValuePair("fileExtName", suffix);
                    metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));

                    try {
                        String[] ss = storageClient1.upload_file(file.getBytes(), suffix, metaList);

                        map.put(fileName, ss[0] + "/" + ss[1]);
                    } catch (Exception e) {
                        throw new SystemException("", fileName + "文件上传失败");
                    }
                }
            }
        }

        return map;
    }

    //文件下载
    public static InputStream download(String groupName, String fileId) {

        return null;
    }

    //文件删除
    public static void delete(String fileId) {
        try {
            int result = storageClient1.delete_file1(fileId);
        } catch (IOException e) {
            //throw new SystemException("", "文件删除失败");
        } catch (MyException e) {
            //throw new SystemException("", "文件删除失败");
        }
    }

    //更新文件
    public static Map<String, String> modify(HttpServletRequest request, Set<String> oldNames, String suffix) {

        Map<String, String> map = upload(request, suffix);

        for (String s : oldNames) {
            delete(s);
        }

        return map;
    }

    //读取配置
    public static String readProps() {

        return (String) props.get("fastdfs.url");
    }

    //判断文件存不存在（暂时）
    public static boolean isFile(String fileId) {

        try {
            storageClient1.get_file_info1(fileId);
        } catch (IOException e) {
            return false;
        } catch (MyException e) {
            return false;
        }

        return true;
    }
}
