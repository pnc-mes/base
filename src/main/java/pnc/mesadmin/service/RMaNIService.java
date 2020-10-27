package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllRMaNInfo.GetAllRMaNInfoRes;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoRes;
import pnc.mesadmin.dto.GetRMaNInfo.GetRMaNInfoReq00;
import pnc.mesadmin.dto.GetRMaNInfo.GetRMaNInfoRes;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoReq00;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveRMaNInfo.*;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public interface RMaNIService {
    //查询产成品入库列表
    GetAllRMaNInfoRes QALLGetAllRMaNInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询产成品入库信息
    GetRMaNInfoRes GetGetRMaNInfoRes(GetRMaNInfoReq00 argGetRMaNInfoReq00);

    //新增产成品入库信息
    SaveRMaNInfoRes AddSaveRMaNInfoRes(SaveRMaNInfoReq00 argSaveRMaNInfoReq00);

    //查询入库通知单汇总信息
    GetRMaNTotalInfoRes QALLGetRMaNTotalInfoRes(GetRMaNTotalInfoReq00 argGetRMaNTotalInfoReq00);

    //删除入库通知单明细信息
    SaveRMaNInfoRes RmSaveRMaNInfoRes(SaveRMaNInfoReq01 argSaveRMaNInfoReq01);

    //更新入库通知单信息
    SaveRMaNInfoRes ModSaveRMaNInfoRes(SaveRMaNInfoReq02 argSaveRMaNInfoReq02);

    //下达
    SaveRMaNInfoRes ModSaveRMaNInfoRes03(SaveRMaNInfoReq03 argSaveRMaNInfoReq03);

    //取消
    SaveRMaNInfoRes ModSaveRMaNInfoRes04(SaveRMaNInfoReq04 argSaveRMaNInfoReq04);
}
