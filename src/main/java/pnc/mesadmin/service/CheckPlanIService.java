package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllCheckPlanInfo.GetAllCheckPlanInfoRes;
import pnc.mesadmin.dto.GetAllCheckPlanInfo.GetAllCheckPlanInfoResD;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetCheckPlanInfo.GetCheckPlanInfoReq00;
import pnc.mesadmin.dto.GetCheckPlanInfo.GetCheckPlanInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveCheckPlanInfo.SaveCheckPlanInfoReq00;
import pnc.mesadmin.dto.SaveCheckPlanInfo.SaveCheckPlanInfoReq01;
import pnc.mesadmin.dto.SaveCheckPlanInfo.SaveCheckPlanInfoReq02;
import pnc.mesadmin.dto.SaveCheckPlanInfo.SaveCheckPlanInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:07
 * @Description:
 */
public interface CheckPlanIService {
    //列表
    GetAllCheckPlanInfoRes QALLGetAllCheckPlanInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
    /**
     * 查询点检计划列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllCheckPlanInfoResD> QALLGetAllCheckPlanNewRes(BaseRequest req);

    //单个
    GetCheckPlanInfoRes GetGetCheckPlanInfoRes(GetCheckPlanInfoReq00 getCheckPlanInfoReq00);

    //保存
    SaveCheckPlanInfoRes AddSaveCheckPlanInfoRes(SaveCheckPlanInfoReq00 saveCheckPlanInfoReq00);

    //删除
    SaveCheckPlanInfoRes RmSaveCheckPlanInfoRes(SaveCheckPlanInfoReq01 saveCheckPlanInfoReq01);

    //修改
    SaveCheckPlanInfoRes ModSaveCheckPlanInfoRes(SaveCheckPlanInfoReq02 saveCheckPlanInfoReq02);
}
