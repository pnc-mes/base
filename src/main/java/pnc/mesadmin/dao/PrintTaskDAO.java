package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PrintTaskInfo;
import pnc.mesadmin.dto.newmove.GetAllRVInfoDto.GetAllRVInfoRes;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印任务信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-22
 * 修改人：
 * 修改时间：
 */
public interface PrintTaskDAO extends BaseMapper<PrintTaskInfo> {

    /**
     * 新增打印任务信息 (By-pjf)
     * @param argPrintTaskInfo
     * @return
     */
    int InsertPrintTaskInfo(PrintTaskInfo argPrintTaskInfo);

    /**
     * 修改打印任务信息  (By-pjf)
     * @param argPrintTaskInfo
     * @return
     */
    int UpdatePrintTaskInfo(PrintTaskInfo argPrintTaskInfo);

    /**
     * 查询所有打印任务信息  (By-pjf)
     * @return
     */
    List<PrintTaskInfo> SelectAllPrintTaskInfo();

    /**
     * 根据PTCode查询打印任务信息 (By-pjf)
     * @param argPTCode
     * @return
     */
    PrintTaskInfo SelectByPTCode(String argPTCode);

    /**
     * 根据ReCode查询打印任务信息 (By-pjf)
     * @param argReCode
     * @return
     */
    List<PrintTaskInfo> SelectByReCode(@Param("argReCode") String argReCode,
                                       @Param("argOrderType") String argOrderType);

    /**
     * 根据BarCode、BarType查询打印任务信息 (By-pjf)
     * @param argBarCode
     * @param argBarType
     * @return
     */
    PrintTaskInfo SelectByBarCodeType(@Param("argBarCode") String argBarCode,
                                      @Param("argPrintTGd") String argPrintTGd,
                                      @Param("argBarType") String argBarType);

    List<PrintTaskInfo> SelectByObj(GetAllRVInfoRes res);
}
