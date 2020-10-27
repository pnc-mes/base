package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoRes;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetSGInfo.GetSGInfoReq00;
import pnc.mesadmin.dto.GetSGInfo.GetSGInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveSGInfo.*;

import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
public interface SkillGIService {
    //查询技能组列表信息
    GetAllSGInfoRes QALLGetAllSGInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
    /**
     * 查询技能组列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllSGInfoResD> QALLGetAllSGNew(BaseRequest req);

    //查询技能组信息
    GetSGInfoRes GetGetSGInfoRes(GetSGInfoReq00 argGetSGInfoReq00);

    //新增技能组信息
    SaveSGInfoRes AddSaveSGInfoRes(SaveSGInfoReq00 argSaveSGInfoReq00);

    //删除技能组信息
    SaveSGInfoRes RmSaveSGInfoRes(SaveSGInfoReq01 argSaveSGInfoReq01);

    //更新技能组信息
    SaveSGInfoRes ModSaveSGInfoRes(SaveSGInfoReq02 argSaveSGInfoReq02);

    //复制技能组信息
    SaveSGInfoRes AddSaveSGInfoReq03(SaveSGInfoReq03 argSaveSGInfoReq03);
}
