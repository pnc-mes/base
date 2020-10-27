package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetAllWCInfo.GetAllWCInfoRes;
import pnc.mesadmin.dto.GetAllWCInfo.GetAllWCInfoResD;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoReqBD00;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveWCInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工作中心Service
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
public interface WorkCIService {
    //dto 获取工作中心列表
    GetAllWCInfoRes QALLGetAllWCInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * dto 获取工序列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllWCInfoResD> QALLGetAllWCInfoRes(BaseRequest req);


    //dtom获取工作中心信息
    GetWCInfoRes GetGetWCInfoRes(GetWCInfoReqBD00 argGetWCInfoReqBD00);

    //dto新增工作中心说
    SaveWCInfoRes AddSaveWCInfoRes(SaveWCInfoReqBD00 argSaveWCInfoReqBD00);

    //dto删除工作中心
    SaveWCInfoRes RmSaveWCInfoRes(SaveWCInfoReqBD01 argSaveWCInfoReqBD01);

    //dto更新工作中心
    SaveWCInfoRes ModSaveWCInfoRes(SaveWCInfoReqBD02 argSaveWCInfoReqBD02);

    //dto复制工作中心
    SaveWCInfoRes AddSaveWCInfoRes(SaveWCInfoReqBD03 argSaveWCInfoReqBD03);
}
