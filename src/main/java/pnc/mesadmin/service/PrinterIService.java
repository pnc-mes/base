package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoRes;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoResD;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoRes;
import pnc.mesadmin.dto.SavePrInfo.SavePrInfoReqBD00;
import pnc.mesadmin.dto.SavePrInfo.SavePrInfoReqBD02;
import pnc.mesadmin.dto.SavePrInfo.SavePrInfoReqBD03;
import pnc.mesadmin.dto.SavePrInfo.SavePrInfoRes;
import pnc.mesadmin.entity.PrinterInfo;

import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public interface PrinterIService {
    //查询打印机列表信息
    GetAllPrInfoRes QALLselectAllPrinterInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //查询打印机信息
    GetPrInfoRes GetselectByRuid(int prRd) throws SystemException;

    //保存打印机信息
    SavePrInfoRes AddinsertPrinterInfo(SavePrInfoReqBD00 busData00, PrinterInfo printerInfo) throws SystemException;

    //更新打印机信息
    SavePrInfoRes ModupdatePrinterInfo(SavePrInfoReqBD02 busData02, PrinterInfo printerInfo) throws SystemException;

    //复制打印机信息
    SavePrInfoRes copyPrinterInfo(SavePrInfoReqBD03 busData03, PrinterInfo printerInfo) throws SystemException;

    //删除打印机信息
    SavePrInfoRes RmdeletePrinterInfo(int ruid) throws SystemException;

    /**
     * 查询打印机列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllPrInfoResD> QALLPrinterNew(BaseRequest req);
}
