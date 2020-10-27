package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllHPLInfo.GetAllHPLInfoResD;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveRePrInfo.SaveRePrInfoReqBD00;
import pnc.mesadmin.dto.SaveRePrInfo.SaveRePrInfoReqBD01;
import pnc.mesadmin.dto.SaveRePrInfo.SaveRePrInfoReqBD02;
import pnc.mesadmin.entity.PrintTaskInfo;

import java.util.Map;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印记录查询业务逻辑
 * 创建人：pjf
 * 创建时间：2020-10-12
 * 修改人：
 * 修改时间：
 */
public interface HisPrintLogIService {

    /**
     * 获取打印记录
     * @param request
     * @return
     */
    PageResult<GetAllHPLInfoResD> QALLPrintTaskInfo(BaseRequest request);

    /**
     * 重打
     * @param argBD00
     */
    void AddRePrintLogInfo(SaveRePrInfoReqBD00 argBD00);

    /**
     * 单据打印
     * @param argBD01
     */
    void AddRePr(SaveRePrInfoReqBD01 argBD01);

    /**
     * 条码打印
     * @param argBD02
     */
    void AddBarCode(SaveRePrInfoReqBD02 argBD02);


    /**
     * 打印任务信息
     * @param argPrintTaskInfo
     * @param argPrinterSer
     * @param argIsPrint
     */
    void PrintTQueue(PrintTaskInfo argPrintTaskInfo, String argPrinterSer, String argIsPrint);

    /**
     * 获取打印参数数据
     * @param map
     * @param printTGd
     * @return
     */
    String PrintData(Map<String, Object> map, String printTGd);
}
