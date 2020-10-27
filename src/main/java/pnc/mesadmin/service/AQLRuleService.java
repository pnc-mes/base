package pnc.mesadmin.service;

import pnc.mesadmin.dto.AQLRuleDTO.AQLRuleBaseDTO;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

public interface AQLRuleService {
    //获取AQL列表信息
    BaseRes GetAllAQLInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取检验文档
    BaseRes GetAQLInfo(AQLRuleBaseDTO request);

    //新增
    BaseRes AddAQLInfo(AQLRuleBaseDTO reqBD00);

    //删除
    BaseRes RmDelAQLInfo(AQLRuleBaseDTO reqBD00);

    //保存
    BaseRes AddSaveAQLInfo(AQLRuleBaseDTO reqBD00);
}
