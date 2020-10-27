package pnc.mesadmin.service;

import org.apache.ibatis.annotations.Param;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/28 16:03
 * @Description:
 */
public interface CommonTotalNumIService {
    int selectAllCZPInfo(String lineGd, String endtime, String starttime);

    int selectAllYHCheckInfo(String lineGd, String endTime, String startTime);

    int selectAllZJCheckInfo(String lineGd, String endTime, String startTime);


    int reselectAllYHCheckInfo(String lineGd, String endTime, String startTime);

    int reselectAllZJCheckInfo(String lineGd, String endTime, String startTime);
}
