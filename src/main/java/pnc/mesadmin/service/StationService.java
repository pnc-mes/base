package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoReqBD00;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.GetStationDto.GetStationInfoRes;
import pnc.mesadmin.dto.GetStationDto.SaveStationInfoRes;

import java.util.List;

public interface StationService {
    //获取工位列表信息
    BaseRes GetAllStationInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取工位列表信息【树】
    BaseRes GetAllStationInfoTree(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //根据线体获取工位数量
    BaseRes GetAllStationInfoByLineRd(GetAllReq req);

    //获取详情信息
    BaseRes GetStationInfo(GetStationInfoRes request);

    //新增
    BaseRes AddStationInfo(SaveStationInfoRes reqBD00);

    //删除
    BaseRes RmDelStationInfo(SaveStationInfoRes reqBD00);

    //保存
    BaseRes AddSaveStationInfo(SaveStationInfoRes reqBD00);

    //获取工位列表信息2
    GetAllStationTInfoRes GetAllStationTInfo(GetAllStationTInfoReqBD00 getAllStationTInfoReqBD00);

    //保存
    BaseRes AddSaveStationInfo03(SaveStationInfoRes reqBD00);

    //复制
    BaseRes AddSaveStationInfo04(SaveStationInfoRes reqBD00);

}
