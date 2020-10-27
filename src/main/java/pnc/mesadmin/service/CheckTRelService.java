package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.CheckTRelDTO.SaveCheckTRelBaseDTO;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

public interface CheckTRelService {
    //获取检验模板关联列表信息
    BaseRes GetAllCTRelInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取检验模板关联
    BaseRes GetCTRelInfo(SaveCheckTRelBaseDTO request);

    //新增
    BaseRes AddCTRelInfo(SaveCheckTRelBaseDTO reqBD00);

    //删除
    BaseRes RmDelCTRelInfo(SaveCheckTRelBaseDTO reqBD00);

    //保存
    BaseRes AddSaveCTRelInfo(SaveCheckTRelBaseDTO reqBD00);
}
