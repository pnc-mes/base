package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllCalendarInfo.GetAllCalendarInfoRes;
import pnc.mesadmin.dto.GetCalendarInfo.GetCalendarInfoReq00;
import pnc.mesadmin.dto.GetCalendarInfo.GetCalendarInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCalendarInfo.*;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:26
 * @Description:生产日历管理
 */
public interface CalendarIService {
    //查询列表
    GetAllCalendarInfoRes QALLGetAllCalendarInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询单个
    GetCalendarInfoRes GetGetCalendarInfoRes(GetCalendarInfoReq00 getCalendarInfoReq00);

    //保存
    SaveCalendarInfoRes AddSaveCalendarInfoReq00(SaveCalendarInfoReq00 saveCalendarInfoReq00);

    //删除
    SaveCalendarInfoRes RomSaveCalendarInfoReq01(SaveCalendarInfoReq01 saveCalendarInfoReq01);

    //编辑
    SaveCalendarInfoRes ModSaveCalendarInfoReq02(SaveCalendarInfoReq02 saveCalendarInfoReq02);

    //复制
   // SaveCalendarInfoRes AddSaveCalendarInfoReq03(SaveCalendarInfoReq03 saveCalendarInfoReq03);

}
