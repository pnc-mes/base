package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/28 16:06
 * @Description:
 */
public interface CommonTotalNumDAO {
    int selectAllCZPInfo(@Param("lineGd") String lineGd, @Param("endTime") String endTime, @Param("startTime") String startTime);

    int selectAllYHCheckInfo(@Param("lineGd") String lineGd, @Param("endTime") String endTime, @Param("startTime") String startTime);

    int selectAllZJCheckInfo(@Param("lineGd") String lineGd, @Param("endTime") String endTime, @Param("startTime") String startTime);



    int reselectAllYHCheckInfo(@Param("lineGd") String lineGd, @Param("endTime") String endTime, @Param("startTime") String startTime);

    int reselectAllZJCheckInfo(@Param("lineGd") String lineGd, @Param("endTime") String endTime, @Param("startTime") String startTime);
}
