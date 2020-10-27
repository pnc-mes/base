package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllUrSGInfo.GetAllUrSGInfoReqBD00;
import pnc.mesadmin.dto.GetAllUrSGInfo.GetAllUrSGInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoReqBD02;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoRes;
import pnc.mesadmin.dto.SystemException;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：技能培训记录信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-8-28
 * 修改人：
 * 修改时间：
 */
public interface UsrSkillIService {
    //查询技能培训记录列表信息
    GetAllUrSGInfoRes QALLGetAllUrSGInfo(GetAllUrSGInfoReqBD00 argBD00, List<InitDataField> argInitDataFields) throws SystemException;

    //保存技能培训记录信息
    SaveUrSGInfoRes ModSaveUrSGInfoRes(SaveUrSGInfoReqBD02[] argSaveUrSGInfoReqBD02);
}
