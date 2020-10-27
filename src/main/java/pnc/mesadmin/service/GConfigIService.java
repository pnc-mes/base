package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetGCInfo.GetGCInfoRes;
import pnc.mesadmin.dto.SaveGCInfo.SaveGCInfoReqBD00;
import pnc.mesadmin.dto.SaveGCInfo.SaveGCInfoRes;
import pnc.mesadmin.entity.CodeRuleInfo;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：全局配置信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-8-25
 * 修改人：
 * 修改时间：
 */
public interface GConfigIService {
    //查询全局配置列表信息
    GetGCInfoRes QALLselectAllGetGCInfo();

    //新增全局配置信息
    SaveGCInfoRes AddinsertSaveGCInfo(SaveGCInfoReqBD00 busData00);

    //生成代码规则
    String GetCreateSC(CodeRuleInfo codeRuleInfo);

}
