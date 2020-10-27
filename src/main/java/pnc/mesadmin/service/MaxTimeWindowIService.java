package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllMaxTimeInfo.GetAllMaxTimeInfoRes;
import pnc.mesadmin.dto.GetMaxTimeInfo.GetMaxTimeInfoReq00;
import pnc.mesadmin.dto.GetMaxTimeInfo.GetMaxTimeInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveMaxTimeInfo.SaveMaxTimeInfoReq00;
import pnc.mesadmin.dto.SaveMaxTimeInfo.SaveMaxTimeInfoReq01;
import pnc.mesadmin.dto.SaveMaxTimeInfo.SaveMaxTimeInfoReq02;
import pnc.mesadmin.dto.SaveMaxTimeInfo.SaveMaxTimeInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:27
 * @Description:
 */
public interface MaxTimeWindowIService {
    //查询最大时间列表
    GetAllMaxTimeInfoRes QALLGetAllMaxTimeInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询单条信息
    GetMaxTimeInfoRes GetGetMaxTimeInfoRes(GetMaxTimeInfoReq00 getMaxTimeInfoReq00);

    //保存最大时间信息
    SaveMaxTimeInfoRes AddSaveMaxTimeInfoRes(SaveMaxTimeInfoReq00 saveMaxTimeInfoReq00);

    //删除最大时间信息
    SaveMaxTimeInfoRes RmSaveMaxTimeInfoRes(SaveMaxTimeInfoReq01 saveMaxTimeInfoReq01);

    //修改最大信息
    SaveMaxTimeInfoRes ModSaveMaxTimeInfoRes(SaveMaxTimeInfoReq02 saveMaxTimeInfoReq02);
}
