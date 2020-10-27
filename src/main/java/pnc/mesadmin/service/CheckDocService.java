package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.CheckDocDTO.CheckDocBaseDTO;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

public interface CheckDocService {
    //获取检验文档-列表信息
    BaseRes GetAllCDOCInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取检验文档
    BaseRes GetCDOCInfo(CheckDocBaseDTO request);

    //新增
    BaseRes AddCDOCInfo(CheckDocBaseDTO reqBD00);

    //删除
    BaseRes RmDelCDOCInfo(CheckDocBaseDTO reqBD00);

    //保存
    BaseRes AddSaveCDOCInfo(CheckDocBaseDTO reqBD00);

    //复制
    BaseRes AddCopyCDOCInfo(CheckDocBaseDTO reqBD00);

}
