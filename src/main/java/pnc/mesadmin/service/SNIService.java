package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllSNInfo.GetAllSNInfoRes;
import pnc.mesadmin.dto.GetAllSNInfo.GetAllSNInfoResD;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoReqBD00;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveSNInfo.*;
import pnc.mesadmin.entity.SerialRuleInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：序号管理Service层
 * 创建人：张亮亮
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
public interface SNIService {
    //dto查询序号管理列表信息
    GetAllSNInfoRes QALLGetAllSNInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //dto查询序号管理信息
    GetSNInfoRes GetGetSNInfoRes(GetSNInfoReqBD00 argGetSNInfoReqBD00);

    //dto保存序号管理信息
    SaveSNInfoRes AddSaveSNInfoRes(SaveSNInfoReqBD00 argSaveSNInfoReqBD00);

    //dto删除序号管理信息
    SaveSNInfoRes RmSaveSNInfoRes(SaveSNInfoReqBD01 argSaveSNInfoReqBD01);

    //dto更新序号管理信息
    SaveSNInfoRes ModSaveSNInfoRes(SaveSNInfoReqBD02 argSaveSNInfoReqBD02);

    //dto复制序号管理信息
    SaveSNInfoRes AddSaveSNInfoRess(SaveSNInfoReqBD03 argSaveSNInfoReqBD03);

    //生成序号
    String GetCreateSR(SerialRuleInfo serialRuleInfo);

    /**
     * 查询序号规则列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllSNInfoResD> QALLSNNew(BaseRequest req);
}
