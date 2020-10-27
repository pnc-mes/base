package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MinTimeWindowInfo;

import java.util.List;

/**
 * Created by 乔帅阳 2018/7/30.
 */
public interface MinTimeWindowDao {
    //查询最小时间管控列表信息 乔帅阳
    List<MinTimeWindowInfo> SelectMinTimeWindowInfo();
    //查询最小时间管控信息 乔帅阳
    MinTimeWindowInfo SelectMinTimeWindowInfoByruid(int ruid);
    //根据名称查询最小时间管控信息 乔帅阳
    MinTimeWindowInfo SelectMinTimeWindowInfoByName(String MinTimeWindowName);
    //新增最小时间管控信息 乔帅阳
    int InsertMinTimeWindowInfo(MinTimeWindowInfo argMinTimeWindowInfo);
    //删除最小时间管控信息 乔帅阳
    int DeleteMinTimeWindowInfo(int argruid);
    //更新最小时间管控信息 乔帅阳
    int UpdateMinTimeWindowInfo(MinTimeWindowInfo minTimeWindowInfo);

    //根据物料、工艺流程、工序查询
    MinTimeWindowInfo SelectByMaWfSpecGd(@Param("maVerGd") String maVerGd,
                                         @Param("wFVerGd") String wFVerGd,
                                         @Param("specVerGd") String specVerGd,
                                         @Param("devGd") String devGd);

}
