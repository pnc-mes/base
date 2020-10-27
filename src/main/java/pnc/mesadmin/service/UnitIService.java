package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoRes;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResD;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoReqBD00;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveUnitInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：计量单位Service
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
public interface UnitIService {
    //dto查询计量单位列表
    GetAllUnitInfoRes QALLGetAllUnitInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //dto查询计量单位列表
    PageResult<GetAllUnitInfoResD> QALLGetAllUnitNewRes(BaseRequest req);

    //dto查询计量单位
    GetUnitInfoRes GetGetUnitInfoRes(GetUnitInfoReqBD00 argGetUnitInfoReqBD00);

    //dto新增计量单位
    SaveUnitInfoRes AddSaveUnitInfoRes(SaveUnitInfoReqBD00 argSaveUnitInfoReqBD00);

    //dto删除计量单位
    SaveUnitInfoRes RmSaveUnitInfoRes(SaveUnitInfoReqBD01 argSaveUnitInfoReqBD01);

    //dto更新计量单位
    SaveUnitInfoRes ModSaveUnitInfoRes(SaveUnitInfoReqBD02 argSaveUnitInfoReqBD02);

    //dto复制计量单位
    SaveUnitInfoRes AddSaveUnitInfoRes(SaveUnitInfoReqBD03 argSaveUnitInfoReqBD03);

    /**
     * 查询计量单位列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllUnitInfoResD> QALLUnitNew(BaseRequest req);
}
