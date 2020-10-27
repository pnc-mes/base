package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.InStrategyInfo;

import java.util.List;

/**
 * Created by liufuzhi on 2018/1/30.
 */
public interface InStrategyDAO {
    //查询收货策略列表信息
    List<InStrategyInfo> SelectAllByInStrategyInfo();

    //查询收货策略类型
    List<InStrategyInfo> SelectAllByActOn(String actOn);

    //根据ruid查询收货策略信息
    InStrategyInfo SelectAllByRuid(int ruid);

    //根据guid查询
    InStrategyInfo SelectAllByGuid(String guid);

    //查询策略名称和作用类型
    InStrategyInfo SelectstrategyNameANDactOn(@Param("strategyName") String strategyName, @Param("actOn") String actOn);

    //查询默认和作用类型
    InStrategyInfo SelectisDefANDactOn(@Param("isDef") String isDef, @Param("actOn") String actOn);

    //新增收货策略信息
    int InsertInStrategyInfo(InStrategyInfo inStrategyInfo);

    //删除收货策略信息
    int DeleteInStrategyInfoByRuid(int ruid);

    //更新收货策略信息
    int UpdateIInStrategyInfo(InStrategyInfo inStrategyInfo);

}
