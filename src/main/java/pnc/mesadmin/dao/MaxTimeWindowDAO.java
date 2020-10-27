package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MaxTimeWindowInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:22
 * @Description:
 */
public interface MaxTimeWindowDAO {
    //查询最大时间列表
    List<MaxTimeWindowInfo> selectAllMaxTimeWindowInfo();

    //根据id查询最大时间信息
    MaxTimeWindowInfo selectMaxTimeWindowInfoByRuid(int ruid);

    //新增最大时间
    void insertMaxTimeWindowInfo(MaxTimeWindowInfo maxTimeWindowInfo);

    //根据最大时间名字查询一条信息
    MaxTimeWindowInfo selectAllMaxTimeWindowInfoByMaxTimeWindowName(String maxTimeWindowName);

    //
    MaxTimeWindowInfo selectFourMaxTimeWindowInfo(@Param("maVerGd") String maVerGd, @Param("wFVerGd") String wFVerGd,
                                                  @Param("startSpecVerGd") String startSpecVerGd, @Param("endSpecVerGd") String endSpecVerGd);
    //删除最大信息
    int deleteMaxTimeWindowInfo(int ruid);

    //更新最大信息
    int updateMaxTimeWindowInfo(MaxTimeWindowInfo maxTimeWindowInfo);
}
