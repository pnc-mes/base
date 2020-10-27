package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllMinTimeWindowInfo.GetAllMinTimeWindowInfoRes;
import pnc.mesadmin.dto.GetMinTimedowInfo.GetMinTimedowInfoReqBD00;
import pnc.mesadmin.dto.GetMinTimedowInfo.GetMinTimedowInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveMinTimedowInfo.SaveMinTimedowInfoReqBD00;
import pnc.mesadmin.dto.SaveMinTimedowInfo.SaveMinTimedowInfoReqBD01;
import pnc.mesadmin.dto.SaveMinTimedowInfo.SaveMinTimedowInfoReqBD02;
import pnc.mesadmin.dto.SaveMinTimedowInfo.SaveMinTimedowInfoRes;

import java.util.List;

/**
 * Created by 乔帅阳 on 2018/7/30.
 */
public interface MinTimeWindowIServie {
    //获取最小时间管控列表信息
    GetAllMinTimeWindowInfoRes QALLGetAllMinTimeWindowInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
    //获取最小时间管控信息
    GetMinTimedowInfoRes GetGetMinTimedowInfoRes(GetMinTimedowInfoReqBD00 argGetMinTimedowInfoReqBD00);
    //新增最小时间管控信息
    SaveMinTimedowInfoRes AddSaveMinTimedowInfoRes(SaveMinTimedowInfoReqBD00 argSaveWOInfoReqBD00);
    //删除最小时间管控信息
    SaveMinTimedowInfoRes RmSaveMinTimedowInfoRes(SaveMinTimedowInfoReqBD01 argSaveMinTimedowInfoReqBD01);
    //更新最小时间管控信息
    SaveMinTimedowInfoRes ModSaveWOInfoRes(SaveMinTimedowInfoReqBD02 argSaveMinTimedowInfoReqBD02);
}
