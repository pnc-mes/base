package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoRes;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoResD;
import pnc.mesadmin.dto.GetCyclePlanInfo.GetCyclePlanInfoReq00;
import pnc.mesadmin.dto.GetCyclePlanInfo.GetCyclePlanInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveCyclePlanInfo.SaveCyclePlanInfoReq00;
import pnc.mesadmin.dto.SaveCyclePlanInfo.SaveCyclePlanInfoReq01;
import pnc.mesadmin.dto.SaveCyclePlanInfo.SaveCyclePlanInfoReq02;
import pnc.mesadmin.dto.SaveCyclePlanInfo.SaveCyclePlanInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:53
 * @Description:
 */
public interface CyclePlanIService {
    //列表
    GetAllCyclePlanInfoRes QALLGetAllCyclePlanInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * 列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllCyclePlanInfoResD> QALLGetAllCyclePlanNewRes(BaseRequest req);

    //保存
    SaveCyclePlanInfoRes AddSaveCyclePlanInfoRes(SaveCyclePlanInfoReq00 saveCyclePlanInfoReq00);

    //删除
    SaveCyclePlanInfoRes RmSaveCyclePlanInfoRes(SaveCyclePlanInfoReq01 saveCyclePlanInfoReq01);

    //修改
    SaveCyclePlanInfoRes ModSaveCyclePlanInfoReq02(SaveCyclePlanInfoReq02 saveCyclePlanInfoReq02);

    //获取单个
    GetCyclePlanInfoRes GetGetCyclePlanInfoRes(GetCyclePlanInfoReq00 getCyclePlanInfoReq00);
}
