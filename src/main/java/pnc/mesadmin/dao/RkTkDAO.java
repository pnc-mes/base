package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.RkTkInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：入库任务信息表DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public interface RkTkDAO {


    //查询入库单信息 张亮亮
    List<RkTkInfo> SelectRKTkInfo();

   /* //根据入库代码查询入库单信息 张亮亮
    List<RkTkInfo> SelectRKTkInfoByrkType(String argrkType);*/

    //根据入库代码查询入库信息
    RkTkInfo SelectRKTkInfoByrkCode(String argrkCode);

    //根据id查询入库单信息 张亮亮
    RkTkInfo SelectRKTkInfoByruid(int argruid);

    //新增入库单信息 张亮亮
    int InsertRKTkInfo(RkTkInfo argRkTkInfo);

    //更新入库单信息 张亮亮
    int  UpdateRKTkInfo(RkTkInfo argRkTkInfo);

    //根据guid查询信息 张亮亮
    RkTkInfo SelectRKTkInfoByguid(String argguid);

    //删除入库单信息 张亮亮
    int DeleteRKTkInfo(int argruid);

    //根据任务单号、类型、状态查询入库信息
    List<RkTkInfo> SelectRKTkInfoByOOS(@Param("assCode") String assCode, @Param("rkType") String rkType, @Param("exStatus") String exStatus);

    //根据关联单号查询入库单信息  (pjf)
    RkTkInfo SelectByAssCode(String argAssCode);

    List<RkTkInfo> SelectByAssCodes(String argAssCode);

    //筛选关联查询
    List<RkTkInfo>  SelectByRkShaixuan(@Param("rkType") String rkType, @Param("assCode") String assCode, @Param("execTime") String execTime, @Param("execTime1") String execTime1);
}
