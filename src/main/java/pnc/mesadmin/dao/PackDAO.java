package pnc.mesadmin.dao;


import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PackInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打包日志信息表DAO
 * 创建人：张亮亮
 * 创建时间：2017-6-21
 * 修改人：
 * 修改时间：
 */
public interface PackDAO {

    //新增打包日志 (By-pjf)
    int InsertPackInfo(PackInfo argPackInfo);

    /**
     * 修改打包日志->只更新pCode  (By-pjf)
     */
    int UpdatePackInfo(PackInfo argPackInfo);

    /**
     * 修改包装日志表(只可以修改数量，重量，修改人，修改时间，备注)
     */
    int UpdatePack(PackInfo argPackInfo);


    int updatePackInfoIsReworkAndRuid(@Param("isRework") String isRework, @Param("ruid") int ruid);
    /**
     *  更新状态
     * @param argPackInfo
     * @return
     */
    int UpdatePackInfoStauts(PackInfo argPackInfo);

    //查询打包日志信息 张亮亮
    List<PackInfo> SelectPackInfo();

    //根据打包单号查询打包日志 (By-pjf)
    PackInfo SelectByBarCode(String argBarCode);

    List<PackInfo> SelectListByBarCode(String barCode);

    int Delete(String argGuid);

    PackInfo SelectByGuid(String argGuid);

    //获取批次号（存储过程）
    Map<String, String> SelectByList(@Param("scriptName") String scriptName,
                                     @Param("serialRuleGd") String serialRuleGd,
                                     @Param("date") Date date);

    PackInfo SelectPackInfoByPrintTGd(String printTGd);
}
