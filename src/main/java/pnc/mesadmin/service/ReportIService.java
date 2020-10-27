package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllKCRecInfo.GetAllKCRecInfoRes;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoRes;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoResD1;
import pnc.mesadmin.dto.GetAllProdInfo.GetAllProdInfoResB;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSWareInfoRes;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSum;
import pnc.mesadmin.dto.GetAllWIPInfo.GetAllWIPInfoResB;
import pnc.mesadmin.dto.GetOTInfo.GetOTInfoReqBD00;
import pnc.mesadmin.dto.GetOTInfo.GetOTInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线追踪Service
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
public interface ReportIService {
    //在线追踪
    GetOTInfoRes GetGetOTInfoRes(GetOTInfoReqBD00 argGetOTInfoReqBD00);

    //库存预警报表
    GetAllSWareInfoRes QALLGetAllSWareInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //导出库存预警
    ByteArrayOutputStream ExportSWareInfo(GetAllSum argGetAllSum, List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //汇总库存预警
    GetAllSWareInfoRes GetAllSum(GetAllSum argGetAllSum, List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //库存变动报表
    GetAllKCRecInfoRes QALLGetAllKCRecInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //导出库存变动
    ByteArrayOutputStream ExportGetAllKCRecInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //产线余料信息
    GetAllLYMaInfoRes QALLGetAllLYMaInfoRes(GetAllLYMaInfoResD1 argGetAllLYMaInfoResD1) throws SystemException;

    //导出产线余料
    ByteArrayOutputStream ExportYMaInfo(GetAllLYMaInfoResD1 argGetAllLYMaInfoResD1);

    //在制品报表
    GetAllWIPInfoResB QALLWIPInfo(List<InitDataField> initDataFieldList, PageInfo pageInfo);

    //生产日报表
    GetAllProdInfoResB QALLProdRepInfo(List<InitDataField> initDataFieldList, PageInfo pageInfo);
}
