package pnc.mesadmin.utils;
import org.apache.commons.lang.StringUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {
    private static String defaultDatePattern1 = "yyyy-MM-dd HH:mm:ss";
    private static String defaultDatePattern2 = "yyyy-MM-dd";
    private static String defaultDatePattern3 = "yyyy/MM/dd";
    private static String defaultDatePattern4="HH:mm";
    /**
     * 获得默认的 date pattern
     */
   /* private static String getDatePattern()
    {
        return defaultDatePattern1;
    }*/

    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String format(Date date)
    {
        return date == null ? " " : format(date, defaultDatePattern1);
    }




    public static String formatPattern2(Date date)
    {
        return date == null ? " " : format(date, defaultDatePattern2);
    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern)
    {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date format(String str) {

        Date date = null;
        try {
            if(str!=null) {
                date = new SimpleDateFormat(defaultDatePattern1).parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date format2(String str) {
        Date date = null;
        if(StringUtils.isBlank(str)){
            return null;
        }
        try {
            date = new SimpleDateFormat(defaultDatePattern2).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Date format3(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat(defaultDatePattern3).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算过期时间
     * @param productDate 原始日期
     * @param interval  日期数量
     * @param sUnit   日期单位--/年/月/周/日
     * @return
     */
    public static Date getExpireDate(Date productDate, int interval, String sUnit) {

        Calendar lastTime = Calendar.getInstance();
        lastTime.setTime(productDate);
        if(sUnit.equals("00")){  //年
            lastTime.add(Calendar.YEAR,interval);//日期加年
        }else if(sUnit.equals("01")){ //月
            lastTime.add(Calendar.MONTH,interval);//日期加月
        }else if(sUnit.equals("02")){ //周
            lastTime.add(Calendar.DAY_OF_YEAR,7*interval);//日期加周
        }else if(sUnit.equals("03")){//天
            lastTime.add(Calendar.DAY_OF_YEAR,interval);//日期加日
        }

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(defaultDatePattern1);
        Date ExpireDate =lastTime.getTime();
       /* simpleDateFormat.format(lastDate);*/
        return ExpireDate;
    }

    /**
     *  比较两个日期的大小
     * @param tDate1
     * @param tDate2
     * @return
     */
   public static  boolean compareTwoDate(Date tDate1,Date tDate2){
        if(tDate1.getTime() > tDate2.getTime()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 将String类型转换为Time
     * @param str
     * @return
     */
   public static Time getTime(String str){
           Time time=null;
       try {
           SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern4);
           time=new Time(sdf.parse(str).getTime());
       }catch (ParseException e){
           e.printStackTrace();
       }
       return time;
   }

    /**
     * 比较年月日前后
     * @param
     * @return int
     */
    public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }
}
