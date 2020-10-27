package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.OnLineMaPeriodInfo;

import java.util.List;

/**
 * Created by haozan on 2018/9/6.
 * 在线物料有效期信息表DAO
 */
public interface OnLineMaPeriodDAO {
    //查询在线物料有效期列表
    List<OnLineMaPeriodInfo> selectAllMaPeriod();

    //根据id查询在线物料有效期
    OnLineMaPeriodInfo selectMaPeriodByRuid(int ruid);

    //根据Guid查询在线物料有效期
    OnLineMaPeriodInfo selectMaPeriodByGuid(@Param("Guid") String Guid);


    //新增在线物料有效期
    int insertMaPeriod(OnLineMaPeriodInfo onLineMaPeriodInfo);

    //根据在线物料有效期名字查询一条信息
    OnLineMaPeriodInfo selectAllMaPeriodName(String maxTimeWindowName);

    //
    OnLineMaPeriodInfo selectMaPeriod(@Param("maVerGd") String maVerGd, @Param("wFVerGd") String wFVerGd,
                                      @Param("startSpecVerGd") String startSpecVerGd, @Param("endSpecVerGd") String endSpecVerGd);
    //删除在线物料有效期
    int deleteMaPeriod(int ruid);

    //更新在线物料有效期
    int updateMaPeriod(OnLineMaPeriodInfo onLineMaPeriodInfo);

}
