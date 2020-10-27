package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoReq00;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoRes;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoResD;
import pnc.mesadmin.dto.GetCMGCInfo.GetCMGCInfoReqBD00;
import pnc.mesadmin.dto.GetCMGCInfo.GetCMGCInfoReqBD01;
import pnc.mesadmin.dto.GetCMGCInfo.GetCMGCInfoRes;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoReqBD00;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoRes;
import pnc.mesadmin.dto.GetCMWFInfo.GetCMWFInfoReqBD00;
import pnc.mesadmin.dto.GetCMWFInfo.GetCMWFInfoReqBD01;
import pnc.mesadmin.dto.GetCMWFInfo.GetCMWFInfoRes;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoReqBD00;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoRes;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.entity.HoldInfo;
import pnc.mesadmin.entity.UnHoldInfo;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：通用查询业务信息
 * 创建人：pjf
 * 创建时间：2020-10-14
 * 修改人：
 * 修改时间：
 */
public interface CommonIService {
    GetCMWFInfoRes getCMWFInfo(GetCMWFInfoReqBD00 objEGetCMWFInfoReqBD00);

    // 根据WoCode工单号查询工艺
    GetCMWFInfoRes getCMWFInfoByWoCode(GetCMWFInfoReqBD01 objEGetCMWFInfoReqBD01);

    //获取批次信息 张亮亮
    GetCMBBInfoRes GetGetCMBBInfoRes(GetCMBBInfoReq00 argGetCMBBInfoReq00);

    //根据物料ID获取序号信息  (By-pjf)
    GetCMSRInfoRes GetCMSRInfoByMV(GetCMSRInfoReqBD00 argBD00);

    //获取全局配置信息 LFZ 00状态下
    GetCMGCInfoRes GetModeName(GetCMGCInfoReqBD00 argGetCMGCInfoReqBD00);

    //获取全局配置信息 LFZ 01状态下
    GetCMGCInfoRes GetMsgName(GetCMGCInfoReqBD01 argGetCMGCInfoReqBD01);

    //获取工单关联信息
    GetCMWInfoRes GetCMW(GetCMWInfoReqBD00 argBD00);

    /**
     * 查询批次信息(新)
     * @param request
     * @return
     */
    PageResult<GetCMBBInfoResD> GetBatchInfos(BaseRequest<GetCMBBInfoReq00> request);

    /**
     * 查询冻结批次记录
     * @param request
     * @return
     */
    PageResult<HoldInfo> GetAllHoldInfo(BaseRequest request);

    /**
     * 查询解冻批次记录
     * @param request
     * @return
     */
    PageResult<UnHoldInfo> GetAllUnHoldInfo(BaseRequest request);
}
