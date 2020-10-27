package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.CheckTempDTO.CheckTempBaseDto;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

public interface CheckTempService {
    //获取检验模板-列表信息
    BaseRes GetAllCTPInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取检验模板
    BaseRes GetCTPInfo(CheckTempBaseDto request);

    //新增
    BaseRes AddCTPInfo(CheckTempBaseDto reqBD00);

    //删除
    BaseRes RmDelCTPInfo(CheckTempBaseDto reqBD00);

    //保存
    BaseRes AddSaveCTPInfo(CheckTempBaseDto reqBD00);

    //复制
    BaseRes AddCopyCTPInfo(CheckTempBaseDto reqBD00);

}
