package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllBadInfoDto.GetAllBadInfoRes;
import pnc.mesadmin.dto.GetAllBadInfoDto.SaveBadInfoReq00;
import pnc.mesadmin.dto.GetAllBadInfoDto.SaveBadInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveDcInfo.SaveDcInfoReqBD01;

import java.util.List;

public interface BadService {
    PageResult<MBaseRes> QALLGetAllDcInfoRes(BaseRequest req);

    //获取不良原因列表信息
    MBaseRes GetAllBadInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取不良原因详情信息
    MBaseRes GetBadInfo(GetAllBadInfoRes request);

    //新增
    MBaseRes AddBadInfo(SaveBadInfoRes reqBD00);

    //删除
    MBaseRes RmDelBadInfo(SaveDcInfoReqBD01 reqBD00);

    //保存
   MBaseRes AddSaveBadInfo(SaveBadInfoRes reqBD00);

}
