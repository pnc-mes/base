package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllCheckItemDTO.GetCheckItemInfoRequest;
import pnc.mesadmin.dto.GetAllCheckItemDTO.GetCheckItemInfoResponse;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

public interface CheckItemService {
    //获取检验项
    BaseRes GetAllCIInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取检验项信息
    BaseRes GetCIInfo(GetCheckItemInfoRequest request);

    //新增
    BaseRes AddCIInfo(GetCheckItemInfoResponse reqBD00);

    //删除
    BaseRes RmDelCIInfo(GetCheckItemInfoResponse reqBD00);

    //保存
    BaseRes AddSaveCIInfo(GetCheckItemInfoResponse reqBD00);

}
