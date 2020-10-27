package pnc.mesadmin.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.csource.fastdfs.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import pnc.mesadmin.dto.BaseInitField;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by PNC on 2017/5/10.
 */
public class CommonUtils {

    @Resource
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient1 storageClient1;
    private static Properties props;
    private static Properties etProps;

    static {
        System.out.println("---");

        try {
            //读取配置文件
            InputStream in = CommonUtils.class.getClassLoader().getResourceAsStream("fastdfs.properties");
            props = new Properties();
            props.load(in);
            /*props.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, "80");
            props.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, "no");
            props.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, "FastDFS1234567890");
            props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "10.4.1.62:22122");*/

            ClientGlobal.initByProperties(props);

            //读取配置文件
            InputStream etIn = CommonUtils.class.getClassLoader().getResourceAsStream("et.properties");
            etProps = new Properties();
            etProps.load(etIn);

            ClientGlobal.initByProperties(etProps);

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

    //时间转换成字符串
    public static String getFormat(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str = sim.format(date);
        return str;
    }

    //时间转换成字符串(yyyy-MM-dd HH:mm:ss)
    public static String getFormatTwo(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sim.format(date);
        return str;
    }

    public static String getRandomNumber() {
        String orderNo = "";
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMM").format(new Date());
        orderNo = trandNo.toString().substring(0, 7);
        orderNo = orderNo + sdf;
        return orderNo;
    }

    public static Map<String, Object> transforToMap(List<Map<String, Object>> list,
                                                    String key, String property) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        for (Map<String, Object> map : list) {
            map1.put(map.get(key).toString(), map.get(property));
        }
        return map1;
    }

    //json转换dto
    public static <T> T switchClass(Class<T> clazz, String json) {
        Object o = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            o = objectMapper.readValue(json, clazz);
        } catch (IOException er) {
            System.out.println("JSON-ERROR:" + er.getMessage());
        }
        return (T) o;
    }

