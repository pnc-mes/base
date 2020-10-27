package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllReaInfo.GetAllReaInfoRes;
import pnc.mesadmin.dto.GetAllReaInfo.GetAllReaInfoResD;
import pnc.mesadmin.dto.GetReaInfo.GetReaInfoReqBD00;
import pnc.mesadmin.dto.GetReaInfo.GetReaInfoRes;
import pnc.mesadmin.dto.GetReaTypeInfo.GetReaTypeInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveReaInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码Service
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
public interface ReasonIService {
    //dto获取原因类别信息
    GetReaTypeInfoRes QALLGetReaTypeInfoRes();

    //dto获取原因代码列表信息
    GetAllReaInfoRes QALLGetAllReaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //dto查询代码原因信息
    GetReaInfoRes GetGetReaInfoRes(GetReaInfoReqBD00 argGetReaInfoReqBD00);

    //dto新增原因代码信息
    SaveReaInfoRes AddSaveReaInfoRes(SaveReaInfoReq00 argSaveReaInfoReq00);

    //dto删除代码原因
    SaveReaInfoRes RmSaveReaInfoRes(SaveReaInfoReq01 argSaveReaInfoReq01);

    //dto编辑代码原因
    SaveReaInfoRes ModSaveReaInfoRes(SaveReaInfoReq02 argSaveReaInfoReq02);

    //dto复制代码原因
    SaveReaInfoRes AddSaveReaInfoRes(SaveReaInfoReq03 argSaveReaInfoReq03);

    /**
     * 获取原因代码列表(新)
     * @param request
     * @return
     */
    PageResult<GetAllReaInfoResD> QALLReaInfoNew(BaseRequest request);
}
