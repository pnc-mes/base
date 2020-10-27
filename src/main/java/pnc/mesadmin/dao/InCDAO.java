package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.InCInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public interface InCDAO {
    //查询所有的来料单
    List<InCInfo> SelectInCInfo();

    //新增不允许重复
    int SelectInCInfoByInCCode(String argInCCode);

    //新增来料单
    int InsertInCInfo(InCInfo argInCInfo);

    //根据ID修改来料单信息
    int  UpdateInCInfoByRuid(InCInfo argInCInfo);

   //根据来料单号查询来料单信息  王怀龙
    InCInfo SelectByInCCode(String inCCode);


    //根据Ruid查询来料单信息  (pjf)
    InCInfo SelectByRuid(int argRuid);

    /**
     * 根据Guid查询来料单信息 (pjf)
     * @param argGuid
     * @return
     */
    InCInfo SelectByGuid(String argGuid);

    //删除来料单信息
    int DeleteInCInfo(int argRuid);

    /**
     * 根据来料单号+数据来源查询
     * @param argInCCode
     * @param argDSource
     * @return
     */
    InCInfo SelectByInCCodeDSource(@Param("argInCCode") String argInCCode,
                                   @Param("argDSource") String argDSource);

    /**
     * 根据F3查询是否存在
     * @param argF3
     * @return
     */
    InCInfo SelectByF3(String argF3);
}