    //dto转换json
    public static String switchJson(Object object) {
        String result = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(object);
        } catch (IOException er) {
            System.out.println("JSON-ERROR:" + er.getMessage());
        }
        return result;
    }

    public static String ArrayListToString(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        boolean isFirst = true;
        StringBuffer result = new StringBuffer();
        for (String s : list) {
            if (isFirst) {
                isFirst = false;
            } else {
                result.append(",");
            }
            result.append(s);
        }
        return result.toString();
    }

    public static InputStream getFile(String urlPath) {
        InputStream inputStream = null;
        try {
            try {
                String strUrl = urlPath.trim();
                URL url = new URL(strUrl);
                URLConnection connection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                inputStream = httpURLConnection.getInputStream();
                return inputStream;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                inputStream = null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputStream = null;
        }
        return inputStream;
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static UserInfo readUser() {
        UserInfo userInfo = new UserInfo();
        //if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setRealName("sys");
            userInfo.setUserName("admin");
        //}
        return userInfo;
    }

    //读取对象的属性值
    public static String getProperty(Object object, String name) {

        if (object != null) {

            Class objectClass = object.getClass();

            try {
                Field fs = objectClass.getDeclaredField(name);

                fs.setAccessible(true);

                Object val = fs.get(object);

                System.out.println("name:" + fs.getName());
                System.out.println("value:" + val);

                String value = "";

                if (fs.getGenericType().equals(Date.class)) {
                    value = DateUtil.format((Date) fs.get(object));
                } else {
                    value = String.valueOf(val);
                }

                return value;

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }
        }

        return "";
    }

    //任务单号
    public static String createPTCode(int id) {

        //获取左边设置为0
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(10);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(10);
        //获取当前的年月日字符串
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] str = format.format(new Date()).substring(0, 10).split("-");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
        }

        return sb.toString() + nf.format(id);
    }

    //上传
    public static Map<String, String> uploadFile(HttpServletRequest request, String name) throws IOException {

        Map<String, String> map = new HashMap<String, String>();

        //读取配置文件
        InputStream in = CommonUtils.class.getClassLoader().getResourceAsStream("config.properties");

        Properties props = new Properties();

        props.load(in);

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

                    String suffix = fileName.substring(fileName.lastIndexOf("."));

                    if (!".btw".equals(suffix) && !".pdf".equals(suffix)) {
                        throw new SystemException("", "上传文件不符合");
                    }

                    fileName = CommonUtils.getRandomNumber() + suffix;

                    String path = request.getSession().getServletContext().getRealPath("/") + props.get("upload_url") + fileName;

                    File file1 = new File(path);
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }

                    if (!"".equals(name)) {
                        File file2 = new File(request.getSession().getServletContext().getRealPath("/") + props.get("upload_url") + name);
                        if (file2.exists() && file2.isFile()) {
                            file2.delete();
                        }
                    }

                    //上传
                    file.transferTo(file1);

                    map.put(file.getOriginalFilename(), "http://" + request.getServerName() + ":"
                            + request.getServerPort() + request.getContextPath() + "/"
                            + props.get("upload_url") + fileName);
                }
            }
        }

        return map;
    }


    //判断上传文件是否存在
    public static boolean IsUpFile(/*HttpServletRequest request, */String url) {

        boolean isUpFile = true;

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (Exception e) {
            isUpFile = false;
        }// 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        /*//读取配置文件
        InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");

        Properties props = new Properties();

        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!"".equals(name)) {
            File file2 = new File(request.getSession().getServletContext().getRealPath("/") + props.get("upload_url") + name);
            if (file2.exists() && file2.isFile()) {
                return true;
            }
        }*/

        return isUpFile;
    }


    /**
     * 判断Excel空行
     *
     * @return :
     * boolean TRUE:为空，FALSE:不为空
     * @Param :
     * row 行对象
     * cellLength 列数
     */
    public static Boolean EXCEL_ROW_IsEmpty(Row row, int cellLength) {
        int emptyCount = 0;
        for (int k = 0; k < cellLength; k++) {
            Cell cell = row.getCell(k);
            if (cell == null || "".equals(cell.toString().trim())) {
                emptyCount++;
                continue;
            }
        }
        if (emptyCount == cellLength) {
            return true;
        } else {
            return false;
        }
    }


    public static void remoteJsonRequest(String url, int timeout, String content) throws IOException {
        URL postUrl = new URL(etProps.get("et.url") + url);
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setReadTimeout(timeout);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(content);
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        connection.disconnect();
    }

    public static String remoteJsonRequestToStationApi(String url, int timeout, String content) throws IOException {
        URL postUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setReadTimeout(timeout);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(content);
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String response = reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        connection.disconnect();
        return response;
    }

    public static boolean hourMinuteBetween(String nowDate, String startDate, String endDate) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date now = format.parse(nowDate);
        Date start = format.parse(startDate);
        Date end = format.parse(endDate);

        long nowTime = now.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();

        return nowTime >= startTime && nowTime <= endTime;
    }

    public static boolean MinuteBetween(String nowDate, String startDate) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date now = format.parse(nowDate);
        Date start = format.parse(startDate);


        long nowTime = now.getTime();
        long startTime = start.getTime();


        return nowTime >= startTime;
    }

    public static boolean isEffectiveDate(Date startTime, Date endTime) {


        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (end.after(begin)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * MybatisPlus通用查询条件拼接
     * @param fields
     * @param <T>
     * @return
     */
    public static<T> QueryWrapper getQueryWrapper(List<BaseInitField> fields){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        if(fields == null){
            return queryWrapper;
        }

        String[] ins = null;

        for(BaseInitField field : fields){
            switch (field.getFieldOpt().toLowerCase()){
                case "=":
                    queryWrapper.eq(field.getFieldName(), field.getFieldVal());
                    break;
                case "!=":
                    queryWrapper.ne(field.getFieldName(), field.getFieldVal());
                    break;
                case "in":
                    ins = field.getFieldVal().split(",");
                    queryWrapper.in(field.getFieldName(), ins);
                    break;
                case "like":
                    queryWrapper.like(field.getFieldName(), field.getFieldVal());
                    break;
                case "between":
                    ins = field.getFieldVal().split(",");
                    if(ins.length != 2){
                        break;
                    }
                    queryWrapper.between(field.getFieldName(), ins[0], ins[1]);
                    break;
                case "order by":
                    if("desc".equals(field.getFieldVal().toLowerCase())){
                        queryWrapper.orderByDesc(field.getFieldName());
                    }else{
                        queryWrapper.orderByAsc(field.getFieldName());
                    }
                    break;
                default:
                    break;
            }
        }

        return queryWrapper;
    }

    //获取当前下载Url
    public static String getUrl(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/File/download/";
    }
}
