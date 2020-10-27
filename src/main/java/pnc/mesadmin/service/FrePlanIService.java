package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllFrePlanInfo.GetAllFrePlanInfoRes;
import pnc.mesadmin.dto.GetAllFrePlanInfo.GetAllFrePlanInfoResD;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResD;
import pnc.mesadmin.dto.GetFrePlanInfo.GetFrePlanInfoReq00;
import pnc.mesadmin.dto.GetFrePlanInfo.GetFrePlanInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveFrePlanInfo.SaveFrePlanInfoReq00;
import pnc.mesadmin.dto.SaveFrePlanInfo.SaveFrePlanInfoReq01;
import pnc.mesadmin.dto.SaveFrePlanInfo.SaveFrePlanInfoReq02;
import pnc.mesadmin.dto.SaveFrePlanInfo.SaveFrePlanInfoRes;

import java.util.List;

/**
 * Created by HAOZAN on 2018/9/7.
 */
public interface FrePlanIService {

    //获取次数保养计划列表信息
    GetAllFrePlanInfoRes GetAllFrePlanInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取次数保养计划列表信息（新）
    PageResult<GetAllFrePlanInfoResD> QALLGetAllFrePlanNewRes(BaseRequest req);

    //获取次数保养计划信息
    GetFrePlanInfoRes GetFrePlanInfo(GetFrePlanInfoReq00 getFrePlanInfoReq00);

    //00保存次数保养计划信息
    SaveFrePlanInfoRes AddSaveFrePlanInfo(SaveFrePlanInfoReq00 saveFrePlanInfoReq00);

    //01删除次数保养计划信息
    SaveFrePlanInfoRes RmSaveFrePlanInfo(SaveFrePlanInfoReq01[] saveFrePlanInfoReq01s);

    //02修改次数保养计划信息
    SaveFrePlanInfoRes ModSaveFrePlanInfo(SaveFrePlanInfoReq02 saveFrePlanInfoReq02);

}
