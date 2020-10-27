package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllEmailDisInfo.GetAllEmailDisInfoRes;
import pnc.mesadmin.dto.GetEmailDisInfo.GetEmailDisInfoReq00;
import pnc.mesadmin.dto.GetEmailDisInfo.GetEmailDisInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoReq00;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoReq01;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoReq02;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:28
 * @Description:
 */
public interface EmailDisIService {
    //获取分发列表
    GetAllEmailDisInfoRes QALLGetAllEmailDisInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取单个列表
    GetEmailDisInfoRes GetGetEmailDisInfoRes(GetEmailDisInfoReq00 getEmailDisInfoReq00);

    //新增分发
    SaveEmailDisInfoRes AddSaveEmailDisInfoRes(SaveEmailDisInfoReq00 saveEmailDisInfoReq00);

    //删除分发
    SaveEmailDisInfoRes RmSaveEmailDisInfoRes(SaveEmailDisInfoReq01 saveEmailDisInfoReq01);

    //修改分发
    SaveEmailDisInfoRes ModSaveEmailDisInfoRes(SaveEmailDisInfoReq02 saveEmailDisInfoReq02);
}
