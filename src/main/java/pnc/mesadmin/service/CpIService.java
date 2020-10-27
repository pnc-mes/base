package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllCpInfo.GetAllCpInfoRes;
import pnc.mesadmin.dto.GetAllCpInfo.GetAllCpInfoResD;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoRes;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoReqBD00;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveCpInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：企业信息Service层接口
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface CpIService {

    //dto查询企业信息列表
    GetAllCpInfoRes QALLCompanyInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //dto获取企业信息
    GetCpInfoRes GetGetCpInfoRes(GetCpInfoReqBD00 argGetCpInfoRes00);

    //dto新增
    SaveCpInfoRes ADDSaveCpInfoRes(SaveCpInfoReqBD00 argSaveCpInfoRes00);

    //dto删除
    SaveCpInfoRes  RmSaveCpInfoRes(SaveCpInfoReqBD01 argSaveCpInfoRes01);

    //dto更新
    SaveCpInfoRes ModSaveCpInfoRes(SaveCpInfoReqBD02 argSaveCpInfoRes02);

    //dto复制
    SaveCpInfoRes ModSaveCpInfoRes(SaveCpInfoReqBD03 argSaveCpInfoRes03);

    /**
     * 查询企业信息列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllCpInfoResD> QALLCompanyNew(BaseRequest req);
}
