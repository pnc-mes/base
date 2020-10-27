package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllSkillInfo.GetAllSkillInfoResB;
import pnc.mesadmin.dto.GetSkillInfo.GetSkillInfoResB;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoReq00;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoReq01;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoReq02;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoResB;

import java.util.List;

/**
 * Created by test on 2017/8/16.
 */
public interface SkillIService {
    GetAllSkillInfoResB GetAllSkillInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    GetSkillInfoResB GetSkillInfoByRuid(int argRuid);

    SaveSkillInfoResB AddSkillInfo(SaveSkillInfoReq00 busData00);

    SaveSkillInfoResB RmSkillInfo(SaveSkillInfoReq01 busData01);

    SaveSkillInfoResB ModSkillInfo(SaveSkillInfoReq02 busData02);

    SaveSkillInfoResB AddSkillInfoByRuid(SaveSkillInfoReq01 busData01);
}
