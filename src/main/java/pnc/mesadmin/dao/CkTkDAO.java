package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.CkTkInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：出库单Dao层接口
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public interface CkTkDAO {

    //根据出库单号查询出库单信息 --王怀龙
    CkTkInfo SelectCkTkInfoByCkCode(String argCkCode);

    //根据出库单号查询出库单信息 --zll
    CkTkInfo SelectCkTkInfoByAssCode(String argCkCode);

    CkTkInfo SelectCkTkInfoByAssCodeAndAssSource(@Param("assCode") String argCkCode, @Param("assSource") String argAssSource);

    //新增领料单信息
    int InsertCkTkInfo(CkTkInfo argCkTkInfo);

    //查询出库信息
    CkTkInfo SelectCkTkInfoByGuid(String argGuid);

    //更新
    int UpdateCkTkInfo(CkTkInfo argCkTkInfo);

    //根据任务单号和状态查询任务信息  王怀龙
    List<CkTkInfo> SelectCkTkInfoByOOS(@Param("assCode") String assCode, @Param("ckType") String ckType, @Param("exStatus") String exStatus);

   /* //根据类型查询信息
    List<CkTkInfo> SelectCkTkInfoByCkType(String argCkType);*/

    //根据ruid查询出库单信息
    CkTkInfo SelectCkTkInfoByRuid(int argruid);

    //删除出库单信息
    int DeleteCkTkInfoByRuid(int argruid);

    //筛选关联查询
    List<CkTkInfo>   SelectByCkShaixuan(@Param("ckType") String ckType, @Param("assCode") String assCode, @Param("execTime") String execTime, @Param("execTime1") String execTime1);

    //出库任务单查询
    List<CkTkInfo> SelectCkTkInfoByCkCodeAndCkTypeAndExStatus(@Param("AssCode") String AssCode,
                                                              @Param("CkType") String CkType,
                                                              @Param("ExStatus") String ExStatus);

}
